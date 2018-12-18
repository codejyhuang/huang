package com.hrym.rpc.auth.service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import com.hrym.common.base.BaseConstants;
import com.hrym.common.base.BaseResult;
import com.hrym.common.base.PageInfo;
import com.hrym.common.enums.CustomTaskEnum;
import com.hrym.common.enums.TaskAreaEnum;
import com.hrym.common.logUtil.GwsLogger;
import com.hrym.common.logUtil.GwsLoggerTypeEnum;
import com.hrym.common.util.DateUtil;
import com.hrym.common.util.NumUtil;
import com.hrym.common.util.RedisUtil;
import com.hrym.rpc.app.common.constant.TaskParam;
import com.hrym.rpc.app.common.constant.UcenterConstant;
import com.hrym.rpc.app.dao.model.task.*;
import com.hrym.rpc.app.dao.model.view.TaskHomeView;
import com.hrym.rpc.auth.api.TaskAddService;
import com.hrym.rpc.auth.dao.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.util.*;

/**
 * 功课添加业务层
 * Created by mj on 2017/7/6.
 */
public class TaskAddServiceImpl implements TaskAddService {

    @Autowired
    private TaskPlanMapper taskPlanMapper;
    @Autowired
    private TaskTypeMapper taskTypeMapper;
    @Autowired
    private TaskItemMapper taskItemMapper;
    @Autowired
    private LoginMapper loginMapper;
    @Autowired
    private TaskContentMapper taskContentMapper;
    @Autowired
    private GongFoMusicMapper gongFoMusicMapper;
    @Autowired
    private TaskHomeViewMapper taskHomeViewMapper;
    @Autowired
    private TaskAreaMapper taskAreaMapper;
    @Autowired
    private CustomTaskMapper customTaskMapper;
    @Autowired
    private UserMapper userMapper;

    /**
     * 功课主页
     *
     * @param
     * @return
     */
    @Override
    public BaseResult findTasks(TaskParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        //Redis中获取用户对象
        UserInfo user = JSON.parseObject(RedisUtil.get(param.getToken()),UserInfo.class);
        //更新最近在线时间
        userMapper.updateTimeByUuid(DateUtil.currentSecond(),user.getUuid());
        // 核心分页代码
        PageHelper.startPage(param.getPageNo(), BaseConstants.PAGE_SIZE);
        List<TaskHomeView> thv = taskHomeViewMapper.findAllByUuidAndIsExit(user.getUuid());
        List<Map<String, Object>> list = new ArrayList<>();
        if (0 < thv.size()) {
            for (TaskHomeView t : thv) {
                String percent = "0%";
                if (t.getDoneNum() != 0) {
                    percent = NumUtil.getPercent(t.getDoneNum(), t.getPlanTarget());
                }
                int status = 1;
                int currentTime = DateUtil.currentSecond();
                String newStartTime = null;
                if (null != t.getStartTime() && 0 < t.getPlanPeriod()) {
                    //将int时间转换为string类型
                    String startTime = DateUtil.timestampToDates(t.getStartTime(), DateUtil.DATA_PATTON_YYYYMMDD2);
                    //n天后时间
                    String afterAnyDay = DateUtil.getAfterAnyDay(startTime, t.getPlanPeriod(), DateUtil.DATA_PATTON_YYYYMMDD2);
                    long newAfterAnyDay = DateUtil.DateToLinuxTime(afterAnyDay, DateUtil.DATA_PATTON_YYYYMMDD2);
                    //判断开始时间是否过期
                    if (newAfterAnyDay >= currentTime) {
                        status = 1;
                    } else {
                        status = 2;
                        newStartTime = "已逾期";
                    }
                }

                //今日建议数量
                int targetNum = 0;
                if (t.getPlanTarget() != 0 && t.getPlanPeriod() != 0 && t.getStartTime() != null){
                    //redis中去获取今日建议
                    String redisKey = String.valueOf(param.getTaskId())+ UcenterConstant.VERIFY_TASK_PLAN;
                    String value = RedisUtil.get(redisKey);
                    if (org.apache.commons.lang.StringUtils.isNotBlank(value)){
                        targetNum = Integer.valueOf(value);
                    }else {
                        targetNum = NumUtil.getTargetNum(t.getPlanTarget(),t.getPlanPeriod(),t.getDoneNum(),t.getStartTime());
                        RedisUtil.remove(redisKey);
                        RedisUtil.set(redisKey,String.valueOf(targetNum),DateUtil.getHours("24:00:00"));
                    }
                }

                Map<String, Object> maps = Maps.newHashMap();
                t.setTargetNum(targetNum);
                if (status == 2){
                    maps.put("targetNum","--");
                }else {
                    maps.put("targetNum",t.getTargetNum());
                }

                //拼接功课名称和做功课进度
                String expression = "第" + (int) t.getDoneNum() + "/" + (int) t.getPlanTarget() + t.getUnitDesc();
                String nameStr = t.getTypeName() + "·" + t.getItemName();

                if (null != t.getTypeId()){
                    if (t.getTypeId() == 10001 || t.getTypeId() == 10002){
                        nameStr = t.getTypeName();
                    }
                    if (t.getTypeId() == 10003) {
                        TaskContent taskContent = taskContentMapper.findByContentId(t.getItemContentId());
                        maps.put("version", taskContent.getVersion());
                        maps.put("videoFile", taskContent.getVideoFile());
                        maps.put("contentVersion", taskContent.getContentVersion());
                        maps.put("isSupport", t.getIsSupport());
                        maps.put("itemContentId", taskContent.getItemContentId());
                        expression = "已供奉" + (int) t.getDoneNum() + t.getUnitDesc();
                    }else if (t.getTypeId() == 10006){

                        TaskContent taskContent = taskContentMapper.findByItemId(t.getItemId());
                        if (null != taskContent) {
                            maps.put("fileTxt", taskContent.getFileTxt());
                        }
                    }else if (t.getTypeId() == 10007){

                        expression = "已修行" + (int) t.getDoneNum() + t.getUnitDesc();
                        nameStr = t.getTypeName();
                    }else if (t.getTypeId() == 10010){
                        t.setItemId(t.getCustomId());
                        t.setItemName(t.getCustomName());
                        nameStr = t.getCustomName();
                    }
                }
                maps.put("countingMethod", t.getCountingMethod());
                maps.put("percent", percent);
                maps.put("typeId", t.getTypeId());
                maps.put("itemId", t.getItemId());
                maps.put("taskId", t.getTaskId());
                maps.put("startTime", newStartTime);
                maps.put("status", status);
                maps.put("itemName", t.getItemName());
                maps.put("typeName", t.getTypeName());
                maps.put("doneNum", t.getDoneNum());
                maps.put("planTarget", t.getPlanTarget());
                maps.put("isChose", t.getMusicId());
                maps.put("remainCron", t.getRemainCron());
                maps.put("todayCommitNum",t.getTodayCommitNum());
                maps.put("expression", expression);
                maps.put("nameStr", nameStr);
                if (null == t.getRecentAdd()){
                    t.setRecentAdd(0);
                }
                maps.put("recentAdd",t.getRecentAdd());
                list.add(maps);
            }
        }
        PageInfo pageInfo = new PageInfo(thv);
        //库里查询用户是否有功课历史记录
        List<TaskPlan> taskPlans = taskPlanMapper.findAllTaskPlanRecordByUuid(user.getUuid());

        Map<String, Object> map = Maps.newHashMap();
        map.put("ret", list);
        map.put("hasNextPage", pageInfo.isHasNextPage());
        if (taskPlans.size() > 0){
            map.put("historyStatus",1);
        }else {
            map.put("historyStatus",0);
        }

        return new BaseResult(code, message, map);
    }

    /**
     * 功课类型列表
     *
     * @param
     * @return
     */
    @Override
    public BaseResult findTaskTypes() {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        List<TaskType> typeList = taskTypeMapper.findTaskTypes();

        List<Map<String, Object>> list = new ArrayList<>();
        for (TaskType tt : typeList){
            Map<String, Object> map = Maps.newHashMap();
            map.put("img",tt.getImg());
            if (StringUtils.isEmpty(tt.getTypeDesc())){
                map.put("typeDesc",null);
            }else {
                map.put("typeDesc",tt.getTypeDesc().substring(0,tt.getTypeDesc().length()-1));
            }
            map.put("typeId",tt.getTypeId());
            map.put("typeName",tt.getTypeName());
            list.add(map);
        }
        Map<String, Object> map = Maps.newHashMap();
        map.put("typeList", list);

        return new BaseResult(code, message, map);
    }

    /**
     * 功课类型介绍
     *
     * @param param
     * @return
     */
    @Override
    public BaseResult taskTypeIntro(TaskParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        TaskType taskType = taskTypeMapper.findTaskTypeById(param.getTypeId());

        return new BaseResult(code, message, taskType);
    }

    /**
     * 功课内容详细信息
     *
     * @param param
     * @return
     */
    @Override
    public BaseResult findTaskInfoById(TaskParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        TaskItem taskItem = taskItemMapper.findTaskItemById(param.getItemId(), param.getTypeId());

        Map<String, Object> map = Maps.newHashMap();
        if (null != taskItem) {
            if (null != taskItem.getType()) {
                map.put("typeName", taskItem.getType().getTypeName());
            } else {
                map.put("typeName", null);
            }
            if (null != taskItem.getType() && taskItem.getType().getTypeId() == 10006) {
                //查询内容TXT文档（只有经书用）
                TaskContent taskContent = taskContentMapper.findByItemId(param.getItemId());
                if (null != taskContent) {
                    map.put("fileTxt", taskContent.getFileTxt());
                } else {
                    map.put("fileTxt", null);
                }
            } else {
                map.put("fileTxt", null);
            }
            map.put("itemId", taskItem.getItemId());
            map.put("itemIntro", taskItem.getItemIntro());
            map.put("itemName", taskItem.getItemName());
            map.put("itemPic", taskItem.getItemPic());
            map.put("titleDesc", taskItem.getTitleDesc());
            map.put("isSupport", taskItem.getIsSupport());

        }
        return new BaseResult(code, message, map);
    }

    /**
     * 添加功课
     *
     * @param param
     * @return
     */
    @Override
    public BaseResult addTaskPlan(TaskParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        //Redis中获取用户对象
        UserInfo user = JSON.parseObject(RedisUtil.get(param.getToken()),UserInfo.class);

        TaskPlan taskPlan = new TaskPlan();
        if (null != param.getItemId()) {
            taskPlan.setItemId(param.getItemId());
        }
        if (null != user) {
            taskPlan.setUuid(user.getUuid());
        }
        if (null != param.getPlanPeriod()) {
            taskPlan.setPlanPeriod(param.getPlanPeriod());
        }
        if (null != param.getPlanTarget()) {
            taskPlan.setPlanTarget(param.getPlanTarget());
        }
        if (!StringUtils.isEmpty(param.getRemainCron())) {
            taskPlan.setRemainCron(param.getRemainCron());
        }
        if (!StringUtils.isEmpty(param.getStartTime())) {
            taskPlan.setStartTime(DateUtil.getDateToLinuxTime(param.getStartTime(), DateUtil.DATA_PATTON_YYYYMMDD2));
        }else {
            taskPlan.setStartTime(DateUtil.currentSecond());
        }
        if (null != param.getItemContentId()) {
            taskPlan.setItemContentId(param.getItemContentId());
        }
        //1表示最近添加  0表示已点击过
        taskPlan.setRecentAdd(1);
        taskPlan.setTypeId(param.getTypeId());
        taskPlan.setCreateTime(DateUtil.currentSecond());
        taskPlan.setUpdateTime(DateUtil.currentSecond());
        //0:已删除；1：未删除
        taskPlan.setIsExit(1);

        Map<String,Object> map = Maps.newHashMap();
        //1：普通功课添加；2：自定义功课添加
        if (null != param.getTypeId() && 10010 == param.getTypeId()){
            //创建自定义功课实例
            CustomTask customTask = new CustomTask();
            customTask.setCustomName(param.getCustomName());
            if (null != user){
                customTask.setUserId(user.getUuid());
            }
            customTask.setCreateTime(DateUtil.currentSecond());
            customTask.setUpdateTime(DateUtil.currentSecond());
            customTask.setTypeId(param.getTypeId());
            //保存自定义功课
            customTaskMapper.insert(customTask);
            int id = customTaskMapper.getLastId();
            taskPlan.setCustomId(id);
            //添加功课计划
            taskPlanMapper.saveTaskPlan(taskPlan);
            int taskId = taskPlanMapper.getLastId();

            map.put("itemName",param.getCustomName());
            map.put("typeName","自定义功课");
            map.put("taskId",taskId);
        }else {
            //判断该功课是否已添加
            TaskPlan plan = taskPlanMapper.findTaskByItemIdAndTypeIdAndUuid(param.getItemId(),param.getTypeId(),user.getUuid());
            if (null == plan){
                //添加功课计划
                taskPlanMapper.saveTaskPlan(taskPlan);
                int taskId = taskPlanMapper.getLastId();
                map.put("taskId",taskId);
                //更新订制数量
                taskItemMapper.updateOrderNumById(param.getItemId());
            }

            if (null != param.getItemId()){
                TaskItem item = taskItemMapper.findTaskItemById(param.getItemId(),param.getTypeId());
                map.put("itemName",item.getItemName());
                map.put("typeName",item.getType().getTypeName());
            }
            if (null != param.getTypeId() && 10006 == param.getTypeId()){
                //咒语返回TXT文件
                TaskContent taskContent = taskContentMapper.findByItemId(param.getItemId());
                if (null != taskContent) {
                    map.put("fileTxt", taskContent.getFileTxt());
                }
            }
        }


        return new BaseResult(code, message, map);
    }

    /**
     * 基于类型的模糊搜索
     *
     * @param param
     * @return
     */
    @Override
    public BaseResult findTaskItemById(TaskParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        // 核心分页代码
        PageHelper.startPage(param.getPageNo(), BaseConstants.PAGE_SIZE);
        List<TaskType> taskType = taskTypeMapper.findTaskItemById(param.getTypeId(), param.getItemName());
        PageInfo pageInfo = new PageInfo(taskType);

        TaskType data = null;
        if (taskType.size() != 0) {
            data = taskType.get(0);
            data.setHasNextPage(pageInfo.isHasNextPage());
        }
        return new BaseResult(code, message, data);
    }

    /**
     * 设置提醒时间表达式
     *
     * @param param
     * @return
     */
    @Override
    public BaseResult updateRemainCron(TaskParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        TaskPlan taskPlan = new TaskPlan();
        if (null != param.getTaskId() && null != param.getRemainCron()) {
            taskPlan.setRemainCron(param.getRemainCron());
            taskPlan.setUpdateTime(DateUtil.currentSecond());
            taskPlan.setTaskId(param.getTaskId());
            taskPlanMapper.updateRemainCronById(taskPlan);
        } else {
            code = BaseConstants.GWSCODE2002;
            message = BaseConstants.GWSMSG2002;
        }
        return new BaseResult(code, message, null);
    }

    /**
     * 功课版本
     *
     * @param param
     * @return
     */
    @Override
    public BaseResult findItemVersion(TaskParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        List<Map<String, Object>> list = new ArrayList<>();
        if (null != param.getItemId()) {
            List<TaskContent> taskContents = taskContentMapper.findContentById(param.getItemId());
            for (TaskContent t : taskContents) {
                //判断供佛无音频或者版本图片不显示
                if (param.getTypeId() != null && param.getTypeId() == 10003) {
                    if (StringUtils.isEmpty(t.getVideoFile())) {
                        continue;
                    }
                }
                if (param.getTypeId() != null && param.getTypeId() == 10004) {
                    if (StringUtils.isEmpty(t.getText())) {
                        continue;
                    }
                }
                Map<String, Object> map = Maps.newHashMap();
                map.put("filePic", t.getFilePic());
                map.put("fileTxt", t.getFileTxt());
                map.put("itemId", t.getItemId());
                map.put("picVersionName", t.getVersionName());
                map.put("text", t.getText());
                map.put("itemContentId", t.getItemContentId());
                map.put("itemName", t.getTaskItem().getItemName());
                map.put("contentVersion", t.getContentVersion());
                map.put("videoFile", t.getVideoFile());
                map.put("version", t.getVersion());
                list.add(map);
            }
        } else {
            code = BaseConstants.GWSCODE2002;
            message = BaseConstants.GWSMSG2002;
        }
        Map<String, Object> maps = new HashMap<>();
        maps.put("versionList", list);

        return new BaseResult(code, message, maps);
    }

    /**
     * 判断是否添加功课
     *
     * @param param
     * @return
     */
    @Override
    public BaseResult isAddTask(TaskParam param) {

        return checkTaskAdd(param.getItemId(),param.getTypeId(),param.getToken());
    }


    /**
     * 检查用户是否重复添加过该功课
     * @param itemId
     * @param typeId
     * @param token
     * @return
     */
    public BaseResult checkTaskAdd(Integer itemId,Integer typeId,String token){

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        //Redis中获取用户对象
        UserInfo user = JSON.parseObject(RedisUtil.get(token),UserInfo.class);

        if (null == itemId || null == typeId) {
            return new BaseResult(BaseConstants.GWSCODE2002, BaseConstants.GWSMSG2002, null);
        }
        TaskPlan taskPlan = taskPlanMapper.findTaskByItemIdAndTypeIdAndUuid(itemId,typeId,user.getUuid());
        if (null != taskPlan){
            return new BaseResult(BaseConstants.GWSCODE5001, BaseConstants.GWSMSG5001, null);
        }

        return new BaseResult(code, message, null);
    }

    /**
     * 获取供佛音频
     *
     * @return
     */
    @Override
    public BaseResult getGongFoMusic(TaskParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        List<GongFoMusic> gongFoMusic = gongFoMusicMapper.findGongFoMusic();

        Map<String, Object> maps = new HashMap<>();

        if (null != param.getType()) {
            List<Map<String, Object>> list = new ArrayList<>();
            for (GongFoMusic gf : gongFoMusic) {
                if (param.getType() == gf.getVersion()) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("musicId", gf.getMusicId());
                    map.put("musicFile", gf.getMusicFile());
                    map.put("musicVersion", gf.getMusicVersion());
                    map.put("version", gf.getVersion());
                    list.add(map);
                }
            }
            maps.put("list", list);
        } else {
            maps.put("list", gongFoMusic);
        }

        return new BaseResult(code, message, maps);
    }

    /**
     * 添加功课专区列表
     * @param param
     * @return
     */
    @Override
    public BaseResult getTaskArea(TaskParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        Map<String,Object> map = new HashMap<>();
        List<TaskArea> areaList = null;
        if (TaskAreaEnum.XIUXING_AREA.getType() == param.getType()){
            PageHelper.orderBy("a.update_time desc");
            areaList = taskAreaMapper.findTaskAreaByAreaType(param.getType());
        }else {
            if (TaskAreaEnum.HOT_TASK_AREA.getType() == param.getType()){
                //根据专区类型从库里查询功课专区信息 按更新时间倒序排列
                PageHelper.startPage(BaseConstants.PAGE_NUM,BaseConstants.PAGE_SIZE3);
                PageHelper.orderBy("a.update_time desc");
                areaList = taskAreaMapper.findTaskAreaByAreaTypeAndUuid(param.getType());
                if (areaList.size() < 20){
                    //专区表不够20从功课计划中补足20个热门功课(内容和专区功课不能重复)
                    PageHelper.startPage(BaseConstants.PAGE_NUM,BaseConstants.PAGE_SIZE3-areaList.size());
                    List<TaskArea> list = taskAreaMapper.findHotTask();
                    for (TaskArea tk :list){
                        int currentPeopleNum = taskAreaMapper.getCurrentPeopleNum(tk.getItemId(),tk.getTypeId());
                        tk.setCurrentPeopleNum(currentPeopleNum);
                        areaList.add(tk);
                    }
                }
                //热门功课按使用人数排序
                Collections.sort(areaList, new Comparator<TaskArea>() {
                    @Override
                    public int compare(TaskArea o1, TaskArea o2) {
                        return o2.getDonePeopleNum().compareTo(o1.getDonePeopleNum());
                    }
                });
            }else {
                //根据专区类型从库里查询功课专区信息 按更新时间倒序排列
                PageHelper.startPage(BaseConstants.PAGE_NUM,BaseConstants.PAGE_SIZE2);
                PageHelper.orderBy("a.update_time desc");
                areaList = taskAreaMapper.findTaskAreaByAreaTypeAndUuid(param.getType());
            }
            //判断是否添加过该功课
            for (TaskArea tk : areaList){
                BaseResult ret = checkTaskAdd(tk.getItemId(),tk.getTypeId(),param.getToken());
                tk.setIsAdd(ret.getCode());
            }
        }
        //去掉功课类型描述中的句号
        for (TaskArea t : areaList){
            if (!StringUtils.isEmpty(t.getTypeDesc())){
                t.setTypeDesc(t.getTypeDesc().substring(0,t.getTypeDesc().length()-1));
            }
        }
        map.put("list",areaList);
        map.put("areaType",param.getType());

        return new BaseResult(code,message,map);
    }


    /**
     * 添加功课专区搜索
     * @param param
     * @return
     */
    @Override
    public BaseResult taskAreaSearch(TaskParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        PageHelper.startPage(param.getPageNo(),BaseConstants.PAGE_SIZE,"update_time desc");
        List<TaskItem> items = taskItemMapper.findItemLikeName(param.getItemName());

        for (TaskItem t : items){
            if (10001 == t.getTypeId() || 10002 == t.getTypeId() || 10007 == t.getTypeId()){
                t.setItemIntro(t.getIntroduceInfo());
            }
        }
        PageInfo pageInfo = new PageInfo(items);
        Map<String,Object> map = new HashMap<>();
        map.put("list",items);
        map.put("hasNextPage",pageInfo.isHasNextPage());

        return new BaseResult(code,message,map);
    }

    /**
     * 修改功课最近添加状态
     * @param param
     * @return
     */
    @Override
    public BaseResult editRecentAdd(TaskParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        if (null != param.getTaskId()){
            taskPlanMapper.updateRecentAdd(param.getTaskId());
        }else {
            code = BaseConstants.GWSCODE2002;
            message = BaseConstants.GWSMSG2002;
        }
        return new BaseResult(code,message,null);
    }

}
