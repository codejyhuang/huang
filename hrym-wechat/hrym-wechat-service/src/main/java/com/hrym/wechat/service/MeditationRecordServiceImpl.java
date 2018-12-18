package com.hrym.wechat.service;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import com.hrym.common.base.PageInfo;
import com.hrym.common.util.DateUtil;
import com.hrym.common.util.NumUtil;
import com.hrym.wechat.entity.MeditationRecord;
import com.hrym.wechat.entity.MeditationSchedule;
import com.hrym.wechat.entity.MeditationTask;
import com.hrym.wechat.entity.WechatUsers;
import com.hrym.wechat.mapper.*;
import com.hrym.wechat.smallProgram.MeditationRecordService;
import com.hrym.wechat.smallProgram.MeditationScheduleParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

/**
 * Created by hrym13 on 2018/4/14.
 */
@Service
public class MeditationRecordServiceImpl implements MeditationRecordService {

    @Autowired
    private MscheduleMapper mscheduleMapper;
    @Autowired
    private CountRecordMapper countRecordMapper;
    @Autowired
    private WechatUsersMapper wechatUsersMapper;
    @Autowired
    private MeditationRecordMapper meditationRecordMapper;
    @Autowired
    private MeditationTaskMapper taskMapper;


    /**
     * 所有我加入的共修活动
     *
     * @param param
     * @return
     */
    public List findMscheduleByUserId(MeditationScheduleParam param) {


//        PageHelper.startPage()
        WechatUsers wechatUsers = wechatUsersMapper.findUserById(param.getOpenId());

        List<MeditationSchedule> schedules = mscheduleMapper.findMscheduleByUserId(wechatUsers.getUserId());
        List<Map<String, Object>> list = new ArrayList();

        for (MeditationSchedule me : schedules) {
            Map<String, Object> map = new HashMap();
            map.put("activeAuthor", me.getActiveAuthor());
            map.put("activeHeadUrl", me.getActiveHeadUrl());
            map.put("activeTitle", me.getActiveTitle());
            map.put("createdTime", me.getCreatedTime());
            map.put("expectTime", me.getExpectTime());
            map.put("groupStatus", me.getGroupStatus());
            map.put("realNumber", me.getRealNumber());
            map.put("scheduleId", me.getScheduleId());
            map.put("startTime", me.getStartTime());
            map.put("targetNumber", me.getTargetNumber());
            map.put("meditationTypeName", me.getMeditationTypeName());
            MeditationTask task = taskMapper.findMeditationTaskById(wechatUsers.getUserId(), me.getScheduleId());
            if (task.getCount() != null) {
                map.put("dwonNumber", task.getCount() - me.getTargetNumber());
            } else {
                map.put("dwonNumber", 0 - me.getTargetNumber());
            }
            //共修时间倒计时<天>
            try {
                Integer timedown = DateUtil.daysBetween(DateUtil.currentSecond(), me.getExpectTime());
                if (timedown < 0) {
                    map.put("dayDown", 0);
                } else {
                    map.put("dayDown", timedown);
                }
                System.out.println("+++++++++++<<<<<" + timedown);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Integer records = countRecordMapper.finAllCountByDay(wechatUsers.getUserId(), me.getScheduleId());
            if (null != records) {
                map.put("todayNumber", "今天已经报数" + records + "遍");
            } else {
                map.put("todayNumber", "今天还没报数");
            }
            list.add(map);
        }

        return list;
    }

    /**
     * 未加入的共修活动列表
     *
     * @param param
     * @return
     */
    public Map<String, Object> findMschedule(MeditationScheduleParam param) {

        WechatUsers wechatUsers = wechatUsersMapper.findUserById(param.getOpenId());

        //核心分页
        PageHelper.startPage(param.getPageNumber(), 5);
        List<MeditationSchedule> schedules = mscheduleMapper.findMschedule(wechatUsers.getUserId());

//        List<Map<String,Object>> list = new ArrayList();

        for (MeditationSchedule sc : schedules) {

            //简介
            sc.setScheduleIntro(sc.getScheduleIntro());
            //参加人数
            if (sc.getUserNumber() == null) {
                sc.setUserNumber(0);
            } else {
                sc.setUserNumber(sc.getUserNumber());
            }
            if (null != sc.getStartTime()) {
                String time = DateUtil.timestampToDates(sc.getStartTime(), DateUtil.TIME_PATTON_MMdd);
                sc.setStartTimes(time);
            }
            if (null != sc.getExpectTime()) {
                String time = DateUtil.timestampToDates(sc.getExpectTime(), DateUtil.TIME_PATTON_MMdd);
                sc.setExpectTimes(time);
            }
            //完成遍数与百分比
            if (sc.getRealNumber() != null) {
                sc.setRealNumber(sc.getRealNumber());
                String percent = NumUtil.getPercent(sc.getRealNumber(), sc.getTargetNumber());
                sc.setPercent(percent.split("\\%")[0]);
                System.out.println(percent);
            } else {
                sc.setRealNumber(0);
                sc.setPercent("0");
            }
        }
        PageInfo pageInfo = new PageInfo(schedules);
        long totalPage = pageInfo.getTotal();
        Map<String, Object> maps = Maps.newHashMap();
        maps.put("schedules", schedules);
        maps.put("totalPage", pageInfo.getPages());
//        schedules.add(totalPage);
        return maps;
    }


    /**
     * 根据活动ID查详细介绍，《未加入》
     *
     * @param param
     * @return
     */
    @Override
    public MeditationSchedule findMscheduleById(MeditationScheduleParam param) {

        MeditationSchedule schedule = mscheduleMapper.findMscheduleById(param);

        schedule.setRealNumber(schedule.getRealNumber());
        //完成遍数与百分比
        if (schedule.getRealNumber() != null) {
            schedule.setRealNumber(schedule.getRealNumber());
            String percent = NumUtil.getPercent(schedule.getRealNumber(), schedule.getTargetNumber());
            schedule.setPercent(percent.split("\\%")[0]);
            System.out.println(percent);
        } else {
            schedule.setRealNumber(0);
            schedule.setPercent("0");
        }
        //共修时间倒计时<天>
        try {
            Integer timedown = DateUtil.daysBetween(DateUtil.currentSecond(), schedule.getExpectTime());

            if (timedown < 0) {
                schedule.setDayDown(0);
            } else {
                schedule.setDayDown(timedown);
            }
            System.out.println("+++++++++++<<<<<" + timedown);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return schedule;
    }

    /**
     * 以加入共修活动简介《活动未结束》
     *
     * @param param
     * @return
     */
    @Override
    public MeditationSchedule findMscheduleBySId(MeditationScheduleParam param) {


        WechatUsers wechatUsers = wechatUsersMapper.findUserById(param.getOpenId());

        //根据用户ID和共修ID判断是否有记录
        MeditationSchedule schedule = mscheduleMapper.findScheduleBySIdUId(wechatUsers.getUserId(), param.getScheduleId());
        if (null != schedule) {
            MeditationSchedule meditationSchedule = mscheduleMapper.findMscheduleBySId(wechatUsers.getUserId(), param.getScheduleId());

            //共修活动名称
            meditationSchedule.setUserId(wechatUsers.getUserId());
            meditationSchedule.setActiveTitle(meditationSchedule.getActiveTitle());
            //参加人数
            if (null == meditationSchedule.getUserNumber()) {
                meditationSchedule.setUserNumber(0);
            } else {
                meditationSchedule.setUserNumber(meditationSchedule.getUserNumber());
            }

            //到计时
            try {
                Integer timedown = DateUtil.daysBetween(DateUtil.currentSecond(), meditationSchedule.getExpectTime());
                if (timedown < 0) {
                    meditationSchedule.setDayDown(0);
                } else {
                    meditationSchedule.setDayDown(timedown);
                }
                System.out.println("+++++++++++<<<<<" + timedown);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //完成遍数与百分比
            if (meditationSchedule.getRealNumber() != null) {
                meditationSchedule.setRealNumber(meditationSchedule.getRealNumber());
                String percent = NumUtil.getPercent(meditationSchedule.getRealNumber(), meditationSchedule.getTargetNumber());
                meditationSchedule.setPercent(percent.split("\\%")[0]);
                System.out.println(percent);
            } else {
                meditationSchedule.setRealNumber(0);
                meditationSchedule.setPercent("0");
            }

            //今日报数
            if (null == meditationSchedule.getTodayNumber()) {

                meditationSchedule.setTodayNumber(0);
            } else {

                meditationSchedule.setTodayNumber(meditationSchedule.getTodayNumber());
            }

            return meditationSchedule;
        } else {
            MeditationSchedule meditationSchedule = new MeditationSchedule();
            meditationSchedule.setExist(0);
            return meditationSchedule;
        }
    }

    /**
     * 修改后的加入共修活动《多版本》
     *
     * @param param
     */
    @Override
    public Map<String, Object> insertMeditationRecord(MeditationScheduleParam param) {

        //根据openid查找userid
        WechatUsers wechatUsers = wechatUsersMapper.findUserById(param.getOpenId());

        Map<String, Object> map = new HashMap<>();

        if (param.getMeditationTypeId() != null) {

            MeditationRecord meditationRecord = meditationRecordMapper.findMeditationRecord(wechatUsers.getUserId(), param.getMeditationTypeId());
            MeditationRecord record = new MeditationRecord();

            if (null == meditationRecord) {

                record.setCreatedTime(DateUtil.currentSecond());
                record.setMeditationTypeId(param.getMeditationTypeId());
                record.setUserId(wechatUsers.getUserId());
                record.setIsTop(0);
                meditationRecordMapper.insertMeditationTypeRecord(record);
                map.put("status", 1);

            } else {
                //已添加
                map.put("status", 0);
            }
        } else {
            map.put("status", 0);
        }


        return map;
    }

    /**
     * 参加的共修活动简介《活动结束是否完成目标》
     *
     * @param param
     * @return
     */
    @Override
    public MeditationSchedule findMscheduleByGroupId(MeditationScheduleParam param) {

        //根据openid查找userid
        WechatUsers wechatUsers = wechatUsersMapper.findUserById(param.getOpenId());
        System.out.println("ScheduleId" + param.getScheduleId());
        MeditationSchedule meditationSchedule = mscheduleMapper.findMscheduleByGroupId(wechatUsers.getUserId(), param.getScheduleId());

        //共修的目标数
        meditationSchedule.setTargetNumber(meditationSchedule.getTargetNumber());
        meditationSchedule.setUserId(wechatUsers.getUserId());

        if (null != meditationSchedule.getRealNumber()) {
            //总共完成多少遍
            meditationSchedule.setRealNumber(meditationSchedule.getRealNumber());
            //是否完成目标的标志1:完成；
            if (meditationSchedule.getRealNumber() >= meditationSchedule.getTargetNumber()) {

                meditationSchedule.setStatus(1);
            } else {

                meditationSchedule.setStatus(0);
            }
        } else {

            meditationSchedule.setStatus(0);
            //总共完成多少遍
            meditationSchedule.setRealNumber(0);
        }
        //参加本活动人数
        meditationSchedule.setUserNumber(meditationSchedule.getUserNumber());
        //我完成了多少遍
        if (meditationSchedule.getMyNumber() == null) {

            meditationSchedule.setMyNumber(0);
        } else {

            meditationSchedule.setMyNumber(meditationSchedule.getMyNumber());
        }
        //坚持了多少天
        if (meditationSchedule.getMyDay() != null) {
            //坚持了多少天
            try {
                Integer timedown = DateUtil.daysBetween(meditationSchedule.getMyDay(), meditationSchedule.getExpectTime());
                if (timedown < 0) {
                    meditationSchedule.setDayDown(0);
                } else {
                    meditationSchedule.setDayDown(timedown);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return meditationSchedule;
    }


    /**
     * 修改加入共修记录表的置顶状态
     *
     * @param param
     */
    @Override
    public void updateMeditationRecordIsTop(MeditationScheduleParam param) {

        //根据openid查找userid
        WechatUsers wechatUsers = wechatUsersMapper.findUserById(param.getOpenId());

        MeditationRecord med = new MeditationRecord();
        if (param.getIsTop() != null && param.getIsTop() !=0) {

            med.setIsTop(DateUtil.currentSecond());

        }else {
            med.setIsTop(0);
        }
        med.setUserId(wechatUsers.getUserId());
        med.setMeditationTypeId(param.getMeditationTypeId());
        meditationRecordMapper.updateMedRecordIsTop(med);
    }



}
