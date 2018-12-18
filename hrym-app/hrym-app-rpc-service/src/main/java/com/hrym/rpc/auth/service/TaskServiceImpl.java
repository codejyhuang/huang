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
import com.hrym.rpc.app.common.constant.TaskParam;
import com.hrym.rpc.app.common.constant.UcenterConstant;
import com.hrym.rpc.app.dao.model.task.*;
import com.hrym.rpc.auth.dao.mapper.BluetoothTaskMapper;
import com.hrym.rpc.association.dao.mapper.TopicMapper;
import com.hrym.rpc.auth.api.TaskService;
import com.hrym.rpc.auth.dao.mapper.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Created by mj on 2017/7/17.
 */
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskPlanMapper taskPlanMapper;
    @Autowired
    private TaskMusicMapper taskMusicMapper;
    @Autowired
    private ThumbsUpMapper thumbsUpMapper;
    @Autowired
    private TaskItemMapper taskItemMapper;
    @Autowired
    private TaskSubPlanMapper taskSubPlanMapper;
    @Autowired
    private TopicMapper topicMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TaskRecordMapper taskRecordMapper;
    @Autowired
    private BluetoothTaskMapper bluetoothTaskMapper;
    @Autowired
    private CustomTaskMapper customTaskMapper;

    /**
     * 功课状态
     * @param param
     * @return
     */
    @Override
    public BaseResult findTaskPlanById(TaskParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        Map<String,Object> map = Maps.newHashMap();
        TaskPlan taskPlan = null;
        TaskItem taskItem = null;
        if (null != param.getTaskId()){
            taskPlan = taskPlanMapper.findTaskPlanById(param.getTaskId());
            if (taskPlan.getCustomId() != null){
                CustomTask ct = customTaskMapper.selectByPrimaryKey(taskPlan.getCustomId());
                map.put("itemName",ct.getCustomName());
                map.put("itemId",ct.getCustomId());
            }else {
                taskItem = taskItemMapper.findTaskItemById(taskPlan.getItemId(),taskPlan.getTypeId());
                map.put("itemId",taskItem.getItemId());
                map.put("itemName",taskItem.getItemName());
                map.put("typeDesc",taskItem.getType().getTypeDesc());
                map.put("typeName",taskItem.getType().getTypeName());
            }
            //语音计数字段
            if (null != taskPlan.getTaskContent()){
                map.put("voiceCount",taskPlan.getTaskContent().getVoiceCount());
                map.put("voiceName",taskPlan.getTaskContent().getVoiceName());
                map.put("voiceDic",taskPlan.getTaskContent().getVoiceDic());
                map.put("voiceLm",taskPlan.getTaskContent().getVoiceLm());
                map.put("voiceText",taskPlan.getTaskContent().getVoiceText());
            }

            //判断是否过期 状态（0：未开始；1:过程中；2:已过期）
            int status = 1;
            int currentTime = DateUtil.currentSecond();
            if (null != taskPlan.getStartTime() && 0 < taskPlan.getPlanPeriod()){
                //将int时间转换为string类型
                String startTime = DateUtil.timestampToDates(taskPlan.getStartTime(),DateUtil.DATA_PATTON_YYYYMMDD2);
                //n天后时间
                String afterAnyDay = DateUtil.getAfterAnyDay(startTime,taskPlan.getPlanPeriod(),DateUtil.DATA_PATTON_YYYYMMDD2);
                long newAfterAnyDay = DateUtil.DateToLinuxTime(afterAnyDay,DateUtil.DATA_PATTON_YYYYMMDD2);
                //判断开始时间是否过期
                if (taskPlan.getStartTime() > currentTime){
                    status = 0;
                }else if (newAfterAnyDay >= currentTime){
                    status = 1;
                }else {
                    status = 2;
                }
            }
            //今日建议数量
            int targetNum = 0;
            if (taskPlan.getPlanTarget() != 0 && taskPlan.getPlanPeriod() != 0 && taskPlan.getStartTime() != null){
                //redis中去获取今日建议
                String redisKey = String.valueOf(param.getTaskId())+ UcenterConstant.VERIFY_TASK_PLAN;
                String value = RedisUtil.get(redisKey);
                if (StringUtils.isNotBlank(value)){
                    targetNum = Integer.valueOf(value);
                }else {
                    targetNum = NumUtil.getTargetNum(taskPlan.getPlanTarget(),taskPlan.getPlanPeriod(),taskPlan.getDoneNum(),taskPlan.getStartTime());
                    RedisUtil.remove(redisKey);
                    //获取当前时间
                    String s = DateUtil.getCurrentDateByFormat(DateUtil.TIME_PATTON_HHMMSS);
                    long from = DateUtil.DateToLinuxTime(s,DateUtil.TIME_PATTON_HHMMSS);
                    long to = DateUtil.DateToLinuxTime("24:00:00",DateUtil.TIME_PATTON_HHMMSS);
                    int hours = (int)(to-from);
                    RedisUtil.set(redisKey,String.valueOf(targetNum),hours);
                }
            }
            //完成功课百分比
            String percent = "0%";
            taskPlan.setTargetNum(targetNum);
            if (status == 2){
                map.put("targetNum","--");
                percent = "--";
            }else {
                map.put("targetNum",taskPlan.getTargetNum());
                percent = NumUtil.getPercent(taskPlan.getTodayCommitNum(), taskPlan.getTargetNum());
            }

            if (null != taskPlan.getTaskMusic()){
                TaskMusic tm = new TaskMusic();
                if (null != taskPlan.getItemContentId()){
                    tm.setItemContentId(taskPlan.getItemContentId());
                }
                tm.setItemId(taskPlan.getItemId());
                //查找多版本音频数量
                int num = taskMusicMapper.selectCount(tm);
                if (num > 1){
                    map.put("moreMusicVersion",1);
                }else {
                    map.put("moreMusicVersion",0);
                }
                map.put("musicId",taskPlan.getTaskMusic().getMusicId());
                map.put("musicName",taskPlan.getTaskMusic().getMusicName());
                map.put("musicFile",taskPlan.getTaskMusic().getMusicFile());
                map.put("versionId",taskPlan.getTaskMusic().getVersionId());
                map.put("endTime",taskPlan.getTaskMusic().getEndTime());
                map.put("startTime",taskPlan.getTaskMusic().getStartTime());
                map.put("startNum",taskPlan.getTaskMusic().getStartNum());
                map.put("settingTime",taskPlan.getTaskMusic().getSettingTime());
                map.put("step",taskPlan.getTaskMusic().getStep());
                map.put("musicSubUrl",taskPlan.getTaskMusic().getMusicSubtitle());
                map.put("musicFileAndroid",taskPlan.getTaskMusic().getMusicFileAndroid());
            }else if (taskPlan.getTypeId() != 10010){
                TaskMusic tm = new TaskMusic();
                if (null != taskPlan.getItemContentId()){
                    tm.setItemContentId(taskPlan.getItemContentId());
                }
                tm.setItemId(taskPlan.getItemId());
                //查找多版本音频数量
                int num = taskMusicMapper.selectCount(tm);
                tm.setMusicVersion(1);
                //查询标准版音频
                tm = taskMusicMapper.selectOne(tm);
                if (null != tm){
                    map.put("musicId",tm.getMusicId());
                    map.put("musicName",tm.getMusicName());
                    map.put("musicFile",tm.getMusicFile());
                    map.put("versionId",tm.getVersionId());
                    map.put("endTime",tm.getEndTime());
                    map.put("startTime",tm.getStartTime());
                    map.put("startNum",tm.getStartNum());
                    map.put("settingTime",tm.getSettingTime());
                    map.put("step",tm.getStep());
                    map.put("musicSubUrl",tm.getMusicSubtitle());
                    map.put("musicFileAndroid",tm.getMusicFileAndroid());
                    if (num > 1){
                        map.put("moreMusicVersion",1);
                    }else {
                        map.put("moreMusicVersion",0);
                    }
                }
            }
            map.put("isChose",taskPlan.getMusicId());
            map.put("countingMethod",taskPlan.getCountingMethod());
            map.put("doneNum",NumUtil.formatFloatNumber(taskPlan.getDoneNum()));
            map.put("percent",percent);
            map.put("typeId",taskPlan.getTypeId());
            map.put("todayCommitNum",NumUtil.formatFloatNumber(taskPlan.getTodayCommitNum()));
            map.put("planTarget",taskPlan.getPlanTarget());
            map.put("planPeriod",taskPlan.getPlanPeriod());
            map.put("beginTime",taskPlan.getStartTime());
        }else {
            code = BaseConstants.GWSCODE2001;
            message = BaseConstants.GWSMSG2001;
        }

        return new BaseResult(code,message,map);
    }


    /**
     * 删除功课
     * @param param
     * @return
     */
    @Override
    public BaseResult deleteTaskPlan(TaskParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        if (null != param.getTaskId()){
            //0:已删除；1：未删除
            taskPlanMapper.updateIsExit(param.getTaskId(),0,DateUtil.currentSecond());
            //更新蓝牙连接状态
            bluetoothTaskMapper.updateStatusByTaskId(param.getTaskId());
        }else {
            code = BaseConstants.GWSCODE2001;
            message = BaseConstants.GWSMSG2001;
        }
        return new BaseResult(code,message,null);
    }

    /**
     * 获取音频版本
     * @param param
     * @return
     */
    @Override
    public BaseResult findMusicVersion(TaskParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        //查找功课内容ID
        TaskPlan taskPlan = taskPlanMapper.findItemContentIdByTaskId(param.getTaskId());
        // 核心分页代码
        PageHelper.startPage(param.getPageNo(), BaseConstants.PAGE_SIZE);
        List<TaskMusic> musicList = taskMusicMapper.findMusicById(param.getItemId(),taskPlan.getItemContentId());

        PageInfo pageInfo = new PageInfo(musicList);
        List<Map<String,Object>> list = new ArrayList<>();
        for (TaskMusic t : musicList ){
            Map<String,Object> map = Maps.newHashMap();
            map.put("musicFile",t.getMusicFile());
            map.put("musicId",t.getMusicId());
            map.put("musicName",t.getMusicName());
            map.put("musicSubUrl",t.getMusicSubtitle());
            map.put("musicFileAndroid",t.getMusicFileAndroid());
            list.add(map);
        }
        Map<String,Object> maps = Maps.newHashMap();
        maps.put("hasNextPage",pageInfo.isHasNextPage());
        maps.put("musicList",list);

        return new BaseResult(code,message,maps);
    }

    /**
     * 设置计数方式
     * @param param
     * @return
     */
    @Override
    public BaseResult setCountingMethod(TaskParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        if (null != param.getTaskId() && null != param.getCountingMethod()){
            taskPlanMapper.updateTaskPlanById(param.getCountingMethod(),param.getTaskId());
        }else {
            code = BaseConstants.GWSCODE2002;
            message = BaseConstants.GWSMSG2002;
        }

        return new BaseResult(code,message,null);
    }

    /**
     * 设置音频版本
     * @param param
     * @return
     */
    @Override
    public BaseResult setMusicVersion(TaskParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        if (null != param.getTaskId() && null != param.getMusicId()){
            taskPlanMapper.updateMusicIdById(param.getMusicId(),param.getTaskId());
        }else {
            code = BaseConstants.GWSCODE2002;
            message = BaseConstants.GWSMSG2002;
        }

        return new BaseResult(code,message,null);
    }

    /**
     * 计数上报
     * @param param
     * @return
     */
    @Override
    public BaseResult saveCount(TaskParam param) {

        BaseResult baseResult = null;
        if(StringUtils.isNotEmpty(param.getGompaFlag())){

            //禅修情况，更新任务子表
          baseResult = this.doSaveCountCX(param);
        }else {
            baseResult = this.doSaveCount(param);
        }

        //更新功课任务时间
        taskPlanMapper.updateUpdateTimeByTaskId(DateUtil.currentSecond(),param.getTaskId());
        //上报数据记录
        TaskPlan taskPlan = taskPlanMapper.findTaskPlanByTaskId(param.getTaskId());
        TaskRecord record = new TaskRecord();
        record.setTaskId(taskPlan.getTaskId());
        record.setUserId(taskPlan.getUuid());
        record.setReportNum(param.getNum());
        record.setRecordMethod(param.getType());
        if (null == taskPlan.getItemId() && null != taskPlan.getCustomId()){
            record.setItemId(taskPlan.getItemId());
        }else {
            record.setItemId(taskPlan.getItemId());
        }
        if (param.getType() == 2 || param.getType() == 3 || param.getType() == 4){
            if (StringUtils.isNotBlank(param.getHistoryTime())){
                record.setReportTime(DateUtil.getDateToLinuxTime(param.getHistoryTime(),DateUtil.DATE_PATTON_DEFAULT));
            }else {
                record.setReportTime(DateUtil.currentSecond());
            }
        }else {
            record.setReportTime(DateUtil.currentSecond());
        }
        record.setTypeId(taskPlan.getTypeId());
        //插入上报数据
        taskRecordMapper.insert(record);

        return baseResult;

    }

    /**
     * 计数上报（除禅修外）
     *
     * @param param
     * @return
     */
    private BaseResult doSaveCount(TaskParam param){

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        //Redis中获取用户对象
        UserInfo user = JSON.parseObject(RedisUtil.get(param.getToken()), UserInfo.class);

        Map map = Maps.newHashMap();
        if (null != param.getTaskId()){
            if (param.getType() == 0){
                //手动报数
                taskPlanMapper.updateTaskplan(param.getNum(),param.getTaskId());
            }
            if (param.getType() == 1){
                //自动报数
                taskPlanMapper.updateTaskplanByAuto(param.getNum(),param.getTaskId());
            }
            if (param.getType() == 2){
                //自动报数
                taskPlanMapper.updateTaskplanByAuto(param.getNum(),param.getTaskId());
                //蓝牙报数
                bluetoothTaskMapper.updateReportNumByAddress(param.getNum(),param.getBluetoothAddress(),param.getTaskId());
            }
            if (param.getType() == 3){
                BluetoothTask bt = new BluetoothTask();
                bt.setBluetoothAddress(param.getBluetoothAddress());
                bt.setUserId(user.getUuid());
                bt.setTaskId(param.getTaskId());
                bt.setStatus(1);
                //查找该功课蓝牙设备下的信息
                BluetoothTask blue = bluetoothTaskMapper.findOneByAddressAndUserIdAndTaskId(bt);
                if (null == blue){
                    bt.setStatus(0);
                    bt.setItemId(param.getItemId());
                    bt.setTypeId(param.getTypeId());
                    bt.setReportNum(param.getNum());
                    bt.setStartTime(DateUtil.currentSecond());
                    bluetoothTaskMapper.insert(bt);
                }else {
                    //蓝牙结束功课上报
                    bluetoothTaskMapper.updateReportNumByAddress(param.getNum(),param.getBluetoothAddress(),param.getTaskId());
                    //更新蓝牙状态
                    bluetoothTaskMapper.updateStatusByAddress(param.getBluetoothAddress(),param.getTaskId());
                }
                //自动报数
                taskPlanMapper.updateTaskplanByAuto(param.getNum(),param.getTaskId());
            }

            if (param.getType() == 4){
                BluetoothTask bt = new BluetoothTask();
                bt.setBluetoothAddress(param.getBluetoothAddress());
                bt.setUserId(user.getUuid());
                bt.setTaskId(param.getTaskId());
                bt.setStatus(1);
                //查找该功课蓝牙设备下的信息
                BluetoothTask blue = bluetoothTaskMapper.findOneByAddressAndUserIdAndTaskId(bt);
                //自动报数
                taskPlanMapper.updateTaskplanByAuto((param.getNum()-blue.getCurrentNum()),param.getTaskId());
                //蓝牙报数
                bluetoothTaskMapper.updateCurrentNumByAddress((param.getNum()-blue.getCurrentNum()),param.getBluetoothAddress(),param.getTaskId());
            }
            //判断是否已做完功课，做完删除
            TaskPlan taskPlan = taskPlanMapper.findTaskPlanByTaskId(param.getTaskId());

            if (10010 == taskPlan.getTypeId()){
                CustomTask ct = customTaskMapper.selectByPrimaryKey(taskPlan.getCustomId());
                map.put("typeId",ct.getTypeId());
                map.put("itemId",ct.getCustomId());
                map.put("typeName","自定义功课");
                map.put("itemName",ct.getCustomName());
            }else {
                TaskItem item = taskItemMapper.findTaskItemById(taskPlan.getItemId(),taskPlan.getTypeId());
                map.put("typeId",taskPlan.getTypeId());
                map.put("itemId",taskPlan.getItemId());
                map.put("itemName",item.getItemName());
                map.put("typeName",item.getType().getTypeName());
            }
            //如果是供佛则不删除
            if (null != taskPlan.getTypeId() && taskPlan.getTypeId() == 10003){
                return new BaseResult(code,message,null);
            }
            //0:已到100%；1:未到100%
            int status = 0;
            if (null != taskPlan && taskPlan.getDoneNum()>=taskPlan.getPlanTarget()){
                map.put("status",status);
                //0:已删除；1：未删除
                taskPlanMapper.updateIsExit(param.getTaskId(),0,DateUtil.currentSecond());
                //更新蓝牙连接状态
                bluetoothTaskMapper.updateStatusByTaskId(param.getTaskId());
            }else {
                status = 1;
                map.put("status",status);
            }
        }else {
            code = BaseConstants.GWSCODE2002;
            message = BaseConstants.GWSMSG2002;
        }

        return new BaseResult(code,message,map);
    }


    /**
     * 禅修用计数上报
     *
     * @param param
     * @return
     */
    private BaseResult doSaveCountCX(TaskParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        // 更新t_task_sub_plan表，判断子任务是否存在，不存在插入，否则update
        if(StringUtils.isNotEmpty(param.getSubTaskId())){
            taskPlanMapper.updateSubTaskNum(param.getTaskId(),Integer.valueOf(param.getSubTaskId()),param.getNum());
        } else {

            // 第一次做该功课的禅修时，新增记录
            taskPlanMapper.insertSubTaskNum(param.getTaskId(),param.getItemId(),param.getNum());
        }
        double downNum = taskSubPlanMapper.findSumDownNum(param.getTaskId());
        taskPlanMapper.updateDownNum(param.getTaskId(),downNum);

        Map map = Maps.newHashMap();
        TaskPlan taskPlan = taskPlanMapper.findTaskPlanByTaskId(param.getTaskId());
        map.put("typeId",taskPlan.getTypeId());
        map.put("itemId",taskPlan.getItemId());
        TaskItem item = taskItemMapper.findTaskItemById(taskPlan.getItemId(),taskPlan.getTypeId());
        map.put("itemName",item.getItemName());
        map.put("typeName",item.getType().getTypeName());
        return new BaseResult(code,message,map);
    }

    /**
     * 功课排行榜
     * @param param
     * @return
     */
    @Override
    public BaseResult findTaskRanking(TaskParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        List<TaskPlan> taskPlans = null;
        if (null != param.getItemId()){
            UserInfo userInfo = userMapper.findUserInfoByToken(param.getToken());
            TaskPlan taskPlan = taskPlanMapper.findTaskPlanByTaskId(param.getTaskId());
            // 核心分页代码
            PageHelper.startPage(param.getPageNo(), BaseConstants.PAGE_SIZE);
            taskPlans = taskPlanMapper.findAllByItemId(param.getItemId(),taskPlan.getTypeId(),userInfo.getUuid());
        }

        List<Map<String,Object>> list = new ArrayList<>();
        for (TaskPlan t : taskPlans){
            Map<String,Object> maps = Maps.newHashMap();
            maps.put("doneNum",(int)t.getTodayCommitNum());
            maps.put("nickName",t.getUserInfo().getNickName());
            maps.put("thumbsUpNum",t.getThumbsUpNum());
            maps.put("uuid",t.getUserInfo().getUuid());
            maps.put("avatar",t.getUserInfo().getAvatar());
            maps.put("topicId",t.getTaskId());
            if (null != t.getThumbsUp()){
                maps.put("type",t.getThumbsUp().getType());
            } else {
                maps.put("type",0);
            }
            list.add(maps);
        }
        PageInfo pageInfo = new PageInfo(taskPlans);

        //查询我的排行状态
        TaskPlan taskPlan = taskPlanMapper.findMyRankById(param.getTaskId());
        Map map = Maps.newHashMap();
        if (null != taskPlan){
            map.put("userId",taskPlan.getUuid());
            map.put("avatar",taskPlan.getUserInfo().getAvatar());
            map.put("doneNum",(int)taskPlan.getTodayCommitNum());
            map.put("itemId",taskPlan.getItemId());
            map.put("itemName",taskPlan.getTaskItem().getItemName());
            map.put("hasNextPage",pageInfo.isHasNextPage());
            map.put("list",list);
        }

        return new BaseResult(code,message,map);
    }

    /**
     * 点赞／取消点赞
     * @param param
     * @return
     */
    @Override
    public BaseResult doThumbsUp(TaskParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        Map<String,Object> map = null;
        //点赞：1，取消点赞：0
        if (param.getType() == 0){
            thumbsUpMapper.updateThumbsUp(param.getType(),param.getTopicId(),param.getUserId(),param.getTopicType());
            //0:功课相关\1:社群相关
            if (param.getTopicType() == 0){
                taskPlanMapper.updatethumbsUpNum(-1,param.getTopicId());
            }
            if (param.getTopicType() == 1){
                topicMapper.updateAgreeNumByTopicId(-1,param.getTopicId());
                map = getThumbsUpName(param.getTopicId(),param.getTopicType());
            }
            return new BaseResult(code,message,map);
        }

        //判断是否点赞过
        ThumbsUp thumbsUp = thumbsUpMapper.findThumsUp(param.getTopicId(),param.getUserId(),param.getTopicType());
        if (null == thumbsUp){
            ThumbsUp thumbsUpBean = new ThumbsUp();
            if (null != param.getUserId()){
                thumbsUpBean.setUserId(param.getUserId());
            }
            if (null != param.getTopicId()){
                thumbsUpBean.setTopicId(param.getTopicId());
            }
            if (null != param.getBeizanrenId()){
                thumbsUpBean.setBeizanrenId(param.getBeizanrenId());
            }
            thumbsUpBean.setTime(DateUtil.currentSecond());
            thumbsUpBean.setType(param.getType());
            thumbsUpBean.setTopicType(param.getTopicType());
            //插入一条点赞信息
            thumbsUpMapper.insertThumbsUp(thumbsUpBean);
        }else {
            thumbsUpMapper.updateThumbsUp(param.getType(),param.getTopicId(),param.getUserId(),param.getTopicType());
        }

        //0:功课相关\1:社群相关
        if (param.getTopicType() == 0){
            //更新点赞数
            taskPlanMapper.updatethumbsUpNum(1,param.getTopicId());
        }
        if (param.getTopicType() == 1){
            topicMapper.updateAgreeNumByTopicId(1,param.getTopicId());
            map = getThumbsUpName(param.getTopicId(),param.getTopicType());
        }

        return new BaseResult(code,message,map);
    }

    /**
     * 获取点赞人名称并拼接在一起
     * @param topicId
     * @param topicType
     * @return
     */
    public Map<String,Object> getThumbsUpName(Integer topicId,Integer topicType){
        //获取点赞人昵称
        PageHelper.startPage(1,3);
        //0:功课类型；1:社群文章
        List<ThumbsUp> thumbsUpBean = thumbsUpMapper.findUserIdByTopicId(topicId,topicType);
        PageInfo pageInfo = new PageInfo(thumbsUpBean);
        Map<String,Object> map = Maps.newHashMap();
        if (0 < thumbsUpBean.size()){
            String nameStr = "";
            for (ThumbsUp th : thumbsUpBean){
                String name = th.getUserInfo().getNickName()+"、";
                nameStr = nameStr+name;
            }
            if (pageInfo.getTotal() > 3){
                map.put("agreeDesc",nameStr.substring(0,nameStr.length()-1)+"等"+pageInfo.getTotal()+"人觉得很赞");
            }else {
                map.put("agreeDesc",nameStr.substring(0,nameStr.length()-1)+"觉得很赞");
            }
        }else {
            map.put("agreeDesc",null);
        }

        return map;
    }

    /**
     * 功课状态禅修
     * @param param
     * @return
     */
    @Override
    public BaseResult taskStatusCX(TaskParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        List<Map<String,Object>> list = new ArrayList<>();
        List<TaskMusic> taskMusic = null;
        if (null != param.getTaskId()) {
            // 核心分页代码
            PageHelper.startPage(param.getPageNo(), BaseConstants.PAGE_SIZE);
            taskMusic = taskMusicMapper.findMusicResourceById(param.getTaskId());

            for (TaskMusic t : taskMusic){
                Map<String,Object> map = Maps.newHashMap();
                if (null != t.getTaskSubPlan()){
                    map.put("doneNum","已修行"+(int)t.getTaskSubPlan().getDownNum()+"分钟");
                    map.put("subTaskId",t.getTaskSubPlan().getSubTaskId());
                }else {
                    map.put("doneNum","已修行"+0+"分钟");
                    map.put("subTaskId",null);
                }
                if (null != t.getTaskSubResource()){
                    map.put("musicSubFile",t.getTaskSubResource().getValue());
                }else {
                    map.put("musicSubFile",null);
                }
                map.put("itemId",t.getTaskItem().getItemId());
                map.put("itemName",t.getTaskItem().getItemName());
                map.put("musicFile",t.getMusicFile());
                map.put("musicId",t.getMusicId());
                map.put("musicName",t.getMusicName());
                map.put("musicSubUrl",t.getMusicSubtitle());
                map.put("versionId",t.getVersionId());
                if (null != t.getTaskType()){
                    map.put("typeDesc",t.getTaskType().getTypeDesc());
                }else {
                    map.put("typeDesc",null);
                }
                map.put("voiceDown",t.getVoiceDown());
                map.put("voiceStart",t.getVoiceStart());
                list.add(map);
            }
        }else {
            code = BaseConstants.GWSCODE2002;
            message = BaseConstants.GWSMSG2002;
        }

        PageInfo pageInfo = new PageInfo(taskMusic);

        Map<String,Object> maps = Maps.newHashMap();
        maps.put("list",list);
        maps.put("hasNextPage",pageInfo.isHasNextPage());

        return new BaseResult(code,message,maps);
    }

}
