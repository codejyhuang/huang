package com.hrym.wechat.controller;

import com.hrym.common.annotation.Allowed;
import com.hrym.common.base.BaseConstants;
import com.hrym.common.base.BaseController;
import com.hrym.common.base.BaseResult;
import com.hrym.wechat.entity.MeditationSchedule;
import com.hrym.wechat.smallProgram.MeditationScheduleParam;
import com.hrym.wechat.smallProgram.MeditationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by hrym13 on 2018/5/25.
 */
@Controller
@RequestMapping(value = "/hrym/program")

public class MeditationTypeController extends BaseController {

    @Autowired
    private MeditationTypeService meditationTypeService;

    @RequestMapping(value = "/MeditationType")
    @Allowed
    @ResponseBody
    public BaseResult MeditationType(@RequestBody MeditationScheduleParam param){
        String cmd = param.getCmd();
        if ("findMeditationTypeByUserId".equals(cmd)){

            return findMeditationTypeByUserId(param);
        }
        if ("findMeditationTypeIsNoUserId".equals(cmd)){
            return findMeditationTypeIsNoUserId(param);
        }
        if ("findMeditationTypeByTypeId".equals(cmd)){
            return findMeditationTypeByTypeId(param);
        }
        if ("findScheduleById".equals(cmd)){
            return findScheduleById(param);
        }
        if ("meditationTypeIntroller".equals(cmd)){
            return MeditationTypeIntroller(param);
        }
        if ("finshScheduleById".equals(cmd)){
            return finshScheduleById(param);
        }
        return new BaseResult(BaseConstants.GWSCODE4007,BaseConstants.GWSMSG4007,null);
    }

    /**
     * 查找我加入的共修类型内容
     * @param param
     * @return
     */
    public BaseResult findMeditationTypeByUserId (MeditationScheduleParam param){

        String code= BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        List<MeditationSchedule> list=meditationTypeService.findMeditationTypeByUserId(param);
        return new BaseResult(code,message,list);
    }

    /**
     * 查找我未加入的共修活动
     * @param param
     * @return
     */
    public BaseResult findMeditationTypeIsNoUserId(MeditationScheduleParam param){

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        Map<String, Object> schedules = meditationTypeService.findMeditationTypeIsNoUserId(param);

        return new BaseResult(code,message,schedules);
    }

    /**
     * 共修内容简介
     * @param param
     * @return
     */
    public BaseResult findMeditationTypeByTypeId(MeditationScheduleParam param){

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        Map<String, Object> type = meditationTypeService.findMeditationTypeByTypeId(param);

        return new BaseResult(code,message,type);
    }

    /**
     * 共修内容详情页
     * @param param
     * @return
     */
    public BaseResult MeditationTypeIntroller(MeditationScheduleParam param){

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        Map<String, Object> type = meditationTypeService.meditationTypeIntroller(param);

        return new BaseResult(code,message,type);
    }

    /**
     * 根据共修类型查找共修内容
     * @param param
     * @return
     */
    public BaseResult findScheduleById(MeditationScheduleParam param){

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        MeditationSchedule med = meditationTypeService.findScheduleById(param);
        return new BaseResult(code,message,med);
    }


    /**
     * 共修完成页面
     * @param param
     * @return
     */
    public BaseResult finshScheduleById(MeditationScheduleParam param){

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        MeditationSchedule schedule = meditationTypeService.finshScheduleById(param);

        return new BaseResult(code,message,schedule);
    }

}
