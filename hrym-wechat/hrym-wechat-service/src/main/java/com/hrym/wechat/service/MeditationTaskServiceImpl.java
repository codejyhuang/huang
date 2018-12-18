package com.hrym.wechat.service;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import com.hrym.common.base.PageInfo;
import com.hrym.wechat.entity.MeditationSchedule;
import com.hrym.wechat.entity.MeditationTask;
import com.hrym.wechat.entity.WechatUsers;
import com.hrym.wechat.mapper.MeditationTaskMapper;
import com.hrym.wechat.mapper.MscheduleMapper;
import com.hrym.wechat.mapper.WechatUsersMapper;
import com.hrym.wechat.smallProgram.MeditationScheduleParam;
import com.hrym.wechat.smallProgram.MeditationTaskService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hrym13 on 2018/4/20.
 */
public class MeditationTaskServiceImpl implements MeditationTaskService {


    @Autowired
    private MeditationTaskMapper meditationTaskMapper;
    @Autowired
    private WechatUsersMapper wechatUsersMapper;
    @Autowired
    private MscheduleMapper mscheduleMapper;


    /**
     * 个人总排行榜和今日个人排行
     *
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> findMeditationTask(MeditationScheduleParam param) {

        if (param.getTodayStatus() == 0) {

            //今日个人排行
            PageHelper.startPage(param.getPageNumber(), 10);
            List<MeditationTask> tasks = meditationTaskMapper.findTodayOrder(param.getScheduleId());
            PageInfo pageInfo = new PageInfo(tasks);
            List<Map<String, Object>> list = new ArrayList<>();
            if (tasks != null) {
                for (MeditationTask task : tasks) {

                    Map<String, Object> map = Maps.newHashMap();

                    map.put("scheduleId", task.getScheduleId());
                    map.put("activeTitle", task.getActiveTitle());
                    map.put("userId", task.getUserId());
                    //用户昵称
                    map.put("nickName", task.getNickName());
                    //总数
                    if (task.getCount() == null) {

                        map.put("count", 0);
                    } else {

                        map.put("count", task.getCount());
                    }
                    //头像
                    map.put("avatarUrl", task.getAvatarUrl());

                    list.add(map);
                }
            }

            Map<String, Object> maps = Maps.newHashMap();
            maps.put("list", list);
            maps.put("totalPage", pageInfo.getPages());
            maps.put("openGId", null);

            return maps;
        } else {

            //个人总排行榜
            PageHelper.startPage(param.getPageNumber(), 10);
            List<MeditationTask> tasks = meditationTaskMapper.findMeditationTask(param.getScheduleId());
            PageInfo pageInfo = new PageInfo(tasks);

            List<Map<String, Object>> list = new ArrayList<>();
            if (tasks != null) {
                for (MeditationTask task : tasks) {

                    WechatUsers list1 = wechatUsersMapper.findWechatUsersByUserId(task.getUserId());

                    Map<String, Object> map = Maps.newHashMap();

                    //用户名称
                    map.put("nickName", list1.getNickName());
                    map.put("Id", task.getId());
                    map.put("activeTitle", task.getActiveTitle());
                    map.put("userId", task.getUserId());
                    //报数总数
                    if (task.getCount() == null) {

                        map.put("count", 0);
                    } else {
                        map.put("count", task.getCount());
                    }
                    //头像
                    map.put("avatarUrl", list1.getAvatarUrl());
                    //共修活动名称
                    map.put("activeTitle", task.getActiveTitle());
                    //用户地区
                    map.put("province", list1.getProvince());

                    list.add(map);

                }
            }

            Map<String, Object> maps = Maps.newHashMap();
            maps.put("totalPage", pageInfo.getPages());
            maps.put("list", list);
            maps.put("openGId", null);
            return maps;
        }


    }


    /**
     * 个人我的总排名和今日我的排名
     *
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> findMysort(MeditationScheduleParam param) {

        WechatUsers wechatUsers = wechatUsersMapper.findUserById(param.getOpenId());

        if (param.getTodayStatus() == 0) {

            //今日我的排名
            MeditationTask mysort = meditationTaskMapper.todayMyPerOrder(param.getScheduleId(), wechatUsers.getUserId());

            Map<String, Object> map = new HashMap<>();
            if (mysort != null) {
                map.put("userId", mysort.getUserId());
                map.put("nickName", mysort.getNickName());
                map.put("count", mysort.getCount());
                map.put("rowNum", mysort.getRowNum());
                map.put("avatarUrl", mysort.getAvatarUrl());
                map.put("mysort",1);

            }else {
                map.put("mysort",0);
            }
            return map;
        } else {

            //我的总排名
            MeditationTask mysort = meditationTaskMapper.findMysort(param.getScheduleId(), wechatUsers.getUserId());
            Map<String, Object> map = new HashMap<>();
            if (mysort != null) {
                map.put("userId", mysort.getUserId());
                map.put("nickName", mysort.getNickName());
                map.put("count", mysort.getCount());
                map.put("rowNum", mysort.getRowNum());
                map.put("avatarUrl", mysort.getAvatarUrl());
                map.put("mysort",1);
            }else {
                // 0没有数据；1：有数据
                map.put("mysort",0);
            }
            return map;
        }

    }

    /**
     * 查找多版本排行的排名
     *
     * @param param
     * @return
     */
    @Override
    public MeditationSchedule findMeditationScheduleName(MeditationScheduleParam param) {

        MeditationSchedule med = mscheduleMapper.findMeditationScheduleName(param.getScheduleId());


        return med;
    }


    /**
     * 群总排行和群今日排行
     *
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> findMeditationTaskByMId(MeditationScheduleParam param) {

        if (param.getTodayStatus() == 0) {
            //群今日排行
            PageHelper.startPage(param.getPageNumber(), 15);
            List<MeditationTask> tasks = meditationTaskMapper.findTodayGroupOrder(param.getScheduleId(), param.getOpenGId());

            PageInfo pageInfo = new PageInfo(tasks);

            List<Map<String, Object>> list = new ArrayList<>();

            for (MeditationTask task : tasks) {

                Map<String, Object> map = Maps.newHashMap();

                map.put("scheduleId", task.getScheduleId());
                map.put("userId", task.getUserId());
                //用户昵称
                map.put("nickName", task.getNickName());
                //总数
                if (task.getCount() == null) {

                    map.put("count", 0);
                } else {

                    map.put("count", task.getCount());
                }
                //头像
                map.put("avatarUrl", task.getAvatarUrl());
                list.add(map);
            }

            Map<String, Object> maps = Maps.newHashMap();
            maps.put("list", list);
            maps.put("totalPage", pageInfo.getPages());
            maps.put("openGId", param.getOpenGId());
            return maps;

        } else {
            //群总排行
            PageHelper.startPage(param.getPageNumber(), 15);
            List<MeditationTask> tasks = meditationTaskMapper.findMeditationTaskByMId(param.getOpenGId(), param.getScheduleId());
            List<Map<String, Object>> list = new ArrayList<>();

            for (MeditationTask task : tasks) {
                Map<String, Object> map = Maps.newHashMap();
                WechatUsers list1 = wechatUsersMapper.findWechatUsersByUserId(task.getUserId());
                if (list1 != null) {
                    //用户名称
                    map.put("nickName", list1.getNickName());
                    map.put("Id", task.getId());
                    map.put("userId", task.getUserId());
                    //报数总数
                    if (task.getCount() == null) {
                        map.put("count", 0);
                    } else {
                        map.put("count", task.getCount());
                    }
                    //头像
                    map.put("avatarUrl", list1.getAvatarUrl());
                    //共修活动名称
                    map.put("activeTitle", task.getActiveTitle());
                    //用户地区
                    map.put("province", list1.getProvince());
                    list.add(map);
                }
            }

            PageInfo pageInfo = new PageInfo(tasks);
            Map<String, Object> maps = new HashMap<>();
            maps.put("list", list);
            maps.put("totalPage", pageInfo.getPages());
            maps.put("openGId", param.getOpenGId());
            return maps;
        }

    }

    /**
     * 群排行榜我的总排名和群排行我的今日排名
     *
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> findMeditonById(MeditationScheduleParam param) {

        WechatUsers wechatUsers = wechatUsersMapper.findUserById(param.getOpenId());

        if (param.getTodayStatus() == 0) {

            //群排行榜我的今日排名
            MeditationTask mediton = meditationTaskMapper.findTodayMyGroupOrder(param.getScheduleId(), param.getOpenGId(), wechatUsers.getUserId());

            Map<String, Object> map = new HashMap<>();
            if (mediton != null) {
                map.put("userId", mediton.getUserId());
                map.put("nickName", mediton.getNickName());
                map.put("count", mediton.getCount());
                map.put("rowNum", mediton.getRowNum());
                map.put("avatarUrl", mediton.getAvatarUrl());

            }

            return map;

        } else {

            //群排行榜我的总排名
            MeditationTask mediton = meditationTaskMapper.findMeditonById(param.getOpenGId(), param.getScheduleId(), wechatUsers.getUserId());

            Map<String, Object> map = new HashMap<>();
            if (mediton != null) {
                map.put("userId", mediton.getUserId());
                map.put("nickName", mediton.getNickName());
                map.put("count", mediton.getCount());
                map.put("rowNum", mediton.getRowNum());
                map.put("avatarUrl", mediton.getAvatarUrl());

            }

            return map;
        }
    }


}
