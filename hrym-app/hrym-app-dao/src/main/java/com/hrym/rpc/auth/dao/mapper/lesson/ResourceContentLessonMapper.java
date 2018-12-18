package com.hrym.rpc.auth.dao.mapper.lesson;

import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.VO.lessonVO.ContentLessonVO;
import com.hrym.rpc.app.dao.model.task.lesson.ResourceContentLesson;
import com.hrym.rpc.app.dao.model.task.lesson.ResourceItemLesson;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by hrym13 on 2018/6/20.
 * 功课内容接口 t_resource_content_lesson
 */
public interface ResourceContentLessonMapper {

    /**
     * 根据功课ID查找功课内容列表
     * @param itemId
     * @return
     */
    @DataSource("slave")
    @Select(" select item_id,content_pic,item_content_id,version_name,text from t_resource_content_lesson where item_id = #{itemId} order by item_content_id ASC")
    List<ContentLessonVO> findContentLessonByItemId(@Param("itemId") Integer itemId);

    /**
     * 更新功课内容里的定制数量和正在做的人数
     * @param orderNum
     * @param onlineNum
     */
    @Update(" update t_resource_content_lesson set order_num = order_num+#{orderNum},online_num = online_num+#{onlineNum} where item_content_id = #{itemContentId} ")
    void updateLessonOrderNumAndOnlineNum(@Param("orderNum") Integer orderNum,@Param("onlineNum") Integer onlineNum,@Param("itemContentId") Integer itemContentId);

    /**
     * 根据文章内容ID查找功课内容参加人数
     * @param Id
     * @return
     */
    @DataSource("slave")
    @Select(" SELECT item_content_id,item_id,order_num,online_num FROM t_resource_content_lesson WHERE item_content_id = #{Id}")
    ResourceContentLesson findContentLessonById(Integer Id);


}
