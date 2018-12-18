package com.hrym.wechat.mapper;

import com.hrym.common.annotation.DataSource;
import com.hrym.wechat.entity.FlockRecordBook;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * Created by hrym13 on 2018/11/9.
 */
@Repository
public interface FlockItemBookMapper {
    
    /**
     * 更新经书上报数据
     *
     * @param num
     * @param indexId
     */
    @Update("update t_bookcase set done_num=done_num+#{num},today_commit_num = today_commit_num + #{num},percent=#{percent},update_time=#{time} where index_id=#{indexId}")
    void updateReport(@Param("num") Integer num, @Param("percent") String percent, @Param("indexId") Integer indexId, @Param("time") Integer time);
    
    @DataSource("slave")
    @Select("select index_id,user_id,item_id,done_num,create_time,update_time,is_exist from t_bookcase " +
            "where item_id = #{itemId} and user_id = #{uuid}")
    FlockRecordBook findFlockRecordBookByUuid(@Param("itemId") Integer itemId,@Param("uuid") Integer uuid);
    
    @Insert("insert into t_task_record_book(task_id,user_id,report_num,report_time,item_id,percent,record_method,ymd,year,month) " +
            "values(#{taskId},#{userId},#{reportNum},#{reportTime},#{itemId},#{percent},#{recordMethod},from_unixtime(unix_timestamp(now()), '%Y%m%d'),year(CURRENT_DATE),month(CURRENT_DATE))")
    void saveRecord(FlockRecordBook book);
    
    /**
     * 查找最新插的ID
     * @return
     */
    @DataSource("master")
    @Select("SELECT LAST_INSERT_ID()")
    int getLastId();


    /**
     * 查询 book  历史记录 reportNum
     * @param taskId
     * @return
     */
    @Select("select report_num from t_task_record_book " +
            "where task_id = #{taskId} and record_method = 5")
    Integer queryHistoryRecord(Integer taskId);

    /**
     * 更新历史记录数量
     * @param num
     * @param taskId
     */
    @Update("update t_task_record_book " +
            "set report_num = #{num} " +
            "where task_id = #{taskId} and record_method = 5 ")
    void updateHistoryRecordNum(@Param("num") int num,@Param("taskId") Integer taskId);
}
