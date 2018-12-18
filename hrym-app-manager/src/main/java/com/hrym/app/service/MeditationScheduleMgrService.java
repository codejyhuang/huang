package com.hrym.app.service;

import com.hrym.rpc.app.util.Result;
import com.hrym.rpc.wechat.dao.model.MeditationSchedule;

/**
 * Created by hrym13 on 2018/5/1.
 */
public interface MeditationScheduleMgrService {

    /**
     * 查找所有的共修活动列表
     * @return
     */
    Result findAllMedSchedule(Integer meditationTypeId);

    /**
     * 根据共修ID查找共修活动
     * @param id
     * @return
     */
    MeditationSchedule findMedAcheduleById(Integer id);


    /**
     * 删除共修内容列表
     * @param id
     * @return
     */
    Result deleteMedSchedule(Integer id);

    /**
     * 更新共修列表内容
     * @param meditationSchedule
     * @return
     */
    Result updateMedSchedule(MeditationSchedule meditationSchedule);

    /**
     * 共修列表添加
     * @param meditationSchedule
     * @return
     */
    Result insertMedSchedule(MeditationSchedule meditationSchedule);
}
