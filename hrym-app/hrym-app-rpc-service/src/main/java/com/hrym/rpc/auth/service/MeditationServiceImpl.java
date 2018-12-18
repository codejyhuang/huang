package com.hrym.rpc.auth.service;

import com.hrym.common.util.DateUtil;
import com.hrym.rpc.TokenUtil;
import com.hrym.rpc.app.common.constant.MeditationParam;
import com.hrym.rpc.app.dao.model.task.TaskRecord;
import com.hrym.rpc.app.dao.model.task.meditation.MeditationContent;
import com.hrym.rpc.app.dao.model.task.meditation.MeditationItem;
import com.hrym.rpc.app.dao.model.task.UserInfo;
import com.hrym.rpc.app.dao.model.task.meditation.MeditationPlan;
import com.hrym.rpc.auth.api.MeditationService;
import com.hrym.rpc.auth.dao.mapper.TaskRecordMapper;
import com.hrym.rpc.auth.dao.mapper.meditationMapper.MeditationContentMapper;
import com.hrym.rpc.auth.dao.mapper.meditationMapper.MeditationItemMapper;
import com.hrym.rpc.auth.dao.mapper.meditationMapper.MeditationPlanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * 禅修
 * Created by mj on 2018/5/28.
 */
public class MeditationServiceImpl implements MeditationService {

    @Autowired
    private MeditationItemMapper meditationItemMapper;
    @Autowired
    private MeditationContentMapper meditationContentMapper;
    @Autowired
    private MeditationPlanMapper meditationPlanMapper;
    @Autowired
    private TaskRecordMapper taskRecordMapper;

    /**
     * 获取禅修主页
     * @param param
     * @return
     */
    @Override
    public Map<String,Object> getHomePage(MeditationParam param) {

        UserInfo user = TokenUtil.getUserByToken(param.getToken());
        if (null == user){
            user = new UserInfo();
        }
        //查找用户最后一次做的禅修功课
        MeditationPlan plan = meditationPlanMapper.findLastOneByUserId(user.getUuid());
//        PageHelper.startPage(param.getPageNo(),param.getPageSize());
        List<MeditationItem> itemList = meditationItemMapper.findAll();
        for (MeditationItem item : itemList){
            int index = 0;
            //查询该功课对应的内容
            List<MeditationContent> contentList = meditationContentMapper.findAllByItemId(item.getItemId());
            for (MeditationContent content : contentList){
                int itemId = content.getItemId();
                int contentId = content.getContentId();
                //查询用户上次该版本内容设置的时间
                MeditationPlan meditation = meditationPlanMapper.findLastOneByUserIdAndItemIdAndContentId(user.getUuid(),itemId,contentId);
                if (null != meditation){
                    content.setFixedMusicTime(meditation.getTimeNum());
                }
                if(null != plan && plan.getItemId() == itemId && plan.getContentId() == contentId) {
                    item.setIndex(index);
                    if (plan.getDownNum() > 0){
                        item.setSort(100);
                    }
                }
                index++;
            }
            item.setContentList(contentList);
        }
        //集合排序
        Collections.sort(itemList, new Comparator<MeditationItem>() {

            @Override
            public int compare(MeditationItem o1, MeditationItem o2) {
                int i = o2.getSort() - o1.getSort();
//                if(i == 0){
//                    return o2.getUpdateTime() - o1.getUpdateTime();
//                }
                return i;
            }
        });
        Map<String,Object> map = new HashMap<>();
        map.put("list",itemList);

        return map;
    }

    /**
     * 修改禅修时长
     * @param param
     */
    @Override
    public void editMeditation(MeditationParam param) {

        MeditationPlan plan = new MeditationPlan();
        UserInfo user = TokenUtil.getUserByToken(param.getToken());
        //查找库里用不是否已经做过
        MeditationPlan meditationPlan = meditationPlanMapper.findOneByUserIdAndItemIdAndContentId(user.getUuid(),
                param.getItemId(),param.getContentId());

        plan.setItemId(param.getItemId());
        plan.setUserId(user.getUuid());
        plan.setContentId(param.getContentId());
        plan.setTimeNum(param.getTimeNum());
        plan.setUpdateTime(DateUtil.currentSecond());
        if (null == meditationPlan){
            plan.setDownNum(0);
            plan.setCreateTime(DateUtil.currentSecond());
            meditationPlanMapper.saveMeditationPlan(plan);
        }else {
            meditationPlanMapper.updateTimeNumByUserIdAndItemIdAndContentId(plan);
        }
    }

    /**
     * 禅修上报
     * @param param
     */
    @Override
    public void meditationReport(MeditationParam param) {

        if (param.getItemId() == null || param.getContentId() == null){
            return;
        }
        int taskId = 0;
        MeditationPlan plan = new MeditationPlan();
        UserInfo user = TokenUtil.getUserByToken(param.getToken());
        //查找库里用不是否已经做过
        MeditationPlan meditationPlan = meditationPlanMapper.findOneByUserIdAndItemIdAndContentId(user.getUuid(),
                param.getItemId(),param.getContentId());

        plan.setItemId(param.getItemId());
        plan.setUserId(user.getUuid());
        plan.setContentId(param.getContentId());
        plan.setTimeNum(param.getTimeNum());
        plan.setDownNum(param.getReportNum());
        plan.setUpdateTime(DateUtil.currentSecond());
        if (null == meditationPlan){
            plan.setCreateTime(DateUtil.currentSecond());
            meditationPlanMapper.saveMeditationPlan(plan);
            //获取插入后的主键id
            taskId = meditationPlanMapper.getLastId();
        }else {
            meditationPlanMapper.updateDownNumByUserIdAndItemIdAndContentId(plan);
            taskId = meditationPlan.getId();
        }


        TaskRecord record = new TaskRecord();
        record.setUserId(user.getUuid());
        record.setTaskId(taskId);
        record.setReportNum(param.getReportNum());
        record.setReportTime(DateUtil.currentSecond());
        record.setItemId(param.getItemId());
        record.setRecordMethod(1);
        record.setTypeId(10007);
        taskRecordMapper.insert(record);
    }


}
