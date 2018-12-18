package com.hrym.rpc.auth.dao.mapper.custom;

import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.VO.customVO.TaskPlanCustomVO;
import com.hrym.rpc.app.dao.model.task.custom.TaskPlanCustom;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Created by hrym13 on 2018/6/21.
 */
public interface TaskPlanCustomMapper {

    /**
     * 根据用户ID和自定义功课ID查找该任务
     * @param customId
     * @param uuid
     * @return
     */
    @DataSource("slave")
    @Select(" select * from t_task_plan_custom where custom_id = #{customId} and uuid = #{uuid} and and is_exit = 1")
    TaskPlanCustom findTaskPlanCustomById(@Param("customId") Integer customId,@Param("uuid") Integer uuid);

    /**
     * 创建自定义任务
     * @param taskPlanCustom
     */
    @Insert(" insert into t_task_plan_custom " +
            "(uuid,custom_id,create_time,update_time,recent_add,is_exit)" +
            " values" +
            "(#{uuid},#{customId},#{createTime},#{updateTime},#{recentAdd},#{isExit})")
    void insertTaskPlanCustom(TaskPlanCustom taskPlanCustom);

    /**
     * 更新自定义功课新添加标签
     * @param recentAdd
     * @param taskId
     */
    @Update("update t_task_plan_custom set recent_add = #{recentAdd} where task_id = #{taskId} and uuid = #{uuid} ")
    void updateTaskPlanCustomByTaskId(@Param("recentAdd") Integer recentAdd,
                                      @Param("taskId") Integer taskId,
                                      @Param("uuid") Integer uuid);

    /**
     * 自定义功课图片
     * @return
     */
    @DataSource("slave")
    @Select(" SELECT task_id,today_commit_num,(SELECT pic FROM t_resource_picture WHERE type = 1)as item_pic FROM t_task_plan_custom WHERE task_id = #{taskId} ")
    TaskPlanCustomVO CustomNumberStart(Integer taskId);





}
