package com.hrym.wechat.controller;

import com.hrym.common.annotation.Allowed;
import com.hrym.common.base.BaseConstants;
import com.hrym.common.base.BaseController;
import com.hrym.common.base.BaseResult;
import com.hrym.wechat.entity.MeditationSchedule;
import com.hrym.wechat.smallProgram.MeditationRecordService;
import com.hrym.wechat.smallProgram.MeditationScheduleParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by hrym13 on 2018/4/15.
 */
@Controller
@RequestMapping(value = "/hrym/program")
public class MeditationRecordController extends BaseController {

    @Autowired
    private MeditationRecordService meditationRecordService;


    @RequestMapping(value = "/MeditationSchedule",method = RequestMethod.POST)
    @Allowed
    @ResponseBody
    public BaseResult MeditationSchedule(@RequestBody MeditationScheduleParam param){

        String cmd = param.getCmd();
        //所有我加入的共修活动
        if ("findMscheduleByUserId".equals(cmd)){
            return findMscheduleByUserId(param);
        }
        //所有我未加入的共修活动
        if ("findMschedule".equals(cmd)){
            return findMschedule(param);
        }
        //未加入共修简介
        if ("findMscheduleById".equals(cmd)){
            return findMscheduleById(param);
        }
        //加入共修活动
        if ("insertMeditationRecord".equals(cmd)){
            return insertMeditationRecord(param);
        }
        //已加入的共修活动简介《活动未结束》
        if ("findMscheduleBySId".equals(cmd)){
            return findMscheduleBySId(param);
        }
        // 参加的的共修活动简介《活动结束》
        if ("findMscheduleByGroupId".equals(cmd)){
            return findMscheduleByGroupId(param);
        }
        // 改变置顶状态
        if ("updateIsTop".equals(cmd)) {
            return updateMeditationRecordIsTop(param);
        }
        return new BaseResult(BaseConstants.GWSCODE4007,BaseConstants.GWSMSG4007,null);
    }

    /**
     * 所有我加入的共修活动
     * @param param
     * @return
     */
    public BaseResult findMscheduleByUserId(MeditationScheduleParam param){

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        List<MeditationScheduleParam> list = meditationRecordService.findMscheduleByUserId(param);

        return new BaseResult(code,message,list);
    }

    /**
     * 所有我未加入的共修活动
     * @param param
     * @return
     */
    public BaseResult findMschedule(MeditationScheduleParam param){

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        Map<String, Object> list = meditationRecordService.findMschedule(param);
        return new BaseResult(code,message,list);
    }

    /**
     * 未加入共修简介
     * @param param
     * @return
     */
    public BaseResult findMscheduleById(MeditationScheduleParam param){

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        MeditationSchedule schedule = meditationRecordService.findMscheduleById(param);

        return new BaseResult(code,message,schedule);
    }

    /**
     * 已加入的共修活动简介《活动未结束》
     * @param param
     * @return
     */
    public BaseResult findMscheduleBySId(MeditationScheduleParam param){

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        MeditationSchedule schedule = meditationRecordService.findMscheduleBySId(param);

        return new BaseResult(code,message,schedule);
    }

    /**
     * 参加的的共修活动简介《活动结束》
     * @param param
     * @return
     */
    public BaseResult findMscheduleByGroupId(MeditationScheduleParam param){

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        MeditationSchedule schedule = meditationRecordService.findMscheduleByGroupId(param);

        return new BaseResult(code,message,schedule);
    }

    /**
     * 加入共修活动
     * @param param
     * @return
     */
    public BaseResult insertMeditationRecord(MeditationScheduleParam param){

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        meditationRecordService.insertMeditationRecord(param);
        return new BaseResult(code,message,null);
    }

    /**
     * 修改置顶状态
     * @param param
     * @return
     */
    public BaseResult updateMeditationRecordIsTop(MeditationScheduleParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        meditationRecordService.updateMeditationRecordIsTop(param);

        return new BaseResult(code,message,null);
    }
}
