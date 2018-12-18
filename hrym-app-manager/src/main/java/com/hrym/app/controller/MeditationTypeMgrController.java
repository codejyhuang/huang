package com.hrym.app.controller;

import com.hrym.app.service.MeditationTypeMgrService;
import com.hrym.common.base.BaseController;
import com.hrym.rpc.app.common.constant.ManagerParam;
import com.hrym.rpc.app.util.Result;
import com.hrym.rpc.wechat.dao.model.MeditationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by hrym13 on 2018/5/1.
 */
@Controller
@RequestMapping("/admin")
public class MeditationTypeMgrController extends BaseController {

    @Autowired
    private MeditationTypeMgrService typeMgrService;

    /**
     * 查找共修类型列表
     *
     * @return
     */
    @RequestMapping("/findAllMeditation")
    @ResponseBody
    public Result findAllMeditation(ManagerParam param) {

        return typeMgrService.findAllMeditation(param.getPageNumber(), param.getPageSize());
    }

    /**
     * 共修活动了类型录入
     *
     * @param type
     */
    @RequestMapping("/insertMeditation")
    @ResponseBody
    public Result insertMeditation(MeditationType type) {

        return typeMgrService.insertMeditation(type);
    }

    /**
     * 共修活动了类型更新
     *
     * @param type
     */
    @RequestMapping("/updateMeditation")
    @ResponseBody
    public Result updateMeditation(MeditationType type) {

        return typeMgrService.updateMeditation(type);
    }

    /**
     * 共修活动了类型删除
     *
     * @param id
     */
    @RequestMapping("/deleteMeditation")
    @ResponseBody
    public Result deleteMeditation(Integer id) {

        return typeMgrService.deleteMeditation(id);
    }

    /**
     * 根据ID查找对应的内容
     *
     * @param id
     * @return
     */
    @RequestMapping("/findMeditationById")
    public ModelAndView findMeditationById(Integer id) {

        MeditationType meditationType = typeMgrService.findMeditationById(id);
        ModelAndView mav = new ModelAndView();
        mav.addObject("type", meditationType);
        mav.setViewName("/meditationType/editMeditationType");
        return mav;
    }

    /**
     * 查找所有的共修类型列表
     *
     * @return
     */
    @RequestMapping("/findAllMeditationType")
    @ResponseBody
    public Result findAllMeditationType() {

        return typeMgrService.findAllMeditationType();
    }

    /**
     * 根据共修类型名称模糊查寻列表
     *
     * @param type
     * @param param
     * @return
     */
    @RequestMapping("/findMeditationByName")
    @ResponseBody
    public Result findMeditationByName(MeditationType type, ManagerParam param) {

        if (null != type.getMeditationTypeName()) {
            //        rcl.getCatalogueName()
            //转码
            String val = null;
            try {
                val = URLDecoder.decode(type.getMeditationTypeName(), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            type.setMeditationTypeName(val);
        }
        return typeMgrService.findMeditationByName(type.getMeditationTypeName(),
                param.getPageNumber(),
                param.getPageSize());
    }

    @RequestMapping("/findAllWechatUserCount")
    @ResponseBody
    public Result findAllWechatUserCount() {

        return typeMgrService.findAllWechatUserCount();
    }

}
