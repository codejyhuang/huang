package com.hrym.app.controller;

import com.hrym.app.service.FdfsService;
import com.hrym.app.service.TaskTypeMgrService;
import com.hrym.common.base.BaseController;
import com.hrym.rpc.app.common.constant.ManagerParam;
import com.hrym.rpc.app.dao.model.task.TaskType;
import com.hrym.rpc.app.util.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.net.URLDecoder;

/**
 * Created by hrym13 on 2017/12/6.
 */
@Controller
@RequestMapping("/admin")
public class TaskTypeMgrController extends BaseController {

    @Autowired
    private TaskTypeMgrService taskTypeMgrService;

    /**
     * 查找功课类型
     * @return
     */
    @RequestMapping("/findAllTaskType")
    @ResponseBody
    public Result findAllTaskType(ManagerParam param){

        return taskTypeMgrService.findAllTaskType(param.getPageNumber(),param.getPageSize());

    }

    /**
     * 查找功课类型Id
     * @return
     */
    @RequestMapping("/findAllTaskTypeId")
    @ResponseBody
    public Result findAllTaskTypeId(){

        return taskTypeMgrService.findAllTaskTypeId();
    }

    /**
     * 根据功课类型名称搜索
     * @param typeName
     * @param param
     * @return
     * @throws Exception
     */
    @RequestMapping("/findByTypeNameTaskType")
    @ResponseBody
    public Result findByTypeNameTaskType(String typeName, ManagerParam param) throws Exception{

//        字符转码

        if (StringUtils.isNotBlank(typeName)){
            //转码
            String val = URLDecoder.decode(typeName, "UTF-8");
            typeName=val;
        }
        return taskTypeMgrService.findByTypeNameTaskType(typeName,param.getPageNumber(),param.getPageSize());
    }

    /**
     * 根据ID查找功课类型
     * @param typeId
     * @return
     */
    @RequestMapping("/findByIdTaskType")
    public ModelAndView findByIdTaskType(Integer typeId){
       TaskType taskType = taskTypeMgrService.findByIdTaskType(typeId);
       ModelAndView mav = new ModelAndView();
       mav.addObject("taskList",taskType);
       mav.setViewName("/taskType/editTaskType");
       return mav;
    }

    /**
     * 修改功课类型
     * @param taskType
     */
    @RequestMapping("/updateByIdTaskType")
    @ResponseBody
    public Result updateByIdTaskType(TaskType taskType){

       return taskTypeMgrService.updateByIdTaskType(taskType);

    }

    /**
     *添加功课类型
     * @param taskType
     */
    @RequestMapping("/insertByIdTaskType")
    @ResponseBody
    public Result insertByIdTaskType(TaskType taskType){

        return taskTypeMgrService.insertByIdTaskType(taskType);
    }
    /**
     * 删除功课类型
     * @param typeId
     */
    @RequestMapping("/deleteByIdTaskType")
    @ResponseBody
    public Result deleteByIdTaskType(Integer typeId){

        return taskTypeMgrService.deleteByIdTaskType(typeId);
    }


}
