package com.hrym.wechat.smallProgram;

import com.hrym.wechat.entity.MeditationSchedule;

import java.util.Map;

/**
 * Created by hrym13 on 2018/4/20.
 */
public interface MeditationTaskService {


    /**
     * 个人排行榜
     * @param param
     * @return
     */
    Map<String, Object> findMeditationTask(MeditationScheduleParam param);


    /**
     * 个人我的排名
     * @param param
     * @return
     */
    Map<String, Object> findMysort(MeditationScheduleParam param);

    /**
     * 查找多版本排行的共修名称
     * @param param
     * @return
     */
   MeditationSchedule findMeditationScheduleName(MeditationScheduleParam param);

    /**
     * 群排行榜
     * @param param
     * @return
     */
    Map<String, Object> findMeditationTaskByMId(MeditationScheduleParam param);

    /**
     * 群排行榜我的排名
     * @param param
     * @return
     */
    Map<String,Object> findMeditonById(MeditationScheduleParam param);
}
