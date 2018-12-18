package com.hrym.app.controller;

import com.hrym.app.service.DiurnalDataMgrService;
import com.hrym.common.base.BaseController;
import com.hrym.rpc.app.common.constant.ManagerParam;
import com.hrym.rpc.app.dao.model.system.DiurnalData;
import com.hrym.rpc.app.dao.model.system.UserData;
import com.hrym.rpc.app.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by hrym13 on 2018/3/9.
 */
@Controller
@RequestMapping(value = "/admin")
public class DiurnalDataMgrController extends BaseController {

    @Autowired
    private DiurnalDataMgrService diurnalDataMgrService;

    /**
     * 根据表名查找对应的内容
     *
     * @param tableName
     * @return
     */
    @RequestMapping("/findAlldiurnalData")
    @ResponseBody
    public ModelAndView findAlldiurnalData(String tableName) {

        //根据表名查找对应的内容
        List<DiurnalData> list = diurnalDataMgrService.findUserData(tableName);
        //查找今日活跃人数
        DiurnalData data = diurnalDataMgrService.findUserCount(tableName);
        int recount = data.getRecount();
        //查找至今注册数
        DiurnalData diurnalData = diurnalDataMgrService.findAllCountCreatedTime(tableName);
        ModelAndView mav = new ModelAndView();
        mav.addObject("tableName", tableName);
        mav.addObject("diurnalData", diurnalData);
        mav.addObject("list", list);
        mav.addObject(recount);
        mav.setViewName("/userData/userData");
        return mav;
    }

    /**
     * 查找所有的表
     *
     * @param param
     * @return
     */
    @RequestMapping("/findUserDataTable")
    @ResponseBody
    public Result findUserDataTable(ManagerParam param) {

        return diurnalDataMgrService.findUserDataTable(param.getPageNumber(), param.getPageSize());
    }


}
