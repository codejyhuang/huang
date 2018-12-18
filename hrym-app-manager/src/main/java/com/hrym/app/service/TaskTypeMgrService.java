package com.hrym.app.service;

import com.hrym.rpc.app.dao.model.task.TaskType;
import com.hrym.rpc.app.util.Result;

/**
 * Created by hrym13 on 2017/12/6.
 */
public interface TaskTypeMgrService {


    /**
     * 查找功课类型
     * @return
     */
    Result findAllTaskType( Integer page, Integer rows);
    /**
     * 查找功课类型ID
     * @return
     */
    Result findAllTaskTypeId();

    /**
     * 根据ID查找功课类型
     * @param typeId
     * @return
     */
    TaskType findByIdTaskType(Integer typeId);

    /**
     * 根据类型名称查找功课类型
     * @param typeName
     * @return
     */
    Result findByTypeNameTaskType(String typeName,Integer page,Integer rows);


    /**
     * 修改功课类型
     * @param taskType
     */
    Result updateByIdTaskType(TaskType taskType);

    /**
     *添加功课类型
     * @param taskType
     */
    Result insertByIdTaskType(TaskType taskType);

    /**
     * 删除功课类型
     * @param typeId
     */
    Result deleteByIdTaskType(Integer typeId);

}
