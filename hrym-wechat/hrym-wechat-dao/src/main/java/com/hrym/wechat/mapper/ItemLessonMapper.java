package com.hrym.wechat.mapper;

import com.hrym.common.annotation.DataSource;
import com.hrym.wechat.entity.*;
import com.hrym.wechat.smallProgram.QueryObjectParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.security.access.method.P;

import java.util.List;

public interface ItemLessonMapper {

    List<ItemLesson> listAllByQo(QueryObjectParam qo);
    
    /**
     * 根据任务id查询自定义任务
     *
     * @param
     * @return
     */
    @DataSource("slave")
    @Select("select a.task_id,a.item_id,a.item_content_id,a.start_time,a.plan_target,a.done_num,a.day_target,a.today_commit_num,a.create_time,a.update_time,b.bg_banner_pic as bgPic,a.plan_target_value,a.target_time " +
            "from t_task_plan_lesson a " +
            "left join t_resource_item_lesson b " +
            "on a.item_id=b.item_id " +
            "where a.uuid = #{uuid} and a.item_content_id = #{itemContentId} and a.item_id = #{itemId}")
    ItemLessonTaskPlan findTaskPlanLessonByTaskId(@Param("uuid") Integer uuid,
                                                  @Param("itemContentId") Integer itemContentId,
                                                  @Param("itemId")Integer itemId);
    
    /**
     * 功课更新《通用语句》
     *
     * @param taskPlanLesson
     */
    @Update("<script>" +
            "update t_task_plan_lesson set " +
            "<if test=\"recentAdd != null or recentAdd == 0\">" +
            "recent_add = #{recentAdd}," +
            "</if>" +
            "<if test=\"isExit != null or isExit == 0\">" +
            "is_exit = #{isExit}," +
            "</if>" +
            "<if test=\"dayTarget != null or dayTarget == 0\">" +
            "day_target = #{dayTarget}," +
            "</if>" +
            "<if test=\"planTarget != null or planTarget == 0\">" +
            "plan_target = #{planTarget}," +
            "</if>" +
            "<if test=\"planTargetValue !=null or planTargetValue == 0 \">" +
            "plan_target_value = #{planTargetValue}," +
            "</if>" +
            "<if test=\"targetTime !=null or targetTime == 0 \">" +
            "target_time = #{targetTime}," +
            "</if>" +
            "update_time = #{updateTime} " +
            "where task_id = #{taskId}" +
            "</script>")
    void updateTaskPlanLesson(ItemLessonTaskPlan taskPlanLesson);
    
    
    /**
     * 功课报数
     *
     * @param reportNum
     * @param upTime
     * @param taskId
     */
    @Update("<script>" +
            "update t_task_plan_lesson " +
            "set done_num = done_num+#{reportNum}," +
            "plan_target_value = plan_target_value+#{num}," +
            "today_commit_num = today_commit_num+#{reportNum}," +
            "<if test=\"countMethod == 0 \">" +
            "custom_done_num = custom_done_num+#{reportNum}," +
            "</if>" +
            "<if test=\"countMethod != 0 \">" +
            "auto_done_num = auto_done_num+#{reportNum}," +
            "</if>" +
            "counting_method = #{countMethod}," +
            "update_time = #{upTime} " +
            "where task_id = #{taskId}" +
            "</script>")
    void updateDoneNumByTaskId(@Param("reportNum") long reportNum,
                               @Param("num") long num,
                               @Param("upTime") Integer upTime,
                               @Param("countMethod") Integer countMethod,
                               @Param("taskId") Integer taskId);
    
    /**
     * 导入历史数据  更新 对应的task
     *
     * @param reportNum
     * @param upTime
     * @param taskId
     */
    @Update("update t_task_plan_lesson " +
            "set done_num = done_num+#{reportNum}," +
            "today_commit_num = today_commit_num+#{reportNum}," +
            "custom_done_num = custom_done_num+#{reportNum}," +
            "update_time = #{upTime} " +
            "where task_id = #{taskId}")
    void updateImportDoneNumByTaskId(@Param("reportNum") long reportNum,
                               @Param("upTime") Integer upTime,
                               @Param("taskId") Integer taskId);


    /**
     * 保存功课记录
     */
    @Insert("insert t_task_record_lesson(task_id,user_id,report_num,report_time,item_id,record_method,item_content_id,record_status,ymd,year,month) " +
            "values(#{taskId},#{userId},#{reportNum},#{reportTime},#{itemId},#{recordMethod},#{itemContentId},#{recordStatus},from_unixtime(unix_timestamp(now()), '%Y%m%d'),year(CURRENT_DATE),month(CURRENT_DATE))")
    void saveTaskRecord(FlockRecordLesson record);
    

//    String selectUnitByItemIdAndContentId(@Param("itemId") Integer itemId, @Param("contentId") Integer itemContentId);

    /**
     * 查询 功课列表
     * @param qo
     * @return
     */
    @DataSource("slave")
    List<FlockLessonDO> queryLessonFromView(QueryObjectParam qo);

    @DataSource("slave")
    FlockLessonDO queryByItemIdAndContentIdAndUuidAndType(FlockItem flockUser);

    /**
     * 查询功课
     * @param itemId
     * @param itemContentId
     * @param type
     * @return
     */
    FlockLessonDO queryLessonFromViewByItemIdAndItemContentIdAndType(@Param("itemId") Integer itemId, @Param("itemContentId") Integer itemContentId,@Param("type") Integer type);

    /**
     * 批量删除  功课
     * @param id
     * @param itemId
     * @param itemContentId
     * @param type
     */
    int batchLogicRemoveFlockLesson(@Param("flockId") Integer id,
                                @Param("itemId") Integer itemId,
                                @Param("itemContentId") Integer itemContentId,
                                @Param("type")Integer type,
                                @Param("state")Integer state);




    FlockItem findItenLessonName( @Param("itemId") Integer itemId,
                                  @Param("itemContentId") Integer itemContentId,
                                  @Param("tableLessonName") String tableLessonName);

    /**
     * 查询用户是否有自建的功课
     * @param uuid
     * @return
     */
    int queryCountByUuidFromView(Integer uuid);

    /**
     * 修该 系统功课的 online_num
     * @param itemId
     * @param itemContentId
     */
    void updateOnlineNum(@Param("itemId") Integer itemId,
                         @Param("itemContentId") Integer itemContentId,
                         @Param("type")Integer type,
                         @Param("num")Integer num);
    
    
    ItemUserUnit queryItemViewUnit(@Param("itemId") Integer itemId,
                                   @Param("itemContentId") Integer itemContentId,
                                   @Param("type") Integer type);
    
    void updateitemListOrder(@Param("itemId") Integer itemId,
                             @Param("itemContentId") Integer itemContentId,
                             @Param("type") Integer type,
                             @Param("order") Integer order,
                             @Param("uuid") Integer uuid,
                             @Param("tableName") String tableName);

    /**
     * 查询导入历史记录 数据
     * @param taskId
     * @return
     */
    @Select("select report_num from t_task_record_lesson where task_id = #{taskId} and record_method = 5 ")
    @DataSource("slave")
    Long queryHistoryRecordNum(Integer taskId);

    /**
     * 更新导入历史 num
     * @param reportNum
     * @param taskId
     */
    @Update("update t_task_record_lesson set report_num = #{reportNum} where task_id = #{taskId} and record_method = 5")
    void updateImportRecordNum(@Param("reportNum") long reportNum, @Param("taskId") Integer taskId);
    
    List<AppRecordCommon> queryItemRecordList(Integer userId);
}
