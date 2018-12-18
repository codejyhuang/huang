package com.hrym.app.controller;

import com.hrym.app.service.TaskAreaMgrService;
import com.hrym.common.base.BaseController;
import com.hrym.rpc.app.common.constant.ManagerParam;
import com.hrym.rpc.app.dao.model.task.TaskArea;
import com.hrym.rpc.app.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by hrym13 on 2018/1/2.
 */
@Controller
@RequestMapping("/admin")
public class TaskAreaMgrController extends BaseController {

    @Autowired
    private TaskAreaMgrService taskAreaMgrService;
    /**
     * 查找所有的功课专区内容
     * @return
     */
    @RequestMapping("/findAllTaskArea")
    @ResponseBody
    public Result findAllTaskArea(ManagerParam param){
        return taskAreaMgrService.findAllTaskArea(param.getPageNumber(),param.getPageSize());
    }

    /**
     * 添加功课计划
     * @param taskArea
     * @return
     */
    @RequestMapping("/insertTaskArea")
    @ResponseBody
    public Result insertTaskArea(TaskArea taskArea){

        return taskAreaMgrService.insertTaskArea(taskArea);
    }

    /**
     * 删除功课计划内容
     * @param Id
     * @return
     */
    @RequestMapping("/deleteTaskArea")
    @ResponseBody
    public Result deleteTaskArea(Integer Id){

        return taskAreaMgrService.deleteTaskArea(Id);
    }

    /**
     * 更新功课计划内容
     * @param taskArea
     * @return
     */
    @RequestMapping("/updateTaskArea")
    @ResponseBody
    public Result updateTaskArea(TaskArea taskArea){

        return taskAreaMgrService.updateTaskArea(taskArea);
    }

    /**
     * 根据功课计划ID查找功课计划内容
     * @param Id
     * @return
     */
    @RequestMapping("/findTaskAreaById")
    @ResponseBody
    public Result findTaskAreaById(Integer Id){

        return taskAreaMgrService.findTaskAreaById(Id);
    }
}
