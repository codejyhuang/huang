package com.hrym.app.controller;

import com.hrym.app.service.MeditationScheduleMgrService;
import com.hrym.rpc.app.common.constant.ManagerParam;
import com.hrym.rpc.app.util.Result;
import com.hrym.rpc.wechat.dao.model.MeditationSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by hrym13 on 2018/5/1.
 */
@Controller
@RequestMapping("/admin")
public class MeditationScheduleMgrController {

    @Autowired
    private MeditationScheduleMgrService scheduleMgrService;

    /**
     * 查找所有的共修活动列表
     *
     * @return
     */
    @RequestMapping("/findAllMedSchedule")
    @ResponseBody
    public Result findAllMedSchedule(Integer meditationTypeId) {

        return scheduleMgrService.findAllMedSchedule(meditationTypeId);
    }

    /**
     * 删除共修内容列表
     *
     * @param id
     * @return
     */
    @RequestMapping("/deleteMedSchedule")
    @ResponseBody
    public Result deleteMedSchedule(Integer id) {

        return scheduleMgrService.deleteMedSchedule(id);
    }

    /**
     * 更新共修列表内容
     *
     * @param meditationSchedule
     * @return
     */
    @RequestMapping("/updateMedSchedule")
    @ResponseBody
    public Result updateMedSchedule(MeditationSchedule meditationSchedule) {

        return scheduleMgrService.updateMedSchedule(meditationSchedule);
    }

    /**
     * 共修列表内容添加
     *
     * @param meditationSchedule
     * @return
     */
    @RequestMapping("/insertMedSchedule")
    @ResponseBody
    public Result insertMedSchedule(MeditationSchedule meditationSchedule) {

        return scheduleMgrService.insertMedSchedule(meditationSchedule);
    }

    /**
     * 根据共修ID查找共修活动
     *
     * @param id
     * @return
     */
    @RequestMapping("/findMedAcheduleById")
    public ModelAndView findMedAcheduleById(Integer id) {

        MeditationSchedule med = scheduleMgrService.findMedAcheduleById(id);
        ModelAndView mav = new ModelAndView();

        mav.addObject("med", med);
        mav.setViewName("/meditationType/editMedSchedule");

        return mav;
    }

    @RequestMapping("/MedAcheduleById")
    @ResponseBody
    public MeditationSchedule MedAcheduleById(Integer id) {

        MeditationSchedule med = scheduleMgrService.findMedAcheduleById(id);

        return med;
    }

}
