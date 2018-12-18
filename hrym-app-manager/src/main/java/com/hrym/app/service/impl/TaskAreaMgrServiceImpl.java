package com.hrym.app.service.impl;

import com.github.pagehelper.PageHelper;
import com.hrym.app.dao.TaskAreaMgrMapper;
import com.hrym.app.service.TaskAreaMgrService;
import com.hrym.common.base.BaseConstants;
import com.hrym.common.base.PageInfo;
import com.hrym.common.enums.TaskAreaEnum;
import com.hrym.common.util.DateUtil;
import com.hrym.rpc.app.dao.model.task.TaskArea;
import com.hrym.rpc.app.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hrym13 on 2018/1/2.
 */
@Service
public class TaskAreaMgrServiceImpl implements TaskAreaMgrService {

    @Autowired
    private TaskAreaMgrMapper taskAreaMgrMapper;
    /**
     * 查找所有的功课专区内容
     * @return
     */
    @Override
    public Result findAllTaskArea(Integer page, Integer rows) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        PageHelper.startPage(page,rows);
        List<TaskArea> taskAreas = taskAreaMgrMapper.findAllTaskArea();

        List<Map<String,Object>> list = new ArrayList<>();

        for (TaskArea t: taskAreas){

            Map<String,Object> map = new HashMap<>();
            map.put("createTime", DateUtil.timestampToDates(t.getCreateTime(),DateUtil.TIME_PATTON_DEFAULT));
            map.put("itemId",t.getItemId());
            map.put("typeId",t.getTypeId());
            map.put("areaId",t.getAreaId());
            map.put("areaName",t.getAreaName());
            map.put("areaTypeStr",TaskAreaEnum.getEnumByNumber(t.getAreaType()).getDesc());
            map.put("taskPeriod",t.getTaskPeriod());
            map.put("taskTarget",t.getTaskTarget());
            map.put("updateTime",DateUtil.timestampToDates(t.getUpdateTime(),DateUtil.TIME_PATTON_DEFAULT));
            map.put("version",t.getVersion());
            map.put("versionName",t.getVersionName());

            list.add(map);
        }

        PageInfo pageInfo = new PageInfo(taskAreas);
        long total = pageInfo.getTotal();

        return new Result(code,message,total,list);
    }

    /**
     * 添加功课计划
     * @param taskArea
     * @return
     */
    @Override
    public Result insertTaskArea(TaskArea taskArea) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        if (null !=taskArea){
            taskArea.setCreateTime(DateUtil.currentSecond());
            taskArea.setUpdateTime(DateUtil.currentSecond());
            if (null==taskArea.getTypeId()){
                return new Result("1","请选择功课类型",null);
            }else {
                taskAreaMgrMapper.insert(taskArea);
                return new Result(code,message,null);

            }
        }else {
            code= BaseConstants.GWSCODE3001;
            message = BaseConstants.GWSMSG3001;

            return new Result(code,message,null);
        }
    }

    /**
     * 更新功课计划内容
     * @param taskArea
     * @return
     */
    @Override
    public Result updateTaskArea(TaskArea taskArea) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        if (null != taskArea.getAreaId()){
            taskArea.setUpdateTime(DateUtil.currentSecond());
            taskAreaMgrMapper.updateByPrimaryKeySelective(taskArea);
            return new Result("1",message,null);

        }else {
            code= BaseConstants.GWSCODE3001;
            message = BaseConstants.GWSMSG3001;
            return new Result(code,message,null);

        }
    }

    /**
     * 删除功课计划内容
     * @param Id
     * @return
     */
    @Override
    public Result deleteTaskArea(Integer Id) {
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        if (null !=Id){
            taskAreaMgrMapper.deleteByPrimaryKey(Id);
            return new Result(code,message,null);

        }else {
            code= BaseConstants.GWSCODE3001;
            message = BaseConstants.GWSMSG3001;
            return new Result(code,message,null);

        }
    }

    /**
     * 根据功课计划ID查找功课计划内容
     * @param Id
     * @return
     */
    @Override
    public Result findTaskAreaById(Integer Id) {
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        if (null != Id){
            TaskArea taskArea = taskAreaMgrMapper.findTaskAreaById(Id);
            return new Result(code,message,taskArea);

        }else {
            code= BaseConstants.GWSCODE3001;
            message = BaseConstants.GWSMSG3001;
            return new Result(code,message,null);

        }
    }


}
