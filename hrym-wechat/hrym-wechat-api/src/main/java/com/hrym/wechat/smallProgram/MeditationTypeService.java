package com.hrym.wechat.smallProgram;

import com.hrym.wechat.entity.MeditationSchedule;

import java.util.List;
import java.util.Map;

/**
 * Created by hrym13 on 2018/5/25.
 */
public interface MeditationTypeService {

    /**
     * 查找我加入的共修类型
     * @param param
     * @return
     */
    List findMeditationTypeByUserId(MeditationScheduleParam param );

    /**
     * 查找我未加入的共修类型
     * @param param
     * @return
     */
    Map<String,Object> findMeditationTypeIsNoUserId(MeditationScheduleParam param);

    /**
     * 共修类型详情《多版本》
     * @param param
     * @return
     */
    Map<String,Object> findMeditationTypeByTypeId(MeditationScheduleParam param);


    /**
     * 共修类型简介《多版本》
     * @param param
     * @return
     */
    Map<String,Object> meditationTypeIntroller(MeditationScheduleParam param);

    /**
     * 根据共修类型ID查找共修内容
     * @param param
     * @return
     */
    MeditationSchedule findScheduleById(MeditationScheduleParam param);

    /**
     * 共修完成页面
     * @param param
     * @return
     */
    MeditationSchedule finshScheduleById(MeditationScheduleParam param);


    
}
