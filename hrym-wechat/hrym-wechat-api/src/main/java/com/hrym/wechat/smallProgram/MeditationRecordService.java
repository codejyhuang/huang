package com.hrym.wechat.smallProgram;

import com.hrym.wechat.entity.MeditationSchedule;

import java.util.List;
import java.util.Map;

/**
 * Created by hrym13 on 2018/4/14.
 */
public interface MeditationRecordService {

    /**
     * 所有我加入的共修活动
     *
     * @param param
     * @return
     */
    List findMscheduleByUserId(MeditationScheduleParam param);


    /**
     * 所有我未加入的共修活动
     *
     * @param param
     * @return
     */
    Map<String, Object> findMschedule(MeditationScheduleParam param);

    /**
     * 根据活动ID查详细介绍，《未加入》
     *
     * @param param
     * @return
     */
    MeditationSchedule findMscheduleById(MeditationScheduleParam param);


    MeditationSchedule findMscheduleBySId(MeditationScheduleParam param);

    /**
     * 加入共修活动
     *
     * @param param
     */
    Map<String, Object> insertMeditationRecord(MeditationScheduleParam param);

    /**
     * 参加的共修活动简介《活动结束》
     *
     * @param param
     * @return
     */
    MeditationSchedule findMscheduleByGroupId(MeditationScheduleParam param);

    /**
     * 修改加入共修记录表的置顶状态
     *
     * @param param
     */
    void updateMeditationRecordIsTop(MeditationScheduleParam param);


}
