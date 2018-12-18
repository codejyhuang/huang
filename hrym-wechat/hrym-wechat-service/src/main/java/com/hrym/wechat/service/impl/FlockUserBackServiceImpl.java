package com.hrym.wechat.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hrym.common.util.DateUtil;
import com.hrym.common.util.NumUtil;
import com.hrym.wechat.entity.*;
import com.hrym.wechat.mapper.*;
import com.hrym.wechat.service.IFlockUserBackService;
import com.hrym.wechat.smallProgram.FlockRecordParam;
import com.hrym.wechat.smallProgram.FlockUserBackParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by hrym13 on 2018/11/28.
 */
@Service
public class FlockUserBackServiceImpl implements IFlockUserBackService {
    
    @Autowired
    private FlockUserBackMapper flockUserBackMapper;
    @Autowired
    private ItemLessonMapper itemLessonMapper;
    @Autowired
    private FlockMapper flockMapper;
    @Autowired
    private FlockItemMapper flockItemMapper;
    @Autowired
    private ItemUserUnitMapper userUnitMapper;
    @Autowired
    private WechatUsersMapper wechatUsersMapper;
    
    /**
     * 用户上一次特别回向文
     *
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> lastSpecialBack(FlockUserBackParam param) {
        
        Map<String,Object> map = new HashMap<>();
        
        FlockUserBack flockUserBack = flockUserBackMapper.queryLastTimeSpecialBack(param);
            map.put("flockUserBack",flockUserBack);
        
        return map;
    }
    
    /**
     * 共修回向列表
     *
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> queryDedicationVersesList(FlockUserBackParam param) {
        
        String url = "http://upd.everglamming.com:8089/group1/M00/00/13/wKgAHFoBUH-ARZFiAAC0d7SdfuU124.jpg";
        
        List list = new ArrayList();
        List<FlockUserBack> flockUserBackList = null;
        
        // 0:群回向\n1:特别回向
        if (param.getType() == 0) {
            
            PageHelper.startPage(param.getCurrentPage(), param.getPageSize());
            flockUserBackList = flockUserBackMapper.queryDedicationVersesList(param.getUuid());
            
            if (flockUserBackList != null) {
                for (FlockUserBack userBack : flockUserBackList) {
                    
                    Map<String, Object> map = new HashMap<>();

//
                    FlockUserBackJson json = JSON.parseObject(userBack.getDescJson(), FlockUserBackJson.class);
                    
                    System.out.println(json);
                    map.put("ymd", userBack.getYmd());
                    map.put("name", json.getName());
                    map.put("nickName", json.getNickname());
                    map.put("avatarUrl", json.getAvatarUrl());
                    //recordId群回向时为群ID
                    map.put("flockId", userBack.getRecordId());
                    map.put("info", userBack.getInfo());
                    map.put("id", userBack.getId());
                    
                    String time = DateUtil.timestampToDates(DateUtil.currentSecond(), DateUtil.TIME_PATTON_DEFAULT1);
                    
                    if (userBack.getTime() != null) {
                        time = DateUtil.timestampToDates(userBack.getTime(), DateUtil.TIME_PATTON_DEFAULT1);
                    }
                    map.put("time", time);
                    
                    map.put("viewStr", (json.getNickname() == null ? "师兄" : json.getNickname()) + "." + time);
                    list.add(map);
                }
                
            }
            
        } else {
            
            String info = "愿以此功德,庄严佛净土。上报四重恩,下济三途苦。若有见闻者,悉发菩提心。尽此一报身,同生极乐国";
            PageHelper.startPage(param.getCurrentPage(), param.getPageSize());
            flockUserBackList = flockUserBackMapper.queryDedicationVerses(param.getUuid());
            
            if (flockUserBackList != null) {
                
                for (FlockUserBack userBack : flockUserBackList) {
                    
                    Map<String, Object> map = new HashMap<>();
                    
                    map.put("info", userBack.getInfo()==null?info:userBack.getInfo());
                    map.put("id", userBack.getId());
                    map.put("itemId", userBack.getItemId());
                    map.put("contentId", userBack.getContentId());
                    map.put("itemType", userBack.getItemType());
                    map.put("viewStr", DateUtil.getTimeChaHour(DateUtil.formatIntDate(userBack.getTime() == null ? DateUtil.currentSecond() : userBack.getTime()), new Date()));
                    if (StringUtils.isNotBlank(userBack.getVersionName())) {
                        map.put("versionName", userBack.getVersionName());
                    } else {
                        map.put("versionName", "功课");
                    }
                    list.add(map);
                }
            }
        }
        
        PageInfo pageInfo = new PageInfo(flockUserBackList);
        
        long total = pageInfo.getTotal();
        long totalPages = pageInfo.getPages();
        
        Map<String, Object> maps = new HashMap<>();
        maps.put("list", list);
        maps.put("total", total);
        maps.put("totalPages", totalPages);
        return maps;
    }
    
    /**
     * 我的特别回向详情
     *
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> querySpecialBackDetails(FlockUserBackParam param) {
        
        Map<String, Object> map = new HashMap<>();
        String info = "愿以此功德,庄严佛净土。上报四重恩,下济三途苦。若有见闻者,悉发菩提心。尽此一报身,同生极乐国";
        
        String times = DateUtil.timestampToDates(DateUtil.currentSecond(), DateUtil.DATA_PATTON_YYYYMMDD2);
        
        FlockUserBack flockUserBack = flockUserBackMapper.queryDedicationVersesById(param.getId());
        
        if (flockUserBack != null) {
            
            if (flockUserBack.getTime() != null) {
                
                times = DateUtil.timestampToDates(flockUserBack.getTime(), DateUtil.DATA_PATTON_YYYYMMDD2);
            }
            String weeks = DateUtil.dateToWeek(times, DateUtil.DATA_PATTON_YYYYMMDD2);
            
            map.put("timeStr", times);
            map.put("weeks", weeks);
            map.put("info", flockUserBack.getInfo()==null?info:flockUserBack.getInfo());
            map.put("id", flockUserBack.getId());
            map.put("flag", flockUserBack.getFlag());
        }
        return map;
    }
    
    
    /**
     * 编辑特别回向文
     *
     * @param param
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateSpecialBack(FlockUserBackParam param) {
        
        Integer time = DateUtil.currentSecond();
        
        flockUserBackMapper.updateSpecialBack(param.getId(), param.getInfo(), time);
    }
    
    /**
     * 特别回向删除
     * @param param
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteSpecialBack(FlockUserBackParam param) {
        
        if (param.getId() != null) {
            
            flockUserBackMapper.deleteSpecialBack(param.getId());
        }
    }
    
    /**
     * 报数的功课回向
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> queryCountDirection(FlockUserBackParam param) {
    
        String url = "http://upd.everglamming.com:8089/group1/M00/00/13/wKgAHFoBUH-ARZFiAAC0d7SdfuU124.jpg";
    
        Map<String,Object> map =new HashMap<>();
        String info = "愿以此功德,庄严佛净土。上报四重恩,下济三途苦。若有见闻者,悉发菩提心。尽此一报身,同生极乐国";
        
        FlockLessonDO itemLesson = itemLessonMapper.queryLessonFromViewByItemIdAndItemContentIdAndType(param.getItemId(),param.getContentId(),param.getItemType());
        FlockUserBack flockUserBack = wechatUsersMapper.findUserAcountByUserId(param.getUuid());
        if (flockUserBack !=null){
            map.put("nickName",flockUserBack.getNickname());
            map.put("avatarUrl",(flockUserBack.getAvatar() ==null? url: flockUserBack.getAvatar()));
        }
        if (itemLesson!=null){
        map.put("info",itemLesson.getInfo());
        }else {
            map.put("info",info);
        }
        //查询当前用户的量词
        String unit = userUnitMapper.queryUnitByUuidAndItemIdAndItemContentIdAndType(param.getUuid(),
                param.getItemId(),param.getContentId(), param.getItemType());
        unit = StringUtils.isNotBlank(unit) ? unit : "遍";
        
        map.put("numStr",NumUtil.getTotalNumStr(param.getNum(), "0") + unit);
        String timeStr = DateUtil.timestampToDates(DateUtil.currentSecond(),DateUtil.DATA_PATTON_YYYYMMDD2);
        String weeks = DateUtil.dateToWeek(timeStr,DateUtil.DATA_PATTON_YYYYMMDD2);
        map.put("week",weeks);
        map.put("timeStr",timeStr);
        return map;
    }
    
    
    /**
     * 功课特别回向录入
     *
     * @param param
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void insertSpecialBack(FlockUserBackParam param) {
        
        Integer time = DateUtil.currentSecond();
        String ymd = DateUtil.timestampToDates(time, DateUtil.DATA_PATTON_YYYYMMDD);
        
        if (StringUtils.isNotBlank(param.getInfo())){
            
            flockUserBackMapper.insertSpecialBack(param, time, ymd);
        }
        
        
    }
    
    
    /**
     * 群回向定时任务
     *
     * @param
     * @return
     */
    @Override
//    @Scheduled(cron = " 5 * * * * ?")
    public void testList() {
        
        Integer time = DateUtil.currentSecond();
        String timestr = DateUtil.timestampToDates(time, DateUtil.DATA_PATTON_YYYYMMDD2);
        String weeks = DateUtil.dateToWeek(timestr, DateUtil.DATA_PATTON_YYYYMMDD2);
        String ymd = DateUtil.timestampToDates(time, DateUtil.DATA_PATTON_YYYYMMDD);
        
        List<Flock> list = flockMapper.queryFlockList();
        for (Flock l : list) {
            if (l.getAvatarUrl() == null) {
                l.setAvatarUrl(l.getAvatar());
            }
            List<FlockItem> itemlist = flockItemMapper.queryByFlockItemListByFlockId(l.getId());
            for (FlockItem item : itemlist) {
                if (item.getVersionName() == null) {
                    item.setVersionName("功课");
                }
                //查询当前用户的量词
                String unit = item.getUnit();
                unit = StringUtils.isNotBlank(unit) ? unit : "遍";
                
                item.setDayDoneNumStr(NumUtil.getTotalNumStr(item.getDayDoneNum(), "0") + unit);
            }
            
            FlockUserBack flockUserBack = new FlockUserBack();
            
            flockUserBack.setAvatarUrl(l.getAvatarUrl());
            flockUserBack.setFlockId(l.getId());
            flockUserBack.setRecordId(l.getId());
            flockUserBack.setName(l.getName());
            flockUserBack.setNickname(l.getNickName());
            flockUserBack.setTime(DateUtil.currentSecond());
            flockUserBack.setYmd(ymd);
            flockUserBack.setInfo(l.getDedicationVerses());
            flockUserBack.setTimeStr(timestr);
            flockUserBack.setWeeks(weeks);
            flockUserBack.setItemJoinNum(l.getItemJoinNum());

//            flockUserBack.setList(itemlist);
            
            String itemJson = JSON.toJSONString(itemlist);
            String descJson = JSON.toJSONString(flockUserBack);
            flockUserBack.setDescJson(descJson);
            flockUserBack.setItemJson(itemJson);
            
            flockUserBackMapper.insertTestSpecialBack(flockUserBack);
        }
    }
    
//    @Scheduled(cron = "0/5 * * * * ?")
   @Override
    public  void testS() {

        System.out.println("hello world testS");
        
    }
    
    
    /**
     * 群回向详情dejson,nameJson
     *
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> queryDedicationVerses(FlockUserBackParam param) {
        Map<String, Object> map = new HashMap<>();
        
        FlockUserBack flockUserBack = flockUserBackMapper.queryDedicationVersesById(param.getId());
        
        if (flockUserBack !=null){
            
            FlockUserBackJson descJson = JSON.parseObject(flockUserBack.getDescJson(), FlockUserBackJson.class);
            List<FlockItem> flockItemList = JSON.parseArray(flockUserBack.getItemJson(),FlockItem.class);
    
            map.put("descJson", descJson);
            map.put("itemJson", flockItemList);
        }
//        map.put("fl", flockUserBack);
        
        
        return map;
    }
    
    /**
     * 群回向详情页今日群回向记录
     *
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> queryDedicationVersesRecordList(FlockUserBackParam param) {
        Map<String, Object> map = new HashMap<>();
        
        String tableName = null;
        if (param.getTime() !=null){
            tableName = "t_flock_record_"+DateUtil.getYear(param.getTimeStr());
        }else {
            tableName = "t_flock_record_view";
        }
        PageHelper.startPage(param.getCurrentPage(), param.getPageSize());
        List<FlockUserBack> backList = flockUserBackMapper.queryDedicationVersesRecordList(param.getFlockId(), param.getYmd(),tableName);
        
        PageInfo pageInfo = new PageInfo(backList);
        
        long total = pageInfo.getTotal();
        long totalPages = pageInfo.getPages();
        
        map.put("list", backList);
        map.put("total", total);
        map.put("totalPages", totalPages);
        
        return map;
    }
    
    public static void main(String[] args) {
        FlockUserBackServiceImpl flockUserBackService = new FlockUserBackServiceImpl();
        flockUserBackService.testS();
    }
}
