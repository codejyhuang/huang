package com.hrym.app.service;

import com.hrym.rpc.app.dao.model.task.TaskArea;
import com.hrym.rpc.app.util.Result;

/**
 * Created by hrym13 on 2018/1/2.
 */
public interface TaskAreaMgrService {
    /**
     * 查找所有的功课专区内容
     * @return
     */
    Result findAllTaskArea(Integer page,Integer rows);

    /**
     * 添加功课计划
     * @param taskArea
     * @return
     */
    Result insertTaskArea(TaskArea taskArea);

    /**
     * 更新功课计划内容
     * @param taskArea
     * @return
     */
    Result updateTaskArea(TaskArea taskArea);

    /**
     * 删除功课计划
     * @param Id
     * @return
     */
    Result deleteTaskArea(Integer Id);

    /**
     * 根据功课计划ID查找功课计划内容
     * @param Id
     * @return
     */
    Result findTaskAreaById(Integer Id);
}
