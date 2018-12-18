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
import com.hrym.rpc.app.dao.model.task.UserInfo;
import com.hrym.rpc.auth.api.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by mj on 2017/7/17.
 */
@Controller
@RequestMapping(value = "/hrym/api")
public class TaskController extends BaseController {

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/task")
    @ResponseBody
    public BaseResult taskAdd(@RequestBody TaskParam param){

        String cmd = param.getCmd();
        if ("deleteTask".equals(cmd)){
            return doDeleteTask(param);
        }
        if ("taskStatus".equals(cmd)){
            return doTaskStatus(param);
        }
        if ("findMusicVersion".equals(cmd)){
            return doFindMusicVersion(param);
        }
        if ("setCountingMethod".equals(cmd)){
            return doSetCountingMethod(param);
        }
        if ("setMusicVersion".equals(cmd)){
            return doSetMusicVersion(param);
        }
        if ("saveCount".equals(cmd)){
            return doSaveCount(param);
        }
        if ("taskRanking".equals(cmd)){
            return doTaskRanking(param);
        }
        if ("dianzan".equals(cmd)){
            return doThumbsUp(param);
        }
        if ("taskStatusCX".equals(cmd)) {
            return doTaskStatusCX(param);
        }
        return new BaseResult(BaseConstants.GWSCODE4007,BaseConstants.GWSMSG4007,null);
    }

    /**
     * 删除功课
     * @param param
     * @return
     */
    public BaseResult doDeleteTask(TaskParam param){

        return taskService.deleteTaskPlan(param);
    }

    /**
     * 功课状态
     * @param param
     * @return
     */
    public BaseResult doTaskStatus(TaskParam param){

        BaseResult ret = taskService.findTaskPlanById(param);

        return ret;
    }

    /**
     * 获取音频版本
     * @param param
     * @return
     */
    public BaseResult doFindMusicVersion(TaskParam param){

        return taskService.findMusicVersion(param);
    }

    /**
     * 设置计数方式
     * @param param
     * @return
     */
    public BaseResult doSetCountingMethod(TaskParam param){

        return taskService.setCountingMethod(param);
    }

    /**
     * 设置音频版本
     * @param param
     * @return
     */
    public BaseResult doSetMusicVersion(TaskParam param){

        return taskService.setMusicVersion(param);
    }

    /**
     * 计数上报
     * @param param
     * @return
     */
    public BaseResult doSaveCount(TaskParam param){

        //Redis中获取用户对象
        UserInfo user = JSON.parseObject(RedisUtil.get(param.getToken()),UserInfo.class);

        BaseResult ret = taskService.saveCount(param);
        Object o = ret.getData();
        if (null != o){
            LogBean bean = new LogBean();
            bean.setUuid(user.getUuid());
            bean.setGroup(GwsLoggerTypeEnum.TASK_END.getType());
            bean.setGroupValue(Integer.valueOf(String.valueOf(((Map)o).get("itemId"))));
            bean.setGroupValueDesc(String.valueOf(((Map)o).get("itemName")));
            Integer typeId = Integer.valueOf(String.valueOf(((Map)o).get("typeId")));
            bean.setType(typeId);
            bean.setTypeDesc(String.valueOf(((Map)o).get("typeName")));
            if (typeId != 10007 && 0 == Integer.valueOf(String.valueOf(((Map)o).get("status")))){
                bean.setContent("功课完结");
            }
            if (1 ==  param.getType()){
                bean.setContent2("自动报数");
            }else if (0 == param.getType()){
                bean.setContent2("手动报数");
            }else {
                bean.setContent2("蓝牙报数");
            }
            bean.setNum((long)param.getNum());
            //功课完结
            GwsLogger.info("计数上报",bean);
            //做功课
            bean.setGroup(GwsLoggerTypeEnum.DO_TASK.getType());
            GwsLogger.info("计数上报",bean);
        }

        return ret;
    }

    /**
     * 功课排行榜
     * @return
     */
    public BaseResult doTaskRanking(TaskParam param){

        return taskService.findTaskRanking(param);
    }

    /**
     * 点赞／取消点赞
     * @param param
     * @return
     */
    public BaseResult doThumbsUp(TaskParam param){

        return taskService.doThumbsUp(param);
    }

    /**
     * 功课状态禅修
     * @param param
     * @return
     */
    public BaseResult doTaskStatusCX(TaskParam param){

        return taskService.taskStatusCX(param);
    }

}
