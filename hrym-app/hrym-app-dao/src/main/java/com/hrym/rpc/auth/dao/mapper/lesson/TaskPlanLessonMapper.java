package com.hrym.rpc.auth.dao.mapper.lesson;

import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.VO.lessonVO.TaskPlanLessonVO;
import com.hrym.rpc.app.dao.model.task.custom.TaskPlanCustom;
import com.hrym.rpc.app.dao.model.task.lesson.TaskPlanLesson;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/** 普通功课添加
 * Created by hrym13 on 2018/6/21.
 */
public interface TaskPlanLessonMapper {

    /**
     * 根据用户ID和功课ID,功课内容ID查找功课任务是否已创建
     * @param itemId
     * @param itemContentId
     * @param uuid
     * @return
     */
    @DataSource("slave")
    @Select(" select * from t_task_plan_lesson where " +
            "item_id = #{itemId} and uuid = #{uuid} and item_content_id = #{itemContentId} and is_exit = 1")
    TaskPlanLesson findLessonByItemIdAndUuidAndContendId(@Param("itemId") Integer itemId,
                                                         @Param("uuid") Integer uuid,
                                                         @Param("itemContentId")Integer itemContentId);
    /**
     * 根据用户ID和功课ID查找该任务内容
     * @param itemId
     * @param uuid
     * @return
     */
    @DataSource("slave")
    @Select(" select * from t_task_plan_lesson where item_id = #{itemId} and uuid = #{uuid} and is_exit = 1")
    TaskPlanLesson findLessonByItemIdAndUuid(@Param("itemId") Integer itemId,
                                             @Param("uuid") Integer uuid);

    /**
     * 正常功课任务创建
     * @param taskPlanLesson
     */
    @Insert("insert into t_task_plan_lesson" +
            " (uuid,item_id,item_content_id,create_time,update_time,recent_add,is_exit)" +
            " values" +
            "(#{uuid},#{itemId},#{itemContentId},#{createTime},#{updateTime},#{recentAdd},#{isExit})")
    void insertTaskPlanLesson(TaskPlanLesson taskPlanLesson);


    /**
     * 更新正常功课新添加标签
     * @param recentAdd
     * @param taskId
     */
    @Update("update t_task_plan_lesson set recent_add = #{recentAdd} where task_id = #{taskId} and uuid = #{uuid} ")
    void updateTaskPlanLessonByTaskId(@Param("recentAdd") Integer recentAdd,
                                      @Param("taskId") Integer taskId,
                                      @Param("uuid") Integer uuid);


    /**
     * 查询用户所有添加的功课
     * @param uuid
     * @return
     */
    @DataSource("slave")
    @Select(" select * from t_task_plan_lesson_custom_view " +
            "where uuid = #{uuid} and is_exit = 1 order by update_time desc")
    List<TaskPlanLessonVO> findAllByUuid(Integer uuid);
}
