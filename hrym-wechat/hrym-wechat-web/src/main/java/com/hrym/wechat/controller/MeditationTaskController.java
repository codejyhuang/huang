package com.hrym.wechat.controller;

import com.hrym.common.annotation.Allowed;
import com.hrym.common.base.BaseConstants;
import com.hrym.common.base.BaseController;
import com.hrym.common.base.BaseResult;
import com.hrym.wechat.entity.MeditationSchedule;
import com.hrym.wechat.smallProgram.MeditationScheduleParam;
import com.hrym.wechat.smallProgram.MeditationTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by hrym13 on 2018/4/20.
 */
@Controller
@RequestMapping(value = "/hrym/program")
public class MeditationTaskController extends BaseController {

    @Autowired
    private MeditationTaskService meditationTaskService;

    @RequestMapping(value = "/MeditationTask")
    @Allowed
    @ResponseBody
    public BaseResult MeditationTask(@RequestBody MeditationScheduleParam param){

        String cmd = param.getCmd();
        if ("findMeditationTask".equals(cmd)){

            if (null ==param.getOpenGId() || param.getOpenGId().equals("")){
                //个人排行
                return findMeditationTask(param);
            }else {
                //群排行
                return findMeditationTaskByMId(param);
            }
        }
        if ("findMysort".equals(cmd)){

            if (null ==param.getOpenGId() || param.getOpenGId().equals("")){
                //个人排行我的排名
                return findMysort(param);
            }else {
                //群排行我的排名
                return findMeditonById(param);
            }
        }
        if ("findMeditationScheduleName".equals(cmd)){
            return findMeditationScheduleName(param);
        }

        return new BaseResult(BaseConstants.GWSCODE4007,BaseConstants.GWSMSG4007,null);
    }

    /**
     * 个人排行榜
     * @param param
     * @return
     */
    public BaseResult findMeditationTask(MeditationScheduleParam param){

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        Map<String, Object> list = meditationTaskService.findMeditationTask(param);
        return new BaseResult(code,message,list);

    }

    /**
     * 个人我的排名
     * @param param
     * @return
     */
    public BaseResult findMysort(MeditationScheduleParam param){

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        Map<String, Object> map = meditationTaskService.findMysort(param);
        return new BaseResult(code,message,map);

    }

    public BaseResult findMeditationScheduleName(MeditationScheduleParam param){

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        MeditationSchedule med = meditationTaskService.findMeditationScheduleName(param);
        return new BaseResult(code,message,med);
    }


    /**
     * 群排行榜我的排名
     * @param param
     * @return
     */
    public BaseResult findMeditonById(MeditationScheduleParam param){

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        Map<String, Object> map = meditationTaskService.findMeditonById(param);
        return new BaseResult(code,message,map);

    }


    /**
     * 群排行榜
     * @param param
     * @return
     */
    public BaseResult findMeditationTaskByMId(MeditationScheduleParam param){

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        Map<String, Object> list = meditationTaskService.findMeditationTaskByMId(param);
        return new BaseResult(code,message,list);

    }

}
