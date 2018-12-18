package com.hrym.rpc.auth.service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import com.hrym.common.base.BaseConstants;
import com.hrym.common.base.BaseResult;
import com.hrym.common.base.PageInfo;
import com.hrym.common.util.DateUtil;
import com.hrym.common.util.NumUtil;
import com.hrym.common.util.RedisUtil;
import com.hrym.rpc.app.common.constant.RecordParam;
import com.hrym.rpc.app.dao.model.task.TaskPlan;
import com.hrym.rpc.app.dao.model.task.UserInfo;
import com.hrym.rpc.auth.api.HistoryRecordService;
import com.hrym.rpc.auth.dao.mapper.TaskPlanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by mj on 2017/12/29.
 */
public class HistoryRecordServiceImpl implements HistoryRecordService {

    @Autowired
    private TaskPlanMapper taskPlanMapper;

    /**
     * 最近完成的功课历史数据列表
     * @return
     */
    @Override
    public BaseResult getRecentRecordList(RecordParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        //Redis中获取用户对象
        UserInfo user = JSON.parseObject(RedisUtil.get(param.getToken()),UserInfo.class);
        //获取用户创建到但前的总天数
        int dayNum = 0;

        List<Map<String,Object>> mapList = new ArrayList<>();
        Map<String,Object> maps = Maps.newHashMap();
        List<TaskPlan> plans = null;
        if (null != user){
            PageHelper.startPage(param.getPageNo(),BaseConstants.PAGE_SIZE);
            plans = taskPlanMapper.findTaskPlanRecordByUuid(user.getUuid());
            for (TaskPlan t : plans){
                Map<String,Object> map = Maps.newHashMap();
                if (10010 == t.getTypeId()){
                    t.setItemId(t.getCustomId());
                    t.setItemName(t.getCustomName());
                }
                map.put("doneNum", NumUtil.formatFloatNumber(t.getDoneNum()));
                map.put("itemId",t.getItemId());
                map.put("typeId",t.getTypeId());
                if (10003 == t.getTypeId()){
                    map.put("itemName","供"+t.getItemName());
                }else {
                    map.put("itemName",t.getItemName());
                }
                map.put("unitDesc",t.getUnitDesc());
                if (null != t.getStartTime() && null !=t.getUpdateTime()){
                    String dateStart = DateUtil.timestampToDates(t.getStartTime(),DateUtil.DATE_PATTON_DEFAULT2);
                    String dateEnd = DateUtil.timestampToDates(t.getUpdateTime(),DateUtil.DATE_PATTON_DEFAULT2);
                    if (dateStart.equals(dateEnd)){
                        map.put("dateStr",dateStart);
                    }else {
                        String dateStr = dateStart+"-"+dateEnd;
                        map.put("dateStr",dateStr);
                    }
                }else if (null != t.getCreateTime() && null !=t.getUpdateTime()){
                    String dateStart = DateUtil.timestampToDates(t.getCreateTime(),DateUtil.DATE_PATTON_DEFAULT2);
                    String dateEnd = DateUtil.timestampToDates(t.getUpdateTime(),DateUtil.DATE_PATTON_DEFAULT2);
                    if (dateStart.equals(dateEnd)){
                        map.put("dateStr",dateStart);
                    }else {
                        String dateStr = dateStart+"-"+dateEnd;
                        map.put("dateStr",dateStr);
                    }
                }
                mapList.add(map);
            }
            try {
                //获取用户创建到但前的总天数
                dayNum = DateUtil.daysBetween(user.getCreatedTime(),DateUtil.currentSecond());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        PageInfo pageInfo = new PageInfo(plans);

        maps.put("list",mapList);
        maps.put("hasNextPage",pageInfo.isHasNextPage());
        maps.put("dayNum",dayNum);
        return new BaseResult(code,message,maps);
    }


    /**
     * 功课历史详情数据
     * @param param
     * @return
     */
    @Override
    public BaseResult getRecordDetail(RecordParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        //Redis中获取用户对象
        UserInfo user = JSON.parseObject(RedisUtil.get(param.getToken()),UserInfo.class);

        List<Map<String,Object>> mapList = new ArrayList<>();
        Map<String,Object> maps = Maps.newHashMap();

        List<TaskPlan> plans = null;
        if (null != user){
            PageHelper.startPage(param.getPageNo(),BaseConstants.PAGE_SIZE);
            plans = taskPlanMapper.findTaskPlanRecordByItemIdByUuid(param.getItemId(),user.getUuid(),param.getTypeId());
            for (TaskPlan t : plans){
                Map<String,Object> map = Maps.newHashMap();
                map.put("num",NumUtil.formatFloatNumber(t.getDoneNum()));
                map.put("unitDesc",t.getUnitDesc());
                if (null != t.getUpdateTime()){
                    map.put("year",DateUtil.timestampToDates(t.getUpdateTime(),DateUtil.DATA_PATTON_YYYYMM3));
                    if (null != t.getStartTime()){
                        String monthStart = DateUtil.timestampToDates(t.getStartTime(),DateUtil.TIME_PATTON_MMdd3);
                        String monthEnd = DateUtil.timestampToDates(t.getUpdateTime(),DateUtil.TIME_PATTON_MMdd3);
                        if (monthStart.equals(monthEnd)){
                            map.put("month",monthStart);
                        }else {
                            String month = monthStart+"-"+monthEnd;
                            map.put("month",month);
                        }
                    }else if (null != t.getCreateTime()){
                        String monthStart = DateUtil.timestampToDates(t.getCreateTime(),DateUtil.TIME_PATTON_MMdd3);
                        String monthEnd = DateUtil.timestampToDates(t.getUpdateTime(),DateUtil.TIME_PATTON_MMdd3);
                        if (monthStart.equals(monthEnd)){
                            map.put("month",monthStart);
                        }else {
                            String month = monthStart+"-"+monthEnd;
                            map.put("month",month);
                        }
                    }
                }
                mapList.add(map);
            }
        }
        PageInfo pageInfo = new PageInfo(plans);
        maps.put("list",mapList);
        maps.put("hasNextPage",pageInfo.isHasNextPage());

        return new BaseResult(code,message,maps);
    }

}
