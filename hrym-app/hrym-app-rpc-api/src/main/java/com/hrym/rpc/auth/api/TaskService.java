package com.hrym.rpc.auth.api;

import com.hrym.common.base.BaseResult;
import com.hrym.rpc.app.common.constant.TaskParam;
import com.hrym.rpc.app.dao.model.task.ShareInfo;

import java.util.Map;

/**
 * Created by mj on 2017/7/17.
 */
public interface TaskService {

    /**
     * 查询功课计划状态
     * @param param
     * @return
     */
    BaseResult findTaskPlanById(TaskParam param);

    /**
     * 删除功课
     * @param param
     * @return
     */
    BaseResult deleteTaskPlan(TaskParam param);

    /**
     * 获取音频版本
     * @param param
     * @return
     */
    BaseResult findMusicVersion(TaskParam param);

    /**
     * 设置计数方式
     * @param param
     * @return
     */
    BaseResult setCountingMethod(TaskParam param);

    /**
     * 设置音频版本
     * @param param
     * @return
     */
    BaseResult setMusicVersion(TaskParam param);

    /**
     * 计数上报
     * @param param
     * @return
     */
    BaseResult saveCount(TaskParam param);

    /**
     * 功课排行榜
     * @param param
     * @return
     */
    BaseResult findTaskRanking(TaskParam param);

    /**
     * 点赞／取消点赞
     * @param param
     * @return
     */
    BaseResult doThumbsUp(TaskParam param);

    /**
     * 禅修计数上报
     * @param param
     * @return
     */
    BaseResult taskStatusCX(TaskParam param);

}
