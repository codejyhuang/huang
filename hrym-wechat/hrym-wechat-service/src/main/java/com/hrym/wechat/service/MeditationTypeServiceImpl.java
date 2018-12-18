package com.hrym.wechat.service;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import com.hrym.common.base.PageInfo;
import com.hrym.common.util.DateUtil;
import com.hrym.common.util.NumUtil;
import com.hrym.wechat.entity.MeditationRecord;
import com.hrym.wechat.entity.MeditationSchedule;
import com.hrym.wechat.entity.MeditationType;
import com.hrym.wechat.entity.WechatUsers;
import com.hrym.wechat.mapper.MeditationRecordMapper;
import com.hrym.wechat.mapper.MeditationTypeMapper;
import com.hrym.wechat.mapper.MscheduleMapper;
import com.hrym.wechat.mapper.WechatUsersMapper;
import com.hrym.wechat.smallProgram.MeditationScheduleParam;
import com.hrym.wechat.smallProgram.MeditationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

/**
 * Created by hrym13 on 2018/5/25.
 */
@Service
public class MeditationTypeServiceImpl implements MeditationTypeService {

    @Autowired
    private MeditationTypeMapper meditationTypeMapper;
    @Autowired
    private MscheduleMapper mscheduleMapper;
    @Autowired
    private WechatUsersMapper wechatUsersMapper;
    @Autowired
    private MeditationRecordMapper meditationRecordMapper;

    /**
     * 查找我加入的共修类型内容
     *
     * @param param
     * @return
     */
    @Override
    public List findMeditationTypeByUserId(MeditationScheduleParam param) {

        // 根据openid查找userId
        WechatUsers wechatUsers = wechatUsersMapper.findUserById(param.getOpenId());

        List<MeditationType> scheduleList = meditationTypeMapper.findMeditationTypeByUserId(wechatUsers.getUserId());

        List<Map<String, Object>> list = new ArrayList();
        for (MeditationType schedule : scheduleList) {

            Map<String, Object> map = new HashMap<>();
            List<MeditationSchedule> schedules = mscheduleMapper.findMeditationScheduleByTypeId(schedule.getMeditationTypeId());
            map.put("schedules", schedules);
//            map.put("isTop", schedule.getIsTop());
            // 共修头像
            map.put("activeHeadUrl", schedule.getActiveHeadUrl());
            // 共修类型名称
            map.put("meditationTypeName", schedule.getMeditationTypeName());
            // 共修类型ID
            map.put("meditationTypeId", schedule.getMeditationTypeId());
            // 共修类型版本
            map.put("meditationTypeVersion", schedule.getMeditationTypeVersion());
            // 共修单版本今日报数
            if (schedule.getMeditationTypeVersion() == 0) {

                if (schedule.getTodayNumber() != null) {

                    map.put("todayNumber", "今天已经报数" + schedule.getTodayNumber() + "遍");

                } else {
                    map.put("todayNumber", "今天还没报数");

                }
            } else {
                // 共修多版本今日报数
                if (schedule.getTodayNumber() != null) {

                    map.put("todayNumber", "今天已经报数");

                } else {
                    map.put("todayNumber", "今天还没报数");

                }
            }
//            if (schedule.getMeditationTypeVersion() == 0) {
            if (schedule.getExpectTime() != null && schedule.getExpectTime() != 0) {
                try {
                    Integer timedown = DateUtil.daysBetween(DateUtil.currentSecond(), schedule.getExpectTime());

                    if (timedown < 0) {
                        map.put("dayDown", timedown);
                        MeditationRecord med = new MeditationRecord();
                        med.setIsTop(0);
                        med.setUserId(wechatUsers.getUserId());
                        med.setMeditationTypeId(schedule.getMeditationTypeId());
                        meditationRecordMapper.updateMedRecordIsTop(med);
                        map.put("isTop", 0);

                    } else {
                        map.put("dayDown", timedown);
                        map.put("isTop", schedule.getIsTop());

                    }

                } catch (ParseException e) {
                    e.printStackTrace();

                }
            } else {
                map.put("dayDown", 1);
                map.put("isTop", schedule.getIsTop());
            }
            map.put("expectTime", schedule.getExpectTime());
            // 共修时间倒计时<天>
//            }
            list.add(map);
        }
        return list;
    }

    /**
     * 查找我未加入的共修类型
     *
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> findMeditationTypeIsNoUserId(MeditationScheduleParam param) {

        WechatUsers wechatUsers = wechatUsersMapper.findUserById(param.getOpenId());
        PageHelper.startPage(param.getPageNumber(), 10);
        List<MeditationType> schedules = meditationTypeMapper.findMeditationTypeIsNoUserId(wechatUsers.getUserId());

        Integer num = 0;
        for (MeditationType schedule : schedules) {

            // 共修类型名称
            schedule.setMeditationTypeName(schedule.getMeditationTypeName());

            if (null != schedule.getStartTime() && null != schedule.getExpectTime()) {
                // 判断是不是同一年
                boolean isSameDate = DateUtil.isSameDate(schedule.getStartTime(), schedule.getExpectTime());
                if (isSameDate == true) {
                    // 结束时间
                    String time = DateUtil.timestampToDates(schedule.getExpectTime(), DateUtil.TIME_PATTON_MMdd1);
                    schedule.setExpectTimes(time);

                } else {
                    // 结束时间
                    String time = DateUtil.timestampToDates(schedule.getExpectTime(), DateUtil.DATA_PATTON_YYYYMMDD2);
                    schedule.setExpectTimes(time);

                }
            }
            //共修时间倒计时<天>
            if (schedule.getExpectTime() != null && schedule.getExpectTime() != 0) {
                Integer timedown = null;
                try {
                    timedown = DateUtil.daysBetween(DateUtil.currentSecond(), schedule.getExpectTime());
                    if (timedown == 0) {
                        schedule.setDayDown(1);
                    } else {
                        schedule.setDayDown(timedown);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                schedule.setDayDown(1);
            }

            if (schedule.getDayDown() > 0) {
                num = num + 1;
            }
            schedule.setExpectTime(schedule.getExpectTime());
            // 结束时间
            if (null != schedule.getStartTime()) {
                String time = DateUtil.timestampToDates(schedule.getStartTime(), DateUtil.TIME_PATTON_MMdd1);
                schedule.setStartTimes(time);

            }
            // 参加人数
            schedule.setUserNumber(schedule.getUserNumber());
            // 版本区别
            schedule.setMeditationTypeVersion(schedule.getMeditationTypeVersion());
            // 共修类型简介
            schedule.setMeditationTypeIntro(schedule.getMeditationTypeIntro());
            // 共修内容详情
            List<MeditationSchedule> scheduleList = mscheduleMapper.findMeditationScheduleByTypeId(schedule.getMeditationTypeId());
            schedule.setMedList(scheduleList);
            for (MeditationSchedule schedule1 : scheduleList) {
                // 刷新周期
                schedule1.setRefreshTime(schedule1.getRefreshTime());
                // 共修内容名称
                schedule1.setActiveTitle(schedule1.getActiveTitle());

                if (schedule1.getTargetNumber() != 0) {
                    schedule1.setTargetNumber(schedule1.getTargetNumber());
                    // 完成遍数与百分比
                    if (schedule1.getRealNumber() != null) {
                        schedule1.setRealNumber(schedule1.getRealNumber());
                        String percent = NumUtil.getPercent(schedule1.getRealNumber(), schedule1.getTargetNumber());
                        schedule1.setPercent(percent.split("\\%")[0]);

                    } else {
                        schedule1.setRealNumber(0);
                        schedule1.setPercent("0");
                    }
                } else {
                    schedule1.setTargetNumber(0);
                    if (schedule1.getRealNumber() != null) {

                        schedule1.setRealNumber(schedule1.getRealNumber());
                    } else {
                        schedule1.setRealNumber(0);
                    }
                    schedule1.setPercent("0");
                }
            }

        }
        PageInfo pageInfo = new PageInfo(schedules);
        Map<String, Object> maps = Maps.newHashMap();
        maps.put("num", num);
        maps.put("schedules", schedules);
        maps.put("totalPage", pageInfo.getPages());
        return maps;
    }

    /**
     * 共修类型详情页detals《多版本》
     *
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> findMeditationTypeByTypeId(MeditationScheduleParam param) {

        // 根据openID查找用户userId
        WechatUsers wechatUsers = wechatUsersMapper.findUserById(param.getOpenId());

        // 根据用户ID和共修类型ID判断是否有记录
        MeditationRecord meditationRecord = meditationRecordMapper.findMeditationRecord(wechatUsers.getUserId(), param.getMeditationTypeId());
        if (meditationRecord != null) {
            // 查找共修类型详情
            MeditationType type = meditationTypeMapper.findMeditationTypeByTypeId(param.getMeditationTypeId(), wechatUsers.getUserId());
            // 判断此共修是否已删除
            if (type ==null){
                Map<String, Object> map = new HashMap<>();
                map.put("exist", 2);
                return map;
            }
            if (type.getIsExit() == 2) {
                Map<String, Object> map = new HashMap<>();
                map.put("exist", 2);
                return map;
            }

            if (meditationRecord.getIsTop() != null) {
                type.setIsTop(meditationRecord.getIsTop());
            } else {
                type.setIsTop(0);
            }
            // 共修类型ID
            type.setMeditationTypeId(type.getMeditationTypeId());

            if (type.getTodayNumber() != null) {
                // 今日报数
                type.setTodayNumber(type.getTodayNumber());

            } else {
                // 今日报数
                type.setTodayNumber(0);

            }
            // 共修倒计时
            if (type.getExpectTime() != null) {
                try {
                    int dayDown = DateUtil.daysBetween(DateUtil.currentSecond(), type.getExpectTime());
                    type.setDayDown(dayDown);

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                type.setDayDown(1);
            }
            type.setExpectTime(type.getExpectTime());
            // 共修类型名称
            type.setMeditationTypeName(type.getMeditationTypeName());
            // 共修类型简介
            type.setMeditationTypeIntro(type.getMeditationTypeIntro());
            // 共修类型版本
            type.setMeditationTypeVersion(type.getMeditationTypeVersion());
            // 根据共修类型查找具体内容
            List<MeditationSchedule> list = mscheduleMapper.findScheduleByTypeId(type.getMeditationTypeId(), wechatUsers.getUserId());
            for (MeditationSchedule schedule : list) {

                // 共修内容名称
                schedule.setActiveTitle(schedule.getActiveTitle());
                // 完成的总遍数
                schedule.setMyNumber(schedule.getMyNumber());
                // 刷新时间
                schedule.setRefreshTime(schedule.getRefreshTime());
                // 共修内容类型
                schedule.setScheduleType(schedule.getScheduleType());
                // 单版本进度条
                if (type.getMeditationTypeVersion() == 0) {
                    // 完成遍数与百分比
                    if (schedule.getTargetNumber() != 0) {
//                    if (schedule.getTargetNumber() != null && schedule.getTargetNumber() != 0) {
                        // 目标
                        schedule.setTargetNumber(schedule.getTargetNumber());
                        if (schedule.getRealNumber() != null && schedule.getRealNumber() != 0) {
                            schedule.setRealNumber(schedule.getRealNumber());
                            String percent = NumUtil.getPercent(schedule.getRealNumber(), schedule.getTargetNumber());
                            schedule.setPercent(percent.split("\\%")[0]);
                        } else {
                            schedule.setRealNumber(0);
                            schedule.setPercent("0");
                        }

                    } else {
                        // 目标
                        schedule.setTargetNumber(0);
                        if (schedule.getRealNumber() != null && schedule.getRealNumber() != 0) {

                            schedule.setRealNumber(schedule.getRealNumber());
                            String percent = NumUtil.getPercent(schedule.getRealNumber(), schedule.getTargetNumber());
                            schedule.setPercent(percent.split("\\%")[0]);
                        } else {
                            schedule.setRealNumber(0);
                            schedule.setPercent("0");
                        }
                        schedule.setPercent("0");

                    }
                    // 多版本进度条
                } else if (type.getMeditationTypeVersion() == 1) {
                    // 完成遍数与百分比
                    if (schedule.getTargetNumber() != 0) {
//                    if (schedule.getTargetNumber() != null && schedule.getTargetNumber() != 0) {
                        // 目标
                        schedule.setTargetNumber(schedule.getTargetNumber());
                        if (schedule.getMyNumber() != null && schedule.getMyNumber() != 0) {

                            schedule.setRealNumber(schedule.getMyNumber());
                            String percent = NumUtil.getPercent(schedule.getMyNumber(), schedule.getTargetNumber());
                            schedule.setPercent(percent.split("\\%")[0]);
                        } else {
                            schedule.setRealNumber(0);
                            schedule.setPercent("0");
                        }

                    } else {
                        // 目标
                        schedule.setTargetNumber(0);
                        if (schedule.getMyNumber() != null && schedule.getMyNumber() != 0) {

                            schedule.setRealNumber(schedule.getMyNumber());
                            String percent = NumUtil.getPercent(schedule.getMyNumber(), schedule.getTargetNumber());
                            schedule.setPercent(percent.split("\\%")[0]);
                        } else {
                            schedule.setRealNumber(0);
                            schedule.setPercent("0");
                        }
                        schedule.setPercent("0");

                    }
                }
            }
            Map<String, Object> map = new HashMap<>();
            map.put("meditationType", type);
            map.put("scheduleList", list);
            map.put("exist", 1);
            return map;

        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("exist", 0);
            return map;

        }
    }

    /**
     * 共修类型简介页《含多版本》
     *
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> meditationTypeIntroller(MeditationScheduleParam param) {

        // 根据open ID查找用户userId
        WechatUsers wechatUsers = wechatUsersMapper.findUserById(param.getOpenId());

        // 查找共修类型详情
        MeditationType type = meditationTypeMapper.findMeditationTypeByTypeId(param.getMeditationTypeId(), wechatUsers.getUserId());
        // 共修类型ID
        type.setMeditationTypeId(type.getMeditationTypeId());
        // 今日报数
        type.setTodayNumber(type.getTodayNumber());
        // 共修类型名称
        type.setMeditationTypeName(type.getMeditationTypeName());
        // 共修类型简介
        type.setMeditationTypeIntro(type.getMeditationTypeIntro());
        // 共修类型版本
        type.setMeditationTypeVersion(type.getMeditationTypeVersion());
        type.setExpectTime(type.getExpectTime());
        // 共修倒计时
        if (type.getExpectTime() != null) {
            try {
                int dayDown = DateUtil.daysBetween(DateUtil.currentSecond(), type.getExpectTime());
                type.setDayDown(dayDown);

            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            type.setDayDown(1);
        }
        // 根据共修类型查找具体内容
        List<MeditationSchedule> list = mscheduleMapper.findScheduleByTypeId(type.getMeditationTypeId(), wechatUsers.getUserId());
        for (MeditationSchedule schedule : list) {

            // 共修内容名称
            schedule.setActiveTitle(schedule.getActiveTitle());
            // 完成的总遍数
            schedule.setMyNumber(schedule.getMyNumber());
            // 个人目标
            schedule.setTargetNumber(schedule.getTargetNumber());
            // 刷新时间
            schedule.setRefreshTime(schedule.getRefreshTime());
            // 共修内容类型
            schedule.setScheduleType(schedule.getScheduleType());
            // 单版本进度条
            if (type.getMeditationTypeVersion() ==0){
                // 判断是否有目标数
                if (schedule.getTargetNumber() != 0) {
                    // 完成遍数与百分比
                    if (schedule.getRealNumber() != null && schedule.getRealNumber() != 0) {
                        schedule.setRealNumber(schedule.getRealNumber());
                        String percent = NumUtil.getPercent(schedule.getRealNumber(), schedule.getTargetNumber());
                        schedule.setPercent(percent.split("\\%")[0]);

                    } else {
                        schedule.setRealNumber(0);
                        schedule.setPercent("0");

                    }
                } else {
                    if (schedule.getRealNumber() != null && schedule.getRealNumber() != 0) {
                        schedule.setRealNumber(schedule.getRealNumber());
                        schedule.setPercent("0");

                    } else {
                        schedule.setRealNumber(0);
                        schedule.setPercent("0");

                    }
                }
                // 多版本进度条
            }else {
                // 判断是否有目标数
                if (schedule.getTargetNumber() != 0) {
                    // 完成遍数与百分比
                    if (schedule.getMyNumber() != null && schedule.getMyNumber() != 0) {
                        schedule.setRealNumber(schedule.getMyNumber());
                        String percent = NumUtil.getPercent(schedule.getMyNumber(), schedule.getTargetNumber());
                        schedule.setPercent(percent.split("\\%")[0]);

                    } else {
                        schedule.setRealNumber(0);
                        schedule.setPercent("0");

                    }
                } else {
                    if (schedule.getMyNumber() != null && schedule.getMyNumber() != 0) {
                        schedule.setRealNumber(schedule.getMyNumber());
                        schedule.setPercent("0");

                    } else {
                        schedule.setRealNumber(0);
                        schedule.setPercent("0");

                    }
                }
            }

        }
        Map<String, Object> map = new HashMap<>();
        map.put("meditationType", type);
        map.put("scheduleList", list);
        map.put("exist", 1);

        return map;

    }

    /**
     * 根据共修类型ID查找共修内容
     *
     * @param param
     * @return
     */
    @Override
    public MeditationSchedule findScheduleById(MeditationScheduleParam param) {

        MeditationSchedule med = mscheduleMapper.findScheduleById(param.getMeditationTypeId());
        return med;
    }


    /**
     * 单版本共修完成页面
     *
     * @param param
     * @return
     */
    @Override
    public MeditationSchedule finshScheduleById(MeditationScheduleParam param) {

        // 根据open ID查找用户userId
        WechatUsers wechatUsers = wechatUsersMapper.findUserById(param.getOpenId());

        MeditationSchedule schedule = mscheduleMapper.finshSchedule(wechatUsers.getUserId(), param.getMeditationTypeId());
        if (schedule != null) {
            if (schedule.getUserNumber() != null) {
                // 参与人数
                schedule.setUserNumber(schedule.getUserNumber());
            } else {
                // 参与人数
                schedule.setUserNumber(0);
            }

            // 我坚持了多少天
            try {
                if (null != schedule.getCreatedTime()) {

                    Integer day = DateUtil.daysBetween(schedule.getCreatedTime(), DateUtil.currentSecond());
                    schedule.setMyDay(day);
                    schedule.setDayDown(day);
                }

            } catch (ParseException e) {
                e.printStackTrace();

            }
            // 我完成的数
            if (schedule.getMyNumber() == null){
                schedule.setMyNumber(0);
            }else {
                schedule.setMyNumber(schedule.getMyNumber());
            }

            if (0==schedule.getTargetNumber()) {

                if (null != schedule.getRealNumber()) {
                    // 总共完成数
                    schedule.setRealNumber(schedule.getRealNumber());

                    if (schedule.getRealNumber() - schedule.getTargetNumber() < 0) {
                        // 未完成
                        schedule.setStatus(2);
                    } else {
                        // 完成
                        schedule.setStatus(1);
                    }

                } else {
                    schedule.setRealNumber(0);
                    schedule.setStatus(2);

                }
            } else {
                schedule.setTargetNumber(0);
                schedule.setRealNumber(schedule.getRealNumber());
                schedule.setStatus(1);
            }
        }

        return schedule;
    }


    public static void main(String[] args) {

        String percent = NumUtil.getPercent(23, 0);
        System.out.println(percent);
    }


}
