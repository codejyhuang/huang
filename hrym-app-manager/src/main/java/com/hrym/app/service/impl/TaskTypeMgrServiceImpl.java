package com.hrym.app.service.impl;

import com.github.pagehelper.PageHelper;
import com.hrym.app.dao.TaskTypeMgrMapper;
import com.hrym.app.service.TaskTypeMgrService;
import com.hrym.common.base.BaseConstants;
import com.hrym.common.base.PageInfo;
import com.hrym.rpc.app.config.ESCommon;
import com.hrym.rpc.app.dao.model.task.TaskType;
import com.hrym.rpc.app.util.Result;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scala.util.parsing.combinator.testing.Str;

import java.util.List;

/**
 * Created by hrym13 on 2017/12/6.
 */
@Service
public class TaskTypeMgrServiceImpl implements TaskTypeMgrService{

    @Autowired
    private TaskTypeMgrMapper taskTypeMgrMapper;


    /**
     * 查找功课类型
     * @return
     */
    @Override
    public Result findAllTaskType(Integer page, Integer rows) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

//        分页核心代码
        PageHelper.startPage(page,rows);
        List<TaskType> list = taskTypeMgrMapper.findAllTaskType();

        PageInfo pageInfo = new PageInfo(list);
        long total = pageInfo.getTotal();
        return new Result(code,message,total,list);
    }

    /**
     * 查找功课类型Id
     * @return
     */
    @Override
    public Result findAllTaskTypeId() {
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        List<TaskType> taskTypes = taskTypeMgrMapper.findAllTaskType();

        return new Result(code,message,taskTypes);
    }

    /**
     * 根据ID查找功课类型
     * @param id
     * @return
     */
    @Override
    public TaskType findByIdTaskType(Integer id) {

        TaskType taskType = taskTypeMgrMapper.findByIdTaskType(id);
        return taskType;
    }

    /**
     * 根据功课类型名称检索
     * @param typeName
     * @return
     */
    @Override
    public Result findByTypeNameTaskType(String typeName,Integer page,Integer rows) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        if(StringUtils.isBlank(typeName)){

            return findAllTaskType(page, rows);
        }else {
            PageHelper.startPage(page,rows);
            List<TaskType> list = taskTypeMgrMapper.findByTypeNameTaskType(typeName);
            PageInfo pageInfo = new PageInfo(list);
            long total = pageInfo.getTotal();
            return new Result(code,message,total,list);
        }
    }


    /**
     * 修改功课类型
     * @param taskType
     */
    @Override
    public Result updateByIdTaskType(TaskType taskType) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        taskTypeMgrMapper.updateByIdTaskType(taskType);
        return new Result(code,message,null);
    }

    /**
     *添加功课类型
     * @param taskType
     */
    @Override
    public Result insertByIdTaskType(TaskType taskType) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        taskTypeMgrMapper.insertByIdTaskType(taskType);
        return new Result(code,message,null);
    }

    /**
     * 删除功课类型
     * @param id
     */
    @Override
    public Result deleteByIdTaskType(Integer id) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        if (null !=id) {
            taskTypeMgrMapper.deleteByIdTaskType(id);
        }else {
            code = BaseConstants.GWSCODE2003;
            message = BaseConstants.GWSMSG4003;
        }
        return new Result(code,message,null);
    }


}
