package com.hrym.task;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.hrym.common.util.DateUtil;
import com.hrym.common.util.NumUtil;
import com.hrym.mapper.TestMapper;
import com.hrym.mapper.TimingTaskMapper;
import com.hrym.wechat.entity.Flock;
import com.hrym.wechat.entity.FlockItem;
import com.hrym.wechat.entity.FlockUserBack;
import com.hrym.wechat.entity.WechatUserAccount;
import com.hrym.wechat.mapper.FlockItemMapper;
import com.hrym.wechat.mapper.FlockMapper;
import com.hrym.wechat.mapper.FlockUserBackMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hrym13 on 2018/12/11.
 */
@Component
@Service
public class TimingTask {
    
    
    @Autowired
    private TimingTaskMapper timingTaskMapper;
    
    @Autowired
    private TestMapper testMapper;

//        @Scheduled(cron = "0/5 * * * * ?")
//    public  void testS() {
//
//        System.out.println("hello world");
//            System.out.println(DateUtil.currentSecond());
//
//    }
    
        @Scheduled(cron = "0 59 23 ? * * ")
//    @Scheduled(cron = "0/50 * * * * ?")
    public void testList() {
       
        Integer time = DateUtil.currentSecond();
        String timestr = DateUtil.timestampToDates(time, DateUtil.DATA_PATTON_YYYYMMDD2);
        String weeks = DateUtil.dateToWeek(timestr, DateUtil.DATA_PATTON_YYYYMMDD2);
        String ymd = DateUtil.timestampToDates(time, DateUtil.DATA_PATTON_YYYYMMDD);
        String info = "愿以此功德，普及于一切；我等与众生，皆共成佛道。";
        
        List<Flock> list = timingTaskMapper.queryFlockList();
        for (Flock l : list) {
            
            if (l.getAvatarUrl() == null) {
                l.setAvatarUrl(l.getAvatar());
            }
//            String lessonName = "";
            List<FlockItem> itemlist = timingTaskMapper.queryByFlockItemListByFlockId(l.getId());
            for (FlockItem item : itemlist) {
                if (item.getLessonName() == null) {
                    item.setLessonName("功课");
                }
//                if (StringUtils.isNotBlank(lessonName)) {
//                    lessonName = lessonName + "," + item.getLessonName();
//                } else {
//                    lessonName = lessonName + item.getLessonName();
//                }
                //查询当前用户的量词
                String unit = item.getUnit();
                unit = StringUtils.isNotBlank(unit) ? unit : "遍";
                
                item.setDayDoneNumStr(NumUtil.getTotalNumStr(item.getDayDoneNum(), "0") + unit);
                
            }
//            if (StringUtils.isBlank(l.getDedicationVerses())){
//                String userNameS = "";
//                //根据群ID查找今日特别回向人
//                List<WechatUserAccount> userLIst = timingTaskMapper.queryUserNameByFlockIdAndYmd(l.getId(),ymd);
//                if (userLIst.size()>0){
//                    for (WechatUserAccount us:userLIst){
//                        if (StringUtils.isNotBlank(userNameS)){
//                            userNameS = userNameS+","+us.getNickName();
//                        }else {
//                            userNameS = userNameS+us.getNickName();
//                        }
//                    }
//                }
//                //弟子众等愿以此所共修xxx（填写共修的功课名称）之功德，回向给xxx（填写特别回向的人）祈愿其得善诸缘，所求一切世出世间善愿皆能速疾圆成。吉祥如意。
//                String info = "弟子众等愿以此所共修"+lessonName+"之功德，回向给"+userNameS+"祈愿其得善诸缘，所求一切世出世间善愿皆能速疾圆成。吉祥如意。";
//                l.setDedicationVerses(info);
//            }
            
            FlockUserBack flockUserBack = new FlockUserBack();
            
            flockUserBack.setAvatarUrl(l.getAvatarUrl());
            flockUserBack.setFlockId(l.getId());
            flockUserBack.setRecordId(l.getId());
            flockUserBack.setName(l.getName());
            flockUserBack.setNickname(l.getNickName());
            flockUserBack.setTime(DateUtil.currentSecond());
            flockUserBack.setYmd(ymd);
            if (StringUtils.isNotBlank(l.getDedicationVerses())){
                
                flockUserBack.setInfo(l.getDedicationVerses());
            }else {
                flockUserBack.setInfo(info);
    
            }
            flockUserBack.setTimeStr(timestr);
            flockUserBack.setWeeks(weeks);
            flockUserBack.setItemJoinNum(l.getItemJoinNum());

//            flockUserBack.setList(itemlist);
            
            String itemJson = JSON.toJSONString(itemlist);
            String descJson = JSON.toJSONString(flockUserBack);
            flockUserBack.setDescJson(descJson);
            flockUserBack.setItemJson(itemJson);
            
            timingTaskMapper.insertTestSpecialBack(flockUserBack);
        }
            //更新回向表里今日特别回向文编辑状态
            timingTaskMapper.updateSpecialBackByYmd();

//        updateToadyNum();
        
    }
    
    public void updateToadyNum(){
    
        //更新回向表里今日特别回向文编辑状态
        timingTaskMapper.updateSpecialBackByYmd();
    
        // 定时清空群flock表的今日提交数
        timingTaskMapper.updateFlockDayDoneNum();
        
        //定时清空群bookcase表的今日提交数
        timingTaskMapper.updateBookcaseTodayCommitNum();
        //定时清空群flock_item表的今日提交数
        timingTaskMapper.updateFlockItemDayDoneNum();
        
    }
}
