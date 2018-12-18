package com.hrym.wechat.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hrym.common.util.DateUtil;
import com.hrym.wechat.entity.CountRecord;
import com.hrym.wechat.entity.MeditationSchedule;
import com.hrym.wechat.entity.MeditationTask;
import com.hrym.wechat.entity.WechatUsers;
import com.hrym.wechat.mapper.CountRecordMapper;
import com.hrym.wechat.mapper.MeditationTaskMapper;
import com.hrym.wechat.mapper.MscheduleMapper;
import com.hrym.wechat.mapper.WechatUsersMapper;
import com.hrym.wechat.smallProgram.CountRecordParam;
import com.hrym.wechat.smallProgram.CountRecordService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hrym13 on 2018/4/17.
 */
@Service
public class CountRecordServiceImpl implements CountRecordService {

    @Autowired
    private CountRecordMapper recordMapper;
    @Autowired
    private WechatUsersMapper wechatUsersMapper;
    @Autowired
    private MeditationTaskMapper meditationTaskMapper;
    @Autowired
    private MscheduleMapper mscheduleMapper;


//    public static void main(String[] args) {
//        //判断当前日期是否大于截止日期
//        //json字符串转str
//        JSONObject jsStr = JSONObject.parseObject("{\"1\":\"34\",\"3\":\"\"}"); //将字符串{“id”：1}
//
//        for(String key:jsStr.keySet()) {
//
//            if (StringUtils.isNotBlank(jsStr.getString(key)) && !jsStr.getString(key).equals("[]") ) {
//                System.out.println("jsStr.getString(key)======="+jsStr.getString(key));
//
//            } else {
//                System.out.println("jsStr"+jsStr.getString(key));
//            }
//        }
//
//    }

    /**
     * 上报数录入
     *
     * @param param
     */
    @Override
    public void insertAllCount(CountRecordParam param) {
        if (null != param.getOpenId()) {
            //根据openid查找userid
            WechatUsers wechatUsers = wechatUsersMapper.findUserById(param.getOpenId());

            //str转json字符串
            JSONObject jsStr = JSONObject.parseObject(param.getStringList()); //将字符串{“id”：1}

            //遍历获得key 和value
            for (String key : jsStr.keySet()) {

                if (StringUtils.isNotBlank(jsStr.getString(key)) && !jsStr.getString(key).equals("[]")) {

                    CountRecord record = new CountRecord();

                    //用户ID
                    record.setUserId(wechatUsers.getUserId());
                    //上报时间
                    record.setCreatedTime(DateUtil.currentSecond());

                    if (StringUtils.isNotBlank(jsStr.getString(key))) {
                        if (jsStr.getString(key).length() == 7) {
                            //切割字符串
                            if (jsStr.getString(key).substring(2, 5).equals("已完成")) {
                                //发本预习是否完成
                                record.setLaw(jsStr.getString(key).substring(2, 5));
                            } else {
                                record.setCount(Integer.valueOf(jsStr.getString(key)));
                            }

                        } else {
                            String a = jsStr.getString(key);

                            if (a.equals("[]")) {
                                System.out.println("================");
                            } else {
                                //共修报数数字
                                record.setCount(Integer.valueOf(jsStr.getString(key)));
                            }
                        }
                    }

                    //共修报数内容ID
                    record.setScheduleId(Integer.valueOf(key));
                    //共修类型
                    record.setMeditationTypeId(param.getMeditationTypeId());

                    if (record.getLaw() != null) {

                        MeditationSchedule med = mscheduleMapper.findMeditationScheduleName(record.getScheduleId());
                        // 时间格式化并在法本预习截止时间基础上减去刷新周期
                        String lawTimeBefore = DateUtil.getBeforeDateFormat(
                                DateUtil.timestampToDates(med.getLawTime(), DateUtil.DATA_PATTON_YYYYMMDD), med.getRefreshTime());
                        //预习截止时间后一天
                        String lawTimeAfter = DateUtil.getAfterDateFormat(
                                DateUtil.timestampToDates(med.getLawTime(), DateUtil.DATA_PATTON_YYYYMMDD), med.getRefreshTime());

                        //时间格式化
                        Integer lawTime = null;
                        Integer timeAfter = null;
                        if (StringUtils.isNotBlank(lawTimeBefore)) {
                            lawTime = DateUtil.getDateToLinuxTime(lawTimeBefore, DateUtil.DATE_PATTON_DEFAULT);
                            System.out.println(lawTime + "lawTime====");
                        }
                        if (StringUtils.isNotBlank(lawTimeAfter)) {
                            timeAfter = DateUtil.getDateToLinuxTime(lawTimeAfter, DateUtil.DATE_PATTON_DEFAULT);
                            System.out.println(lawTime + "lawTime====");
                        }
                        //查看本周期是否已经预习
                        List<CountRecord> recordlist = recordMapper.findCountRecordLaw(lawTime, timeAfter, record.getScheduleId(), wechatUsers.getUserId());
                        if (recordlist.size() == 0) {
                            record.setLawTime(DateUtil.currentSecond());
                            recordMapper.insertCount(record);
                        }

                    } else {

                        recordMapper.insertCount(record);
                    }

                    //查看此共修任务是否已存在
                    MeditationTask meditationTask = meditationTaskMapper.findMeditationTaskById(wechatUsers.getUserId(), record.getScheduleId());

                    if (meditationTask != null) {
                        MeditationTask task = new MeditationTask();
                        if (record.getCount() == null) {

                            task.setCount(0);
                        } else {

                            task.setCount(record.getCount());
                        }
                        meditationTask.setMeditationTypeId(param.getMeditationTypeId());
                        task.setUpdateTime(DateUtil.currentSecond());
                        task.setId(meditationTask.getId());

                        meditationTaskMapper.updateMeditationTask(task);

                    } else {
                        MeditationTask meditationTask1 = new MeditationTask();

                        if (record.getCount() == null) {

                            meditationTask1.setCount(0);
                        } else {

                            meditationTask1.setCount(record.getCount());
                        }
                        meditationTask1.setMeditationTypeId(param.getMeditationTypeId());
                        meditationTask1.setUpdateTime(DateUtil.currentSecond());
                        meditationTask1.setScheduleId(record.getScheduleId());
                        meditationTask1.setUserId(wechatUsers.getUserId());
                        meditationTask1.setCreatedTime(DateUtil.currentSecond());
                        meditationTaskMapper.insertMeditationTask(meditationTask1);
                    }
                }
            }
        }
    }

    /**
     * 多版本计数报数页面展示
     *
     * @param param
     * @return
     */
    @Override
    public List<Map<String, Object>> findScheduleTypeId(CountRecordParam param) {

        List<MeditationSchedule> schedules = mscheduleMapper.findScheduleTypeId(param.getMeditationTypeId());
        //根据openid查找userid
        WechatUsers wechatUsers = wechatUsersMapper.findUserById(param.getOpenId());

        List<Map<String, Object>> list = new ArrayList<>();
        for (MeditationSchedule schedule : schedules) {
            Map<String, Object> map = new HashMap<>();

            //今日报数
            Integer toDayNumber = recordMapper.finAllCountByDay(wechatUsers.getUserId(), schedule.getScheduleId());
            if (toDayNumber != null) {
                map.put("toDayNumber", toDayNumber);
            } else {
                map.put("toDayNumber", 0);
            }
            map.put("scheduleId", schedule.getScheduleId());
            map.put("meditationTypeId", schedule.getMeditationTypeId());
            //共修内容名称
            map.put("activeTitle", schedule.getActiveTitle());
            //共修报数类型还是法本预习
            map.put("scheduleType", schedule.getScheduleType());
            //刷新时间
            map.put("refreshTime", schedule.getRefreshTime());


            //判断当前日期是否大于预习开始日期《判断预习时间假周期大于今日》
            try {
                Integer timeDown = DateUtil.daysBetween(DateUtil.currentSecond(), schedule.getLawTime());
                System.out.println(timeDown);
                if (timeDown < 0) {

                    if (schedule.getScheduleType() == 1) {
                        if (schedule.getLawTime() == null) {
                            schedule.setLawTime(DateUtil.currentSecond());
                        }
                        //法本预习截止时间基础上加上刷新周期
                        String lawTimeAfter = DateUtil.getAfterDateFormat(DateUtil.timestampToDates(schedule.getLawTime(), DateUtil.DATA_PATTON_YYYYMMDD), schedule.getRefreshTime());
                        //法本预习截止时间基础上减去刷新周期
                        String lawTimeBefore = DateUtil.getBeforeDateFormat(
                                DateUtil.timestampToDates(schedule.getLawTime(), DateUtil.DATA_PATTON_YYYYMMDD), schedule.getRefreshTime());
                        map.put("lawTime", lawTimeAfter);
                        //时间格式化
                        if (StringUtils.isNotBlank(lawTimeAfter)) {
                            int lawTime = DateUtil.getDateToLinuxTime(lawTimeAfter, DateUtil.DATE_PATTON_DEFAULT);
                            schedule.setLawTime(lawTime);

                        }
                        if (StringUtils.isNotBlank(lawTimeBefore)) {
                            int LawTimeBefore = DateUtil.getDateToLinuxTime(lawTimeBefore, DateUtil.DATE_PATTON_DEFAULT);
                            schedule.setLawTimeBefore(LawTimeBefore);
                            //根据ID更新共修类型的预习截止时间
                            mscheduleMapper.updateMeditationSchedule(schedule.getLawTime(), schedule.getScheduleId());
                            System.out.println(schedule.getLawTime());
                        }
                    }


                    //查看本周期是否已经预习
                    List<CountRecord> recordlist = recordMapper.findCountRecordLaw(schedule.getLawTimeBefore(), schedule.getLawTime(), schedule.getScheduleId(), wechatUsers.getUserId());
                    if (recordlist.size() > 0) {
                        map.put("check", "true");
                    } else {
                        map.put("check", "");
                    }
                } else {
                    if (schedule.getLawTime() != null) {

                        //法本预习截止时间
                        map.put("lawTime", DateUtil.timestampToDates(schedule.getLawTime(), DateUtil.DATE_PATTON_DEFAULT));

                        //法本预习截止时间基础上减去刷新周期
                        String strLawTime = DateUtil.timestampToDates(schedule.getLawTime(), DateUtil.DATA_PATTON_YYYYMMDD);
                        //判断是否是法本预习
                        if (schedule.getScheduleType() == 1) {
                            String lawTimeBefore = DateUtil.getBeforeDateFormat(strLawTime, schedule.getRefreshTime());
                            String lawTimeAfter = DateUtil.getAfterDateFormat_new(strLawTime, 1);
                            Integer timeBefore = null;
                            Integer timeAfter = null;
                            if (StringUtils.isNotBlank(lawTimeBefore)) {
                                timeBefore = DateUtil.getDateToLinuxTime(lawTimeBefore, DateUtil.TIME_PATTON_DEFAULT);
                                schedule.setLawTimeBefore(timeBefore);
                            }
                            if (StringUtils.isNotBlank(lawTimeAfter)) {
                                timeAfter = DateUtil.getDateToLinuxTime(lawTimeAfter, DateUtil.TIME_PATTON_DEFAULT);
                            }
                            //查看本周期是否已经预习
                            List<CountRecord> recordlist = recordMapper.findCountRecordLaw(timeBefore, timeAfter, schedule.getScheduleId(), wechatUsers.getUserId());
                            if (recordlist.size() > 0) {
                                map.put("check", "true");
                            } else {
                                map.put("check", "");
                            }
                        }


                    } else {
                        map.put("check", "");
                        map.put("lawTime", DateUtil.timestampToDates(DateUtil.currentSecond(), DateUtil.DATE_PATTON_DEFAULT));
                    }
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
            list.add(map);
        }
        return list;
    }


    /**
     * 上报数历史记录
     *
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> findAllCountRecord(CountRecordParam param) {

        WechatUsers wechatUsers = wechatUsersMapper.findUserById(param.getOpenId());

        PageHelper.startPage(param.getPageNumber(), 15);
        List<CountRecord> list = recordMapper.findAllCountRecord(wechatUsers.getUserId(), param.getScheduleId());
        Map<String, Object> map = new HashMap<>();
        if (list.size() <= 0) {
            map.put("msg", 0);
        } else {
            for (CountRecord record : list) {
                if (null != record.getCreatedTime()) {
                    String time = DateUtil.timestampToDates(record.getCreatedTime(), DateUtil.TIME_PATTON_DEFAULT);
                    record.setCreatedTimes(time);
                }
            }
            PageInfo pageInfo = new PageInfo(list);
            map.put("totalPage", pageInfo.getPages());
            map.put("list", list);
            map.put("msg", 1);
        }

        return map;
    }

    /**
     * 报数历史记录多版本
     *
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> findCountRecordByTypeId(CountRecordParam param) {
        WechatUsers wechatUsers = wechatUsersMapper.findUserById(param.getOpenId());

        PageHelper.startPage(param.getPageNumber(), 15);
        List<CountRecord> list = recordMapper.findCountRecordByTypeId(wechatUsers.getUserId(), param.getMeditationTypeId());
        Map<String, Object> map = new HashMap<>();
        if (list.size() <= 0) {
            map.put("msg", 0);
        } else {
            for (CountRecord record : list) {
                //共修内容ID
                record.setScheduleId(record.getScheduleId());
                //共修内容名称
                record.setActiveTitle(record.getActiveTitle());
                //法本预习
                record.setLaw(record.getLaw());
                //上报数字
                record.setCount(record.getCount());
                record.setCountId(record.getCountId());

                if (null != record.getCreatedTime()) {
                    String time = DateUtil.timestampToDates(record.getCreatedTime(), DateUtil.TIME_PATTON_DEFAULT);
                    record.setCreatedTimes(time);
                }
            }
            PageInfo pageInfo = new PageInfo(list);
            map.put("totalPage", pageInfo.getPages());
            map.put("list", list);
            map.put("msg", 1);
        }

        return map;
    }

    /**
     * 今日上报数
     *
     * @param param
     * @return
     */
    @Override
    public Integer findTodayCount(CountRecordParam param) {
        WechatUsers wechatUsers = wechatUsersMapper.findUserById(param.getOpenId());

        Integer toDayNumber = recordMapper.finAllCountByDay(wechatUsers.getUserId(), param.getScheduleId());
        if (null == toDayNumber) {
            toDayNumber = 0;
        }
        return toDayNumber;
    }

    /**
     * 删除历史记录的数据
     *
     * @param param
     */
    @Override
    public void deleteCountRecord(CountRecordParam param) {

        //根据openid查找userid
        WechatUsers wechatUsers = wechatUsersMapper.findUserById(param.getOpenId());

        if (param.getCountId() != null) {
            recordMapper.deleteCountRecord(param.getCountId());
            //查看此共修任务是否已存在
            MeditationTask meditationTask = meditationTaskMapper.findMeditationTaskById(wechatUsers.getUserId(), param.getScheduleId());

            if (meditationTask != null) {
                MeditationTask task = new MeditationTask();
                if (param.getCount() == null) {

                    task.setCount(0);
                } else {

                    task.setCount(-param.getCount());
                }
                meditationTask.setMeditationTypeId(task.getMeditationTypeId());
                task.setUpdateTime(DateUtil.currentSecond());
                task.setId(meditationTask.getId());

                meditationTaskMapper.updateMeditationTask(task);
            }
        }
    }

    public static void main(String[] args) {


        int a = 233;
        int b = 33;
        System.out.println(-a);
    }


}
