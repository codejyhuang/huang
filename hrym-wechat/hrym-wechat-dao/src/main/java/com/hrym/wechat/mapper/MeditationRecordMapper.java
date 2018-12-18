package com.hrym.wechat.mapper;

import com.hrym.wechat.entity.MeditationRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * Created by hrym13 on 2018/4/14.
 */
@Repository
public interface MeditationRecordMapper {

    /**
     * 查找本活动有多少人参加
     *
     * @param scheduleId
     * @return
     */
    @Select(" SELECT COUNT(1) FROM x_meditation_record WHERE schedule_id = #{scheduleId}")
    Integer selectUserCount(Integer scheduleId);

//    /**
//     * 加入共修活动
//     * @param record
//     */
//    @Insert(" insert into x_meditation_record(schedule_id,status,created_time,user_id)values(#{scheduleId},#{status},#{createdTime},#{userId})")
//    void insertMeditationRecord(MeditationRecord record);

    /**
     * 查看用户是否在此共修中
     *
     * @param userId
     * @param meditationTypeId
     * @return
     */
    @Select(" select * from x_meditation_record where user_id = #{userId} AND meditation_type_id= #{meditationTypeId}")
    MeditationRecord findMeditationRecord(@Param("userId") Integer userId, @Param("meditationTypeId") Integer meditationTypeId);

    //新版加入活动

    /**
     * 加入共修活动
     *
     * @param record
     */
    @Insert(" insert into x_meditation_record (meditation_type_id,user_id,created_time,is_top)values(#{meditationTypeId},#{userId},#{createdTime},#{isTop})")
    void insertMeditationTypeRecord(MeditationRecord record);

    /**
     * 改变置顶的状态
     *
     * @param med
     */
    @Update("update x_meditation_record set is_top = #{isTop} where meditation_type_id = #{meditationTypeId} and user_id = #{userId}")
    void updateMedRecordIsTop(MeditationRecord med);


}
