package com.hrym.app.dao;

import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.wechat.dao.model.MeditationSchedule;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hrym13 on 2018/5/1.
 */
@Repository
public interface MeditationScheduleMgrMapper {


    /**
     * 查找所有的共修活动列表
     * @return
     */
    @DataSource("/slave")
    @Select("SELECT e.* ,t.meditation_type_name FROM x_meditation_schedule e LEFT JOIN x_meditation_type t " +
            " ON t.meditation_type_id=e.meditation_type_id WHERE t.meditation_type_id=#{meditationTypeId}")
    List<MeditationSchedule> findAllMedSchedule(Integer meditationTypeId);

    /**
     * 根据共修ID查找共修活动
     * @param id
     * @return
     */
    @DataSource("/slave")
    @Select(" SELECT e.* ,t.meditation_type_name FROM x_meditation_schedule e LEFT JOIN x_meditation_type t " +
            " ON t.meditation_type_id=e.meditation_type_id WHERE e.schedule_id=#{id}")
    MeditationSchedule findMedAcheduleById(Integer id);

    /**
     * 删除单个共修活动
     * @param id
     */
    @Delete(" delete from x_meditation_schedule where schedule_id = #{id}")
    void deleteMedSchedule(Integer id);


    /**
     * 根据共修类型删除共修活动
     * @param id
     */
    @Delete(" delete from x_meditation_schedule where meditation_type_id = #{id}")
    void deleteMedScheduleByTypeId(Integer id);

    /**
     * 更新共修活动内容
     * @param meditationSchedule
     */
    @Update(" update x_meditation_schedule set target_number=#{targetNumber},expect_time=#{expectTime}," +
            "active_title=#{activeTitle},start_time = #{startTime},meditation_type_id=#{meditationTypeId}," +
            "law_time= #{lawTime},update_time = #{updateTime},refresh_time=#{refreshTime},schedule_type=#{scheduleType} where schedule_id = #{scheduleId}")
    void updateMedSchedule(MeditationSchedule meditationSchedule);

    /**
     * 添加共修活动内容
     * @param meditationSchedule
     */
    @Insert(" insert into x_meditation_schedule (target_number,created_time,expect_time,active_title,start_time,meditation_type_id,update_time,refresh_time,schedule_type,law_time)" +
            "values" +
            "(#{targetNumber},#{createdTime},#{expectTime},#{activeTitle},#{startTime},#{meditationTypeId},#{updateTime},#{refreshTime},#{scheduleType},#{lawTime})")
    void insertMedSchedule(MeditationSchedule meditationSchedule);
}
