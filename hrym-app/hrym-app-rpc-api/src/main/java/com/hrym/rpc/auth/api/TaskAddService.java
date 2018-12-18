package com.hrym.rpc.auth.api;

import com.hrym.common.base.BaseResult;
import com.hrym.rpc.app.common.constant.TaskParam;

import java.util.Map;

/**
 * 功课添加接口
 * Created by mj on 2017/7/6.
 */
public interface TaskAddService {

    /**
     * 我的功课主页
     * @param
     * @return
     */
    BaseResult findTasks(TaskParam param);

    /**
     * 功课类型列表
     * @param
     * @return
     */
    BaseResult findTaskTypes();

    /**
     * 功课类型介绍
     * @param param
     * @return
     */
    BaseResult taskTypeIntro(TaskParam param);

    /**
     * 功课内容详情
     * @param param
     * @return
     */
    BaseResult findTaskInfoById(TaskParam param);

    /**
     * 添加功课
     * @param param
     * @return
     */
    BaseResult addTaskPlan(TaskParam param);

    /**
     * 基于类型的功课模糊查询
     * @param param
     * @return
     */
    BaseResult findTaskItemById(TaskParam param);

    /**
     * 设置提醒时间表达式
     * @param param
     * @return
     */
    BaseResult updateRemainCron(TaskParam param);

    /**
     * 功课版本
     * @param param
     * @return
     */
    BaseResult findItemVersion(TaskParam param);

    /**
     * 判断是否添加功课
     * @param param
     * @return
     */
    BaseResult isAddTask(TaskParam param);

    /**
     * 获取供佛音频
     * @return
     */
    BaseResult getGongFoMusic(TaskParam param);

    /**
     * 添加功课专区列表
     * @param param
     * @return
     */
    BaseResult getTaskArea(TaskParam param);

    /**
     * 添加功课专区搜索
     * @param param
     * @return
     */
    BaseResult taskAreaSearch(TaskParam param);

    /**
     * 修改功课最近添加状态
     * @param param
     * @return
     */
    BaseResult editRecentAdd(TaskParam param);
}

