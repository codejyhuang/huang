package com.hrym.wechat.mapper;

import com.hrym.common.annotation.DataSource;
import com.hrym.wechat.entity.FlockItem;
import com.hrym.wechat.entity.FlockRecordCustom;
import com.hrym.wechat.entity.ItemCustom;
import com.hrym.wechat.smallProgram.QueryObjectParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ItemCustomMapper {


    /**
     * 添加自定义功课
     * @param custom
     */
    void buildCustom(ItemCustom custom);

    /**
     * 查询用户 所有自定义功课
     * @param qo
     * @return
     */
    @DataSource("slave")
    List<ItemCustom> listAllByUuid(QueryObjectParam qo);
    
     ItemCustom findTaskPlanCustomById(@Param("uuid") Integer uuid,@Param("itemId") Integer itemId);
    
    /**
     * 自定义功课手动报数
     *
     * @param reportNum
     * @param upTime
     * @param taskId
     */
    @Update("update t_task_plan_custom " +
            "set done_num = done_num+#{reportNum}," +
            "plan_target_value = plan_target_value+#{num}," +
            "today_commit_num = today_commit_num+#{reportNum}," +
            "custom_done_num = custom_done_num+#{reportNum}," +
            "counting_method = 0," +
            "update_time = #{upTime} " +
            "where task_id = #{taskId}")
    void updateDoneNumByTaskId(@Param("reportNum") long reportNum,
                               @Param("num") long num,
                               @Param("upTime") Integer upTime,
                               @Param("taskId") Integer taskId);
    
    /**
     * 自定义功课导入数据
     *
     * @param reportNum
     * @param upTime
     * @param taskId
     */
    @Update("update t_task_plan_custom " +
            "set done_num = done_num+#{reportNum}," +
            "today_commit_num = today_commit_num+#{reportNum}," +
            "custom_done_num = custom_done_num+#{reportNum}," +
            "update_time = #{upTime} " +
            "where task_id = #{taskId}")
    void updateImportDoneNumByTaskId(@Param("reportNum") long reportNum,
                               @Param("upTime") Integer upTime,
                               @Param("taskId") Integer taskId);

    /**
     * 自定义功课更新《通用语句》
     *
     * @param itemCustom
     */
    @Update("<script>" +
            "update t_task_plan_custom set " +
            "<if test=\"recentAdd !=null or recentAdd == 0 \">" +
            "recent_add = #{recentAdd}," +
            "</if>" +
            "<if test=\"isExit !=null or isExit == 0 \">" +
            "is_exit = #{isExit}," +
            "</if>" +
            "<if test=\"dayTarget !=null or dayTarget == 0 \">" +
            "day_target = #{dayTarget}," +
            "</if>" +
            "<if test=\"planTarget !=null or planTarget == 0 \">" +
            "plan_target = #{planTarget}," +
            "</if>" +
            "<if test=\"planTargetValue !=null or planTargetValue == 0 \">" +
            "plan_target_value = #{planTargetValue}," +
            "</if>" +
            "<if test=\"targetTime !=null or targetTime == 0 \">" +
            "target_time = #{targetTime}," +
            "</if>" +
            "<if test=\"competeTime !=null or competeTime == 0 \">" +
            "compete_time = #{competeTime}," +
            "</if>" +
            "update_time = #{updateTime} " +
            "where task_id = #{taskId}" +
            "</script>")
    void updateTaskPlanCustom(ItemCustom itemCustom);
    
    /**
     * 保存功课记录
     */
    @Insert("insert t_task_record_custom(task_id,user_id,report_num,report_time,item_id,record_method,record_status,ymd,year,month) " +
            "values(#{taskId},#{userId},#{reportNum},#{reportTime},#{itemId},#{recordMethod},#{recordStatus},from_unixtime(unix_timestamp(now()), '%Y%m%d'),year(CURRENT_DATE),month(CURRENT_DATE))")
    void saveTaskRecord(FlockRecordCustom record);

    /**
     * 查询 自定义功课 unit 量词
     * @param itemId
     * @return
     */
    @DataSource("slave")
    String selectUnitByItemId(Integer itemId);
    
    @DataSource("slave")
    @Select("SELECT custom_id as item_id,custom_name as item_name FROM t_resource_item_custom WHERE custom_id = #{itemId}")
    FlockItem findCustomName(Integer itemId);

    /**
     * 查询是否有同名自建功课
     * @param lessonName
     * @return
     */
    @DataSource("slave")
    int queryCountByCustomName(@Param("lessonName") String lessonName,@Param("userId") Integer userId);

    /**
     * 查询导入的历史 record
     * @param taskId
     * @return
     */
    @Select("select report_num from t_task_record_custom where task_id = #{taskId} and record_method = 5")
    @DataSource("slave")
    Long queryHistoryRecordNum(Integer taskId);

    /**
     * 更新导入历史记录
     * @param reportNum
     * @param taskId
     */
    @Update("update t_task_record_custom set report_num = #{reportNum} where task_id = #{taskId} and record_method = 5")
    void updateImportRecordNum(@Param("reportNum") long reportNum,@Param("taskId") Integer taskId);
}
