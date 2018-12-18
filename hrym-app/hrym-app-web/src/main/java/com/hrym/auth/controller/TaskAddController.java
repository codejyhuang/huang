package com.hrym.auth.controller;

import com.alibaba.fastjson.JSON;
import com.hrym.common.annotation.Allowed;
import com.hrym.common.base.BaseConstants;
import com.hrym.common.base.BaseController;
import com.hrym.common.base.BaseResult;
import com.hrym.common.logUtil.GwsLogger;
import com.hrym.common.logUtil.GwsLoggerTypeEnum;
import com.hrym.common.logUtil.LogBean;
import com.hrym.common.util.RedisUtil;
import com.hrym.rpc.app.common.constant.TaskParam;
import com.hrym.rpc.app.dao.model.task.TaskItem;
import com.hrym.rpc.app.dao.model.task.TaskType;
import com.hrym.rpc.app.dao.model.task.UserInfo;
import com.hrym.rpc.auth.api.TaskAddService;
import com.hrym.rpc.auth.dao.mapper.TaskItemMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 功课添加控制层
 * Created by mj on 2017/7/7.
 */
@Controller
@RequestMapping(value = "/hrym/api")
public class TaskAddController extends BaseController{

    private static final Log log = LogFactory.getLog(TaskAddController.class);
    @Autowired
    private TaskAddService taskAddService;

    @RequestMapping(value = "/taskAdd")
    @ResponseBody
    public BaseResult taskAdd(@RequestBody TaskParam param){

        String cmd = param.getCmd();
        if ("findTask".equals(cmd)){
            return doFindTasks(param);
        }
        if ("findTaskType".equals(cmd)){
            return doFindTaskTypes();
        }
        if ("taskTypeIntro".equals(cmd)){
            return doTaskTypeIntro(param);
        }
        if ("addTaskPlan".equals(cmd)){
            return doAddTaskPlan(param);
        }
        if ("search".equals(cmd)){
            return doSearch(param);
        }
        if ("reSetRemainCron".equals(cmd)){
            return doReSetRemainCron(param);
        }
        if ("findItemVersion".equals(cmd)){
            return doFindItemVersion(param);
        }
        if ("isAdd".equals(cmd)){
            return isAddTask(param);
        }
        if ("getTaskArea".equals(cmd)) {
            return getTaskArea(param);
        }
        if ("areaSearch".equals(cmd)){
            return taskAreaSearch(param);
        }
        if ("editRecentAdd".equals(cmd)){
            return editRecentAdd(param);
        }

        return new BaseResult(BaseConstants.GWSCODE4007,BaseConstants.GWSMSG4007,null);
    }

    @RequestMapping(value = "/taskAddDetail")
    @Allowed
    @ResponseBody
    public BaseResult doTaskAdd(@RequestBody TaskParam param){

        String cmd = param.getCmd();
        if ("taskInfo".equals(cmd)){
            return doTaskInfo(param);
        }
        if ("getGongfoMusic".equals(cmd)){
            return getGongFoMusic(param);
        }

        return new BaseResult(BaseConstants.GWSCODE4007,BaseConstants.GWSMSG4007,null);
    }

    /**
     * 我的功课主页
     * @return
     */
    public BaseResult doFindTasks(TaskParam param){

        return taskAddService.findTasks(param);
    }

    /**
     * 功课类型列表
     * @return
     */
    public BaseResult doFindTaskTypes(){

        return taskAddService.findTaskTypes();
    }

    /**
     * 功课类型介绍
     * @param param
     * @return
     */
    public BaseResult doTaskTypeIntro(TaskParam param){

        //Redis中获取用户对象
        UserInfo user = JSON.parseObject(RedisUtil.get(param.getToken()),UserInfo.class);
        BaseResult ret = taskAddService.taskTypeIntro(param);

        LogBean bean = new LogBean();
        bean.setUuid(user.getUuid());
        bean.setGroup(GwsLoggerTypeEnum.TASK_CLICK.getType());
        bean.setType(param.getTypeId());
        bean.setTypeDesc(((TaskType)ret.getData()).getTypeName());
        bean.setContent(((TaskType)ret.getData()).getTypeName());
        GwsLogger.info("功课类型详情",bean);

        return ret;
    }

    /**
     * 功课内容详细信息
     * @param param
     * @return
     */
    public BaseResult doTaskInfo(TaskParam param){

        BaseResult ret = taskAddService.findTaskInfoById(param);
        return ret;
    }


    /**
     * 添加功课
     * @param param
     * @return
     */
    public BaseResult doAddTaskPlan(TaskParam param){

        BaseResult ret = taskAddService.addTaskPlan(param);
        //Redis中获取用户对象
        UserInfo user = JSON.parseObject(RedisUtil.get(param.getToken()),UserInfo.class);

        LogBean bean = new LogBean();
        bean.setUuid(user.getUuid());
        bean.setGroup(GwsLoggerTypeEnum.TASK_CREATE.getType());
        bean.setGroupValue(param.getItemId());
        bean.setGroupValueDesc(String.valueOf(((Map<String,Object>)ret.getData()).get("itemName")));
        bean.setType(param.getTypeId());
        bean.setTypeDesc(String.valueOf(((Map<String,Object>)ret.getData()).get("typeName")));
        if (null != param.getPlanTarget()){
            bean.setNum(param.getPlanTarget());
        }
        if (null != param.getPlanPeriod()){
            bean.setNum2(param.getPlanPeriod());
        }

        GwsLogger.info("添加功课",bean);

        return ret;
    }

    /**
     * 基于类型的模糊搜索
     * @param param
     * @return
     */
    public BaseResult doSearch(TaskParam param){

        return taskAddService.findTaskItemById(param);
    }

    /**
     * 设置提醒时间表达式
     * @param param
     * @return
     */
    public BaseResult doReSetRemainCron(TaskParam param){

        return taskAddService.updateRemainCron(param);
    }

    /**
     * 功课版本
     * @param param
     * @return
     */
    public BaseResult doFindItemVersion(TaskParam param){

        return taskAddService.findItemVersion(param);

    }

    /**
     * 判断是否添加功课
     * @param param
     * @return
     */
    public BaseResult isAddTask(TaskParam param) {

        return taskAddService.isAddTask(param);
    }

    /**
     * 获取供佛音频
     * @return
     */
    public BaseResult getGongFoMusic(TaskParam param) {

        return taskAddService.getGongFoMusic(param);
    }

    /**
     * 添加功课专区主页
     * @param param
     * @return
     */
    public BaseResult getTaskArea(TaskParam param) {

        return taskAddService.getTaskArea(param);
    }

    /**
     * 添加功课专区搜索
     * @param param
     * @return
     */
    public BaseResult taskAreaSearch(TaskParam param) {
        return taskAddService.taskAreaSearch(param);
    }

    /**
     * 修改功课最近添加状态
     * @param param
     * @return
     */
    public BaseResult editRecentAdd(TaskParam param) {
        return taskAddService.editRecentAdd(param);
    }
}
