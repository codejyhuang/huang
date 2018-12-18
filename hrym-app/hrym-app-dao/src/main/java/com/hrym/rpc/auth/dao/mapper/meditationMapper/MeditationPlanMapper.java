package com.hrym.rpc.auth.dao.mapper.meditationMapper;

import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.task.TaskSubPlan;
import com.hrym.rpc.app.dao.model.task.meditation.MeditationPlan;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Created by mj on 2018/5/29.
 */
public interface MeditationPlanMapper {

    /**
     * 查找用户最近一次任务
     * @param userId
     * @return
     */
    @DataSource("slave")
    @Select("select * from t_task_plan_meditation where user_id=#{userId} order by update_time desc limit 1")
    MeditationPlan findLastOneByUserId(Integer userId);

    /**
     * 查找任务 （条件：用户id；功课id；内容id）
     * @param userId
     * @param itemId
     * @param contentId
     * @return
     */
    @DataSource("slave")
    @Select("select * from t_task_plan_meditation " +
            "where user_id=#{userId} and item_id=#{itemId} and content_id=#{contentId}")
    MeditationPlan findOneByUserIdAndItemIdAndContentId(@Param("userId") Integer userId,
                                                        @Param("itemId") Integer itemId,
                                                        @Param("contentId") Integer contentId);

    /**
     * 查找任务 （条件：用户id；功课id；内容id）
     * @param plan
     * @return
     */
    @Insert("insert into t_task_plan_meditation " +
            "(down_num,item_id,user_id,content_id,time_num,create_time,update_time) " +
            "values(#{downNum},#{itemId},#{userId},#{contentId},#{timeNum},#{createTime},#{updateTime})")
    void saveMeditationPlan(MeditationPlan plan);

    /**
     * 更新任务 （条件：用户id；功课id；内容id）
     * @param plan
     * @return
     */
    @Update("update t_task_plan_meditation set time_num=#{timeNum},update_time=#{updateTime} " +
            "where user_id=#{userId} and item_id=#{itemId} and content_id=#{contentId}")
    void updateTimeNumByUserIdAndItemIdAndContentId(MeditationPlan plan);

    /**
     * 更新任务 （条件：用户id；功课id；内容id）
     * @param plan
     * @return
     */
    @Update("update t_task_plan_meditation set time_num=#{timeNum},down_num=down_num+#{downNum},update_time=#{updateTime} " +
            "where user_id=#{userId} and item_id=#{itemId} and content_id=#{contentId}")
    void updateDownNumByUserIdAndItemIdAndContentId(MeditationPlan plan);

    /**
     * 查找用户最近一次任务(条件：用户id；功课id；内容id)
     * @param userId
     * @param itemId
     * @param contentId
     * @return
     */
    @DataSource("slave")
    @Select("select * from t_task_plan_meditation where user_id=#{userId} and item_id=#{itemId} and content_id=#{contentId} order by id desc limit 1")
    MeditationPlan findLastOneByUserIdAndItemIdAndContentId(@Param("userId") Integer userId,
                                                            @Param("itemId") Integer itemId,
                                                            @Param("contentId") Integer contentId);

    /**
     * 查找最新插的ID
     * @return
     */
    @DataSource("master")
    @Select("SELECT LAST_INSERT_ID()")
    int getLastId();
}
