package com.hrym.rpc.auth.dao.mapper.lesson;

import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.VO.lessonVO.ItemLessonVO;
import com.hrym.rpc.app.dao.model.task.lesson.ResourceItemLesson;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by hrym13 on 2018/6/20.
 * 功课mapper类
 */
public interface ResourceItemLessonMapper {

    /**
     * 根据功课名称查找推荐功课
     * @param customName
     * @return
     */
    @DataSource("slave")
    @Select(" select item_id,item_name,item_pic,online_num,order_num,alias_name,item_intro,tags from t_resource_item_lesson " +
            "where item_name like '%${customName}%' or alias_name like '%${customName}}%'")
    List<ItemLessonVO> findTaskItemByItemName(@Param("customName") String customName);

    /**
     * 更新功课里的定制数量和正在做的人数
     * @param orderNum
     * @param onlineNum
     */
    @Update(" update t_resource_item_lesson set order_num = order_num+#{orderNum},online_num = online_num+#{onlineNum} where item_id = #{itemId} ")
    void updateLessonOrderNumAndOnlineNum(@Param("orderNum") Integer orderNum,@Param("onlineNum") Integer onlineNum,@Param("itemId") Integer itemId);

    /**
     * 根据功课名称查找推荐功课
     * @param tag1
     * @param tag2
     * @param tag3
     * @return
     */
    @DataSource("slave")
    @Select("<script> " +
            "select DISTINCT a.item_id,a.item_name,a.item_pic,a.online_num,a.order_num,a.alias_name,a.item_intro,a.tags " +
            "from t_resource_item_lesson a " +
            "left join t_work_item_tag b on a.item_id=b.item_id " +
            "left join t_resource_tag c on b.tag_id=c.tag_id " +
            "where 1=1" +
            "<if test=\"tag1!=null and tag1!='' \">" +
            " and find_in_set(#{tag1},tags) " +
            "</if>" +
            "<if test=\"tag2!=null and tag2!='' \">" +
            " and find_in_set(#{tag2},tags) " +
            "</if>" +
            "<if test=\"tag3!=null and tag3!='' \">" +
            " and find_in_set(#{tag3},tags) " +
            "</if>" +
            "ORDER BY c.tag_weight desc,a.order_num desc,a.tags_num desc,a.update_time desc " +
            "</script>")
    List<ItemLessonVO> findTaskItemByTags(@Param("tag1") String tag1,@Param("tag2") String tag2,@Param("tag3") String tag3);


}
