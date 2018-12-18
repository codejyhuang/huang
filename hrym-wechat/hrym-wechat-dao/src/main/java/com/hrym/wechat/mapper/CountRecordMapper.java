package com.hrym.wechat.mapper;

import com.hrym.common.annotation.DataSource;
import com.hrym.wechat.entity.CountRecord;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hrym13 on 2018/4/14.
 */
@Repository
public interface CountRecordMapper {

    /**
     * 查找当天报数《首页接口》《计数上报页面》
     *
     * @return
     */
    @Select("SELECT SUM(count) FROM x_count_record WHERE user_id=#{userId} AND schedule_id=#{scheduleId} AND created_time>=UNIX_TIMESTAMP(date_sub(curdate(),interval 0 day))")
    Integer finAllCountByDay(@Param("userId") Integer userId, @Param("scheduleId") Integer scheduleId);

    /**
     * 共修活动的报数录入
     *
     * @param countRecord
     * @return
     */
    @Insert(" insert into x_count_record (meditation_type_id,schedule_id,created_time,user_id,count,law)values(#{meditationTypeId},#{scheduleId},#{createdTime},#{userId},#{count},#{law}) ")
    void insertAllCount(CountRecord countRecord);

    /**
     * 历史上报记录表单版本
     *
     * @param
     * @return
     */
    @DataSource("/slave")
    @Select(" SELECT * FROM x_count_record WHERE user_id=#{userId} and schedule_id=#{scheduleId}  ORDER BY  created_time desc ")
    List<CountRecord> findAllCountRecord(@Param("userId") Integer userId, @Param("scheduleId") Integer scheduleId);

    /**
     * 历史上报记录表多版本
     *
     * @param
     * @return
     */
    @DataSource("/slave")
    @Select("SELECT e.active_title,d.schedule_id,d.user_id,d.count,d.meditation_type_id,d.law,d.created_time,d.count_id FROM x_count_record d\n" +
            " LEFT JOIN x_meditation_schedule e ON d.schedule_id=e.schedule_id\n" +
            " WHERE d.user_id=#{userId} and d.meditation_type_id=#{meditationTypeId}  ORDER BY  d.created_time desc ")
    List<CountRecord> findCountRecordByTypeId(@Param("userId") Integer userId, @Param("meditationTypeId") Integer meditationTypeId);

    /**
     * 共修活动的报数录入多版本
     *
     * @param countRecord
     * @return
     */
    @Insert(" insert into x_count_record (meditation_type_id,schedule_id,created_time,user_id,count,law,law_time)values(#{meditationTypeId},#{scheduleId},#{createdTime},#{userId},#{count},#{law},#{lawTime}) ")
    void insertCount(CountRecord countRecord);

    /**
     * 根据法本预习截止时间查询刷新周期里是否有记录
     *
     * @param startTime
     * @param endTime
     * @return
     */
    @DataSource("slave")
    @Select("SELECT * FROM x_count_record WHERE law_time> #{startTime} AND law_time<= #{endTime} and schedule_id= #{scheduleId} and user_id = #{userId}")
    List<CountRecord> findCountRecordLaw(@Param("startTime") Integer startTime,
                                         @Param("endTime") Integer endTime,
                                         @Param("scheduleId") Integer scheduleId,
                                         @Param("userId") Integer userId);

    /**
     * 删除历史记录表的数据
     *
     * @param countId
     */
    @Delete("delete from x_count_record where count_id = #{countId}")
    void deleteCountRecord(Integer countId);

}
