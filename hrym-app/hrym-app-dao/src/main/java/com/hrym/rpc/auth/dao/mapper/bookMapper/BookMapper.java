package com.hrym.rpc.auth.dao.mapper.bookMapper;

import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.VO.bookVO.BookcaseVO;
import com.hrym.rpc.app.dao.model.VO.bookVO.TaskItemVO;
import com.hrym.rpc.app.dao.model.task.book.Bookcase;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by mj on 2018/4/16.
 */
public interface BookMapper {

    /**
     * 查找书城列表
     * @param typeId
     * @return
     */
    @DataSource("slave")
    @Select("select " +
            "a.item_id," +
            "a.item_name," +
            "a.item_intro," +
            "a.item_pic," +
            "a.title_desc " +
            "from t_resource_item_book a " +
            "left join t_resource_content_book c on a.item_id=c.item_id " +
//            "left join t_work_item b on a.item_id=b.item_id " +
            "where file_txt like '%.epub%' " +
//            "b.type_id=#{typeId} " +
            "order by a.update_time desc")
    List<TaskItemVO> findBookListByTypeId(Integer typeId);

    /**
     * 查找书城列表
     * @param typeId
     * @return
     */
    @DataSource("slave")
    @Select("select " +
            "a.item_id," +
            "a.item_name," +
            "(select count(user_id) from t_bookcase where item_id=a.item_id) as currentPeopleNum," +
            "a.item_intro," +
            "a.item_pic," +
            "a.title_desc " +
            "from t_resource_item_book a " +
            "left join t_resource_content_book c on a.item_id=c.item_id " +
//            "left join t_work_item b on a.item_id=b.item_id " +
            "where " +
//            "b.type_id=#{typeId} and " +
            "a.item_name like '%${filterStr}%' and file_txt like '%.epub%' " +
            "order by currentPeopleNum desc,a.update_time desc")
    List<TaskItemVO> findBookListByTypeIdAndFilterStr(@Param("typeId") Integer typeId,@Param("filterStr") String filterStr);

    /**
     * 根据功课id查找经书详情
     * @param itemId
     * @return
     */
    @DataSource("slave")
    @Select("select " +
            "a.item_id," +
            "a.item_name," +
            "(select count(user_id) from t_bookcase where item_id=#{itemId}) as currentPeopleNum," +
            "a.item_intro," +
            "a.item_pic," +
            "a.title_desc," +
            "b.file_txt " +
            "from t_resource_item_book a " +
            "left join t_resource_content_book b on a.item_id=b.item_id " +
//            "left join t_work_item c on a.item_id=c.item_id " +
            "where a.item_id=#{itemId} " +
//            "and c.type_id=#{typeId}" +
            "")
    TaskItemVO findBookByItemId(@Param("itemId") Integer itemId,@Param("typeId") Integer typeId);

    /**
     * 根据用户id查找用户的书架
     * @param userId
     * @return
     */
    @DataSource("slave")
    @Select("select " +
            "a.index_id," +
            "a.item_id," +
            "a.type_id," +
            "a.done_num," +
            "a.percent," +
            "b.item_name," +
            "b.item_pic," +
            "b.title_desc," +
            "c.file_txt," +
            "d.unit_desc " +
            "from t_bookcase a " +
            "left join t_resource_item_book b on a.item_id=b.item_id " +
            "left join t_resource_content_book c on a.item_id=c.item_id " +
            "left join t_task_unit d on a.type_id=d.unit_type_id " +
            "where user_id=#{userId} and is_exist=1")
    List<BookcaseVO> findBookByUserId(Integer userId);

    /**
     * 根据功课id类型id用户id查询该用户下的经书信息
     * @param itemId
     * @param typeId
     * @param userId
     * @return
     */
    @DataSource("slave")
    @Select("select * from t_bookcase where item_id=#{itemId} and type_id=#{typeId} and user_id=#{userId} and is_exist=1")
    TaskItemVO findBookByItemIdAndUserIdAndTypeId(@Param("itemId") Integer itemId,
                                                  @Param("typeId") Integer typeId,
                                                  @Param("userId") Integer userId);

    /**
     * 根据功课id类型id用户id查询该用户下的经书信息
     * @param itemId
     * @param typeId
     * @param userId
     * @return
     */
    @DataSource("slave")
    @Select("select * from t_bookcase where item_id=#{itemId} and type_id=#{typeId} and user_id=#{userId}")
    TaskItemVO findByItemIdAndUserIdAndTypeId(@Param("itemId") Integer itemId,
                                                  @Param("typeId") Integer typeId,
                                                  @Param("userId") Integer userId);

    /**
     * 向书架插入经书
     * @param bookcase
     */
    @Insert("insert into t_bookcase" +
            "(user_id,item_id,type_id,done_num,percent,create_time,update_time,is_exist) " +
            "values(#{userId},#{itemId},#{typeId},#{doneNum},#{percent},#{createTime},#{updateTime},#{isExist})")
    void saveBookCase(Bookcase bookcase);

    /**
     * 逻辑删除书架上的经书
     * @param indexId
     */
    @Update("update t_bookcase set is_exist=#{isExist},percent='0%' where index_id=#{indexId}")
    void updateIsExistById(@Param("indexId") Integer indexId,@Param("isExist") Integer isExist);

    /**
     * 更新经书上报数据
     * @param num
     * @param indexId
     */
    @Update("update t_bookcase set done_num=done_num+#{num},percent=#{percent},update_time=#{time} where index_id=#{indexId}")
    void updateReport(@Param("num") Integer num,@Param("percent") String percent,@Param("indexId") Integer indexId,@Param("time") Integer time);

    /**
     * 更新经书上报百分比
     * @param percent
     * @param indexId
     */
    @Update("update t_bookcase set percent=#{percent},update_time=#{time} where index_id=#{indexId}")
    void updatePercent(@Param("percent") String percent,@Param("indexId") Integer indexId,@Param("time") Integer time);
}
