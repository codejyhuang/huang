package com.hrym.wechat.mapper;

import com.hrym.common.annotation.DataSource;
import com.hrym.wechat.entity.MeditationSchedule;
import com.hrym.wechat.smallProgram.MeditationScheduleParam;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hrym13 on 2018/4/14.
 */
@Repository
public interface MscheduleMapper {

    /**
     * 查找我加入的共修活动
     * @param userId
     * @return
     */
    @Select(" SELECT e.schedule_id,e.target_number,e.real_number,e.created_time,e.expect_time,e.active_title," +
            "e.active_head_url,e.group_status,e.start_time,e.active_author,d.user_id,y.meditation_type_name " +
            "FROM x_meditation_schedule e LEFT JOIN x_meditation_record d ON e.schedule_id=d.schedule_id " +
            "LEFT JOIN x_meditation_type y ON y.meditation_type_id=e.meditation_type_id WHERE user_id = #{userId}")
    List<MeditationSchedule> findMscheduleByUserId(Integer userId);

    /**
     * 查找我未加入的共修活动
     * @param userId
     * @return
     */
   @Select("SELECT e.schedule_id,e.schedule_intro,e.target_number,e.active_title,e.start_time,e.expect_time,t.meditation_type_name,t.meditation_type_intro," +
           "(SELECT SUM(count) from x_meditation_task WHERE schedule_id=e.schedule_id)as realNumber," +
           "(SELECT COUNT(1) FROM x_meditation_record WHERE schedule_id=e.schedule_id) as userNumber " +
           "FROM x_meditation_schedule e " +
           "LEFT JOIN x_meditation_type t ON t.meditation_type_id=e.meditation_type_id " +
           "WHERE  UNIX_TIMESTAMP(date_sub(curdate(),interval -1 day))<e.expect_time and e.schedule_id NOT in(SELECT schedule_id FROM x_meditation_record WHERE user_id=#{userId})")
    List<MeditationSchedule> findMschedule(Integer userId);

    /**
     * 查找本活动共有多少人
     * @param param
     * @return
     */
    @Select("SELECT COUNT(1) as userNumber FROM x_meditation_record WHERE schedule_id = #{scheduleId}")
    Integer findUserCount(MeditationScheduleParam param);


    /**
     * 未参加共修活动简介
     * @param param
     * @return
     */
    @DataSource("/slave")
    @Select(" SELECT e.*,y.meditation_type_name," +
            "(SELECT SUM(count)as count FROM x_count_record WHERE schedule_id=e.schedule_id) as realNumber," +
            "(SELECT COUNT(user_id) FROM x_meditation_record WHERE schedule_id=#{scheduleId}) as userNumber " +
            "FROM x_meditation_schedule e" +
            " LEFT JOIN x_meditation_type y ON y.meditation_type_id=e.meditation_type_id " +
            "WHERE e.schedule_id=#{scheduleId}")
    MeditationSchedule findMscheduleById(MeditationScheduleParam param);

    /**
     * 参加的共修活动简介《活动未结束》
     * @param userId
     * @param scheduleId
     * @return
     */
    @DataSource("/slave")
    @Select(" SELECT e.*," +
            "(SELECT SUM(count)as count FROM x_count_record WHERE schedule_id=e.schedule_id) as realNumber," +
            "(SELECT SUM(count)as todayNumber  FROM x_count_record WHERE user_id = #{userId} and schedule_id=e.schedule_id and created_time >=UNIX_TIMESTAMP( date_sub(curdate(),interval 0 day)))as todayNumber, " +
            "(SELECT COUNT(1) FROM x_meditation_record WHERE schedule_id =e.schedule_id) as userNumber " +
            "FROM x_meditation_schedule e WHERE e.schedule_id=#{scheduleId} ")
    MeditationSchedule findMscheduleBySId(@Param("userId") Integer userId, @Param("scheduleId") Integer scheduleId);

    /**
     * 参加的共修活动简介《活动结束》
     * @param userId
     * @param scheduleId
     * @return
     */
    @Select("SELECT e.schedule_id,e.target_number,e.created_time,e.start_time,e.expect_time,e.active_title," +
            "(SELECT COUNT(user_id) FROM x_meditation_record WHERE schedule_id =e.schedule_id) as userNumber," +
            "(SELECT count FROM x_meditation_task WHERE user_id=#{userId} AND schedule_id=e.schedule_id) as myNumber," +
            "(SELECT created_time FROM x_meditation_record WHERE user_id = #{userId} AND schedule_id = e.schedule_id)as myDay," +
            " (SELECT SUM(count) FROM x_meditation_task WHERE schedule_id=e.schedule_id)as realNumber" +
            " FROM x_meditation_schedule e WHERE e.schedule_id =#{scheduleId} ")
    MeditationSchedule findMscheduleByGroupId(@Param("userId") Integer userId, @Param("scheduleId") Integer scheduleId);
//
//    @Update(" update x_meditation_schedule set join_people=join_people+1 where schedule_id=#{scheduleId} ")
//    void updateMedScheduleJoinPeople(@Param("scheduleId") Integer scheduleId );

    @Select("SELECT schedule_id from x_meditation_record WHERE schedule_id=#{scheduleId} AND user_id = #{userId} ")
MeditationSchedule findScheduleBySIdUId(@Param("userId") Integer userId,@Param("scheduleId") Integer scheduleId);


//    新版本共修

    /**
     * 根据共修类型ID查找共修内容
     * @param id
     * @return
     */
    @DataSource("slave")
    @Select("SELECT e.refresh_time,e.schedule_id,e.target_number,e.active_title,e.meditation_type_id,e.schedule_type,\n" +
            "(SELECT SUM(count)FROM x_meditation_task WHERE schedule_id=e.schedule_id) as real_number\n" +
            "FROM x_meditation_schedule e  WHERE e.meditation_type_id=#{id}")
    List<MeditationSchedule> findMeditationScheduleByTypeId(Integer id);

    @DataSource("slave")
    @Select("select * from x_meditation_schedule WHERE meditation_type_id=#{id}")
    MeditationSchedule findScheduleById(@Param("id") Integer meditationTypeId);


    /**
     * 参加的共修活动简介《多版本》
     * @param meditationTypeId
     * @param userId
     * @return
     */
    @DataSource("slave")
    @Select("SELECT e.schedule_id,e.target_number,e.meditation_type_id,e.schedule_type,e.refresh_time,e.active_title,\n" +
            "(SELECT SUM(count) FROM x_meditation_task WHERE schedule_id=e.schedule_id  and meditation_type_id=e.meditation_type_id) as realNumber,\n" +
            "(SELECT count FROM x_meditation_task WHERE schedule_id=e.schedule_id and user_id=#{userId} and meditation_type_id=e.meditation_type_id) as myNumber" +
            " FROM x_meditation_schedule e WHERE e.meditation_type_id=#{id}")
    List<MeditationSchedule> findScheduleByTypeId(@Param("id") Integer meditationTypeId,@Param("userId") Integer userId);

    /**
     * 参加的共修活动报数展示《多版本》
     * @param id
     * @return
     */
    @DataSource("slave")
    @Select("SELECT e.law_time,e.update_time,e.schedule_id,e.meditation_type_id,e.target_number,e.active_title,e.schedule_type,e.refresh_time\n" +
            "FROM x_meditation_schedule e\n" +
            " WHERE e.meditation_type_id=#{id}")
    List<MeditationSchedule> findScheduleTypeId( Integer id);

    /**
     *  根据ID查找共修内容名称
     * @param id
     * @return
     */
    @DataSource("slave")
    @Select("SELECT active_title,law_time,refresh_time FROM x_meditation_schedule WHERE schedule_id=#{id}")
    MeditationSchedule findMeditationScheduleName(Integer id);

    /**
     * 根据ID修改截止时间
     * @param id
     * @param lawTime
     */
    @Update("update x_meditation_schedule set law_time=#{lawTime} WHERE schedule_id=#{id} ")
    void updateMeditationSchedule( @Param("lawTime") Integer lawTime,@Param("id") Integer id);

    /**
     * 共修完成
     * @param userId
     * @param id
     * @return
     */
    @DataSource("slave")
    @Select(" SELECT e.target_number,e.active_title,e.schedule_id,e.meditation_type_id,\n" +
            "(SELECT count FROM x_meditation_task WHERE schedule_id=e.schedule_id AND user_id = #{userId}) as myNumber,\n" +
            "(SELECT COUNT(user_id) FROM x_meditation_record WHERE meditation_type_id=e.meditation_type_id) as userNumber,\n" +
            "(SELECT SUM(count) FROM x_meditation_task WHERE meditation_type_id = e.meditation_type_id) as realNumber,\n" +
            "(SELECT created_time FROM x_meditation_record WHERE meditation_type_id=e.meditation_type_id AND user_id= #{userId}) as createdTime\n" +
            "FROM x_meditation_schedule e WHERE e.meditation_type_id = #{id} ")
    MeditationSchedule finshSchedule(@Param("userId") Integer userId,@Param("id") Integer id);
}
