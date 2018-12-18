package com.hrym.rpc.auth.dao.mapper;

import com.github.abel533.mapper.Mapper;
import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.task.TaskPlan;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


/**
 * 功课Mapper
 * Created by mj on 2017/7/6.
 */
public interface TaskPlanMapper extends Mapper<TaskPlan> {

    /**
     * 分页查询功课计划
     *
     * @param token
     * @return
     */
    @DataSource("slave")
    List<TaskPlan> findTaskByToken(String token);

    /**
     * 添加功课任务
     *
     * @param taskPlan
     */
    @DataSource("master")
    void saveTaskPlan(TaskPlan taskPlan);

    /**
     * @param taskId 删除功课任务
     */
    @DataSource("master")
    void deleteTaskPlanById(Integer taskId);

    /**
     * 通过id查询功课计划状态
     *
     * @param taskId
     * @return
     */
    @DataSource("slave")
    TaskPlan findTaskPlanById(Integer taskId);

    /**
     * 根据任务ID更新计数方式
     *
     * @param countingMethod
     * @param taskId
     */
    @DataSource("master")
    void updateTaskPlanById(@Param("countingMethod") Integer countingMethod, @Param("taskId") Integer taskId);

    /**
     * 根据任务ID更新手动计数上报
     *
     * @param num
     * @param taskId
     */
    @DataSource("master")
    void updateTaskplan(@Param("num") double num, @Param("taskId") Integer taskId);

    /**
     * 根据任务ID更新自动计数上报
     *
     * @param num
     * @param taskId
     */
    @DataSource("master")
    void updateTaskplanByAuto(@Param("num") double num, @Param("taskId") Integer taskId);

    /**
     * 根据功课id查询功课计划并按降序排列
     *
     * @param itemId
     * @return
     */
    @DataSource("slave")
    List<TaskPlan> findAllByItemId(@Param("itemId") Integer itemId,@Param("typeId") Integer typeId,@Param("uuid") Integer uuid);

    /**
     * 根据任务ID更新提醒时间
     *
     * @param taskPlan
     */
    void updateRemainCronById(TaskPlan taskPlan);

    /**
     * 查询我的功课计划
     * @param taskId
     * @return
     */
    @DataSource("slave")
    TaskPlan findMyRankById(Integer taskId);

    /**
     * 更新点赞数量
     *
     * @param unm
     */
    void updatethumbsUpNum(@Param("num") Integer unm, @Param("taskId") Integer taskId);

    /**
     * 根据任务ID更新音频ID
     *
     * @param musicId
     * @param taskId
     */
    void updateMusicIdById(@Param("musicId") Integer musicId, @Param("taskId") Integer taskId);

    /**
     * 通过任务ID查询计划
     *
     * @return
     */
    @DataSource("slave")
    @Select("select * from t_task_plan where task_id=#{taskId}")
    TaskPlan findTaskPlanByTaskId(Integer taskId);

    /**
     * 更新禅修子任务功课数
     *
     * @param taskId
     */
    @Update("update t_task_sub_plan set down_num=down_num+#{downNum} where task_id = #{taskId} and sub_task_id=#{subTaskId}")
    void updateSubTaskNum(@Param("taskId") Integer taskId, @Param("subTaskId") Integer subTaskId, @Param("downNum") double downNum);

    /**
     * 新增禅修子任务功课数
     *
     * @param taskId
     */
    @Insert("insert t_task_sub_plan (task_id,item_id,down_num) VALUES (#{taskId},#{itemId},#{downNum})")
    void insertSubTaskNum(@Param("taskId") Integer taskId, @Param("itemId") Integer itemId, @Param("downNum") double downNum);

    /**
     * 更新禅修总完成数
     *
     * @param taskId
     * @param downNum
     */
    @Update("update t_task_plan set done_num = #{downNum} where task_id=#{taskId}")
    void updateDownNum(@Param("taskId") Integer taskId, @Param("downNum") double downNum);

    /**
     * 通过任务ID查找功课内容ID
     *
     * @param taskId
     * @return
     */
    @DataSource("slave")
    @Select("select * from t_task_plan where task_id=#{taskId}")
    TaskPlan findItemContentIdByTaskId(Integer taskId);

    /**
     * 更新任务存在状态 0:已删除；1：未删除
     * @param taskId
     * @param isExit
     */
    @Update("update t_task_plan set is_exit = #{isExit},update_time=#{time} where task_id=#{taskId}")
    void updateIsExit(@Param("taskId") Integer taskId,@Param("isExit") Integer isExit,@Param("time") Integer time);

    /**
     * 定时每天将今日提交总数清零
     */
    @Update("update t_task_plan set today_commit_num=0")
    void updateTodayCommitNum();

    /**
     * 通过功课id和类型id查询用户的功课计划
     * @param itemId
     * @param typeId
     * @param uuid
     * @return
     */
    @DataSource("slave")
    @Select("select * from t_task_plan where item_id=#{itemId} and type_id=#{typeId} and uuid=#{uuid} and is_exit=1")
    TaskPlan findTaskByItemIdAndTypeIdAndUuid(@Param("itemId") Integer itemId,@Param("typeId") Integer typeId,@Param("uuid") Integer uuid);

    /**
     * 更具任务id更新更新时间s
     * @param time
     * @param taskId
     */
    @Update("update t_task_plan set update_time=#{time} where task_id=#{taskId}")
    void updateUpdateTimeByTaskId(@Param("time") Integer time,@Param("taskId") Integer taskId);

    /**
     * 更具用户id查询用户历史任务(包括具体数据)
     * @param uuid
     * @return
     */
    @DataSource("slave")
    List<TaskPlan> findTaskPlanRecordByUuid(Integer uuid);

    /**
     * 根据用户id和功课id查询功课详细历史数据
     * @param itemId
     * @param uuid
     * @return
     */
    @DataSource("slave")
    List<TaskPlan> findTaskPlanRecordByItemIdByUuid(@Param("itemId") Integer itemId,@Param("uuid") Integer uuid,@Param("typeId") Integer typeId);



    /**
     * 根据用户id查询用户是否有历史任务
     * @param uuid
     * @return
     */
    @DataSource("slave")
    @Select("select task_id from t_task_plan where uuid=#{uuid} and is_exit=0 and done_num!=0")
    List<TaskPlan> findAllTaskPlanRecordByUuid(Integer uuid);

    /**
     * 查找最新插的ID
     * @return
     */
    @DataSource("master")
    @Select("SELECT LAST_INSERT_ID()")
    int getLastId();

    /**
     * 修改功课最近添加状态
     * @param taskId
     */
    @Update("update t_task_plan set recent_add=0 where task_id=#{taskId}")
    void updateRecentAdd(Integer taskId);
}
