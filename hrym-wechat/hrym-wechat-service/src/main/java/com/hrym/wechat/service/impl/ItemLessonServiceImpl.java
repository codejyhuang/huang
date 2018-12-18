package com.hrym.wechat.service.impl;

import com.github.pagehelper.PageHelper;
import com.hrym.common.base.PageInfo;
import com.hrym.common.util.DateUtil;
import com.hrym.common.util.LunarCalendar;
import com.hrym.common.util.NumUtil;
import com.hrym.common.util.TableUtil;
import com.hrym.wechat.entity.*;
import com.hrym.wechat.mapper.*;
import com.hrym.wechat.service.IItemLessonService;
import com.hrym.wechat.smallProgram.FlockCountReportParam;
import com.hrym.wechat.smallProgram.ItemParam;
import com.hrym.wechat.smallProgram.QueryObjectParam;
import com.hrym.wechat.smallProgram.WechatFlockParam;
import com.hrym.wechat.vo.FlockRecordVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.*;

@Service
public class ItemLessonServiceImpl implements IItemLessonService {

    @Autowired
    private ItemLessonMapper itemLessonMapper;
    @Autowired
    private FlockRecordMapper flockRecordMapper;
    @Autowired
    private FlockItemMapper flockItemMapper;
    @Autowired
    private FlockItemUserCountMapper countMapper;
    @Autowired
    private ItemUserUnitMapper itemUserUnitMapper;
    @Autowired
    private UserAccountMapper userAccountMapper;
    @Autowired
    private FlockItemUserCountMapper flockItemUserCountMapper;
    @Autowired
    private FlockMapper flockMapper;
    @Autowired
    private ParseRecordMapper parseRecordMapper;
    @Autowired
    private AppRecordCommonMapper appRecordCommonMapper;
    @Autowired
    private TaskCommonMapper appTaskCommonMapper;

    /**
     * 新建共修群   功课列表
     *
     * @param qo
     * @return
     */
    @Override
    public Map<String, Object> listByQo(QueryObjectParam qo) {
        Map<String, Object> map = new HashMap<>();

        PageHelper.startPage(qo.getCurrentPage(), 10);
        List<FlockLessonDO> flockLessonDOList = itemLessonMapper.queryLessonFromView(qo);
        for (FlockLessonDO lessonDO : flockLessonDOList) {
            lessonDO.setIsAdd(0);
        }
        PageInfo pageInfo = new PageInfo(flockLessonDOList);
        map.put("list", flockLessonDOList);
        map.put("hasNextPage", pageInfo.isHasNextPage());
        map.put("totalPage", pageInfo.getPages());
        map.put("total", pageInfo.getTotal());
        return map;
    }

    /**
     * 管理共修群   功课列表
     *
     * @param qo
     * @return
     */
    @Override
    public Map<String, Object> queryByQo(QueryObjectParam qo) {
        Map<String, Object> map = new HashMap<>();
        List<FlockItem> flockItemList = flockItemMapper.queryByFlockId(qo.getId());
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        List<FlockLessonDO> flockLessonDOList = itemLessonMapper.queryLessonFromView(qo);
        //遍历所有功课
        for (FlockLessonDO lessonDO : flockLessonDOList) {
//            Integer count =  flockItemUserCountMapper.queryCountByItemIdAndContentIdAndType(lessonDO.getType(),lessonDO.getItemId(),lessonDO.getItemContentId());
//            lessonDO.setFlockPeopleNum(count==null?0:count);
            lessonDO.setIsAdd(0);
            //遍历所有 群功课
            for (FlockItem flockItem : flockItemList) {
                if ((flockItem.getType().equals(flockItem.getType())
                        && flockItem.getType().equals(1)
                        && flockItem.getItemId().equals(lessonDO.getItemId()))

                        || (flockItem.getType().equals(flockItem.getType())
                        && flockItem.getType().equals(0)
                        && flockItem.getItemId().equals(lessonDO.getItemId())
                        && flockItem.getItemContentId().equals(lessonDO.getItemContentId()))

                        || (flockItem.getType().equals(flockItem.getType())
                        && flockItem.getType().equals(2)
                        && flockItem.getItemId().equals(lessonDO.getItemId())
                        && flockItem.getItemContentId().equals(lessonDO.getItemContentId()))
                ) {
                    lessonDO.setIsAdd(1);
                }
            }
        }
        PageInfo pageInfo = new PageInfo(flockLessonDOList);
        long totalPage = pageInfo.getPages();
        map.put("list", flockLessonDOList);
        map.put("totalPage", totalPage);
        return map;
    }

    /**
     * 功课详细信息
     *
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> flockAssignmentMessage(ItemParam param) {
        return getItemDynamic(param);
    }


//    /**
//     * 功课详细信息
//     *
//     * @param param
//     * @return
//     */
//    @Override
//    public Map<String, Object> flockAssignmentMessage(ItemParam param) {
//        Map<String, Object> map = new HashMap<>();
//        String tableName = "t_flock_record_" + DateUtil.getYear();
//        FlockLessonDO lessonDO = itemLessonMapper.queryLessonFromViewByItemIdAndItemContentIdAndType(param.getItemId(), param.getItemContentId(), param.getType());
//        PageHelper.startPage(param.getCurrentPage(), param.getPageSize());
//        List<FlockRecordDO> flockRecordDOList = new ArrayList<>();
//        if (param.getTimeType().equals(0)) {
//            flockRecordDOList = flockRecordMapper.queryByFlockIdAndItemIdAndItemContentIdAndType(param.getFlockId(), param.getItemId(), param.getItemContentId(), param.getType());
//            if (flockRecordDOList.size() != 0) {
//                for (FlockRecordDO flockRecordDO : flockRecordDOList) {
//                    flockRecordDO.setTimeStr(DateUtil.getTimeStrForWechat(DateUtil.formatIntDate(flockRecordDO.getCreateTime()), new Date()));
//                    flockRecordDO.setReportStr(NumUtil.getTotalNumStr(flockRecordDO.getNum(), "0"));
//                }
//            }
//        } else if (param.getTimeType().equals(4)) {
//            //查询累计
//            flockRecordDOList = countMapper.queryByFlockIdAndItemIdAndItemContentIdAndType(param.getFlockId(), param.getItemId(), param.getItemContentId(), param.getType());
//            Iterator<FlockRecordDO> iterator = flockRecordDOList.iterator();
//            while (iterator.hasNext()) {
//                FlockRecordDO recordDO = iterator.next();
//                //查询当前用户的量词
//                recordDO.setReportStr(NumUtil.getTotalNumStr(recordDO.getNum(), "0"));
//            }
//            FlockRecordDO userRecord = countMapper.queryByUuidFromItemCount(param.getFlockId(),
//                    param.getItemId(), param.getItemContentId(), param.getType(), param.getUserId());
//            if (userRecord != null) {
//                userRecord.setReportStr(NumUtil.getTotalNumStr(userRecord.getNum(), "0"));
//            }
//            map.put("userRecord", userRecord == null ? null : userRecord);
//        } else {
//            flockRecordDOList = flockRecordMapper.queryByFlockIdAndItemIdAndItemContentIdAndTimeType(param.getFlockId(),
//                    param.getItemId(), param.getItemContentId(), param.getType(), param.getTimeType(), tableName, DateUtil.getYMD(), DateUtil.getYear(), DateUtil.getMonth());
//            Iterator<FlockRecordDO> iterator = flockRecordDOList.iterator();
//            while (iterator.hasNext()) {
//                FlockRecordDO recordDO = iterator.next();
//                recordDO.setReportStr(NumUtil.getTotalNumStr(recordDO.getNum(), ""));
//            }
//            FlockRecordDO userRecord = flockRecordMapper.queryByUuid(param.getFlockId(),
//                    param.getItemId(), param.getItemContentId(), param.getType(), tableName,
//                    DateUtil.getYear(), DateUtil.getYMD(), DateUtil.getMonth(), param.getTimeType(), param.getUserId());
//            if (userRecord != null) {
//                userRecord.setReportStr(NumUtil.getTotalNumStr(userRecord.getNum(), "0"));
//            }
//            map.put("userRecord", userRecord == null ? null : userRecord);
//
//        }
//
//
//        if (map.get("userRecord") == null) {
//            WechatUserAccount wechatUserAccount = userAccountMapper.queryByUuid(param.getUserId());
//            //用户没有自己的上报记录时候
//            FlockRecordDO recordDO = new FlockRecordDO();
//            recordDO.setOrder(0);
//            recordDO.setReportStr("0");
//            recordDO.setAvatar(wechatUserAccount.getAvatar());
//            recordDO.setNickName(wechatUserAccount.getNickName());
//            map.put("userRecord", recordDO);
//        }
//
//        String unit = itemUserUnitMapper.queryUnitByUuidAndItemIdAndItemContentIdAndType(param.getUserId(), param.getItemId(),
//                param.getItemContentId(), param.getType());
//        map.put("unit", unit == null ? "遍" : unit);
//        map.put("lessonName", lessonDO.getLessonName());
//        map.put("hasNextPage", new PageInfo<>(flockRecordDOList).isHasNextPage());
//        map.put("totalPage", new PageInfo<>(flockRecordDOList).getPages());
//        map.put("total", new PageInfo<>(flockRecordDOList).getTotal());
//        map.put("recordList", flockRecordDOList);
//        return map;
//    }


    /**
     * 我的功课  task
     *
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> selfItem(ItemParam param) {
        return getMyTask(param.getUserId());
    }

    /**
     * 我的功课  添加task   功课列表
     *
     * @param qo
     * @return
     */
    @Override
    public Map<String, Object> selfItemList(QueryObjectParam qo) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> myTask = getMyTask(qo.getUuid());
        List<TaskCommon> lessonList = (List<TaskCommon>) myTask.get("lessonList");
        PageHelper.startPage(qo.getCurrentPage(), 10);
        List<FlockLessonDO> flockLessonDOList = itemLessonMapper.queryLessonFromView(qo);
        for (FlockLessonDO lessonDO : flockLessonDOList) {
            lessonDO.setIsAdd(0);
            for (TaskCommon taskCommon : lessonList) {
                if (taskCommon.getType().equals(lessonDO.getType())
                        && taskCommon.getItemId().equals(lessonDO.getItemId())
                        && taskCommon.getItemContentId().equals(taskCommon.getItemContentId())) {
                    lessonDO.setIsAdd(1);
                }
            }
        }
        PageInfo pageInfo = new PageInfo(flockLessonDOList);
        map.put("list", flockLessonDOList);
        map.put("hasNextPage", pageInfo.isHasNextPage());
        map.put("totalPage", pageInfo.getPages());
        map.put("total", pageInfo.getTotal());
        return map;
    }

    /**
     * 我的功课  添加 task 页面
     *
     * @param param
     */
    @Override
    public void addTask(ItemParam param) {
        String tableName = TableUtil.getTaskPlanTableNameByItemType(param.getType());
        int count = appTaskCommonMapper.queryCountByItemIdAndItemContentIdAndTableNameAndUuid(param.getType(), param.getItemId(), param.getItemContentId(), param.getUserId(), tableName, null);
        //未删除的为零
        if (count == 0) {
                //全部状态为0  创建一条
                TaskCommon taskCommon = new TaskCommon();
                taskCommon.setItemId(param.getItemId());
                taskCommon.setItemContentId(param.getItemContentId());
                taskCommon.setCreateTime(DateUtil.currentSecond());
                taskCommon.setUuid(param.getUserId());
                appTaskCommonMapper.insert(taskCommon, tableName, param.getType());
        } else {
            int existCount = appTaskCommonMapper.queryCountByItemIdAndItemContentIdAndTableNameAndUuid(param.getType(), param.getItemId(), param.getItemContentId(), param.getUserId(), tableName, 1);
            if (existCount == 0) {
                if (param.getType().equals(2)) {
                    //有未删除   更新
                    appTaskCommonMapper.updateBookIsExist(param.getType(), param.getItemId(), param.getItemContentId(), param.getUserId(), tableName);
                } else {
                    //有未删除   更新
                    appTaskCommonMapper.updateIsExist(param.getType(), param.getItemId(), param.getItemContentId(), param.getUserId(), tableName);
                }
            }

        }
    }

    @Override
    public void removeTask(ItemParam param) {
        if (param.getId() != null) {
            String tableName = TableUtil.getTaskPlanTableNameByItemType(param.getType());
            appTaskCommonMapper.updateStateByPrimaryKey(param.getId(), 0, param.getType(), tableName);
        }
    }


    /**qu
     * 自修详情 卡片
     *
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> selfRepairCard(ItemParam param) {
        Map<String, Object> map = new HashMap<>();
        String tableName = "";
        //用户改功课始修时间    待确定  查询改task  的创建时间
        List<TaskCommon> taskCommonList = new ArrayList<>();
        if (param.getType().equals(2)) {
            //  经书
            taskCommonList = appTaskCommonMapper.queryTaskByUuidFromBookCase(param.getUserId(), DateUtil.currentSecond(), param.getId());
            tableName = "t_task_record_book";
        } else {
            //  系统/自建功课
            taskCommonList = appTaskCommonMapper.queryTaskByUuidFromView(param.getUserId(), param.getId());
            tableName = "t_task_record_custom";
            if (param.getType().equals(0)) {
                //系统功课
                tableName = "t_task_record_lesson";
            }
        }
        String timeStr = "";
        if (taskCommonList.size() != 0) {
            timeStr = DateUtil.timestampToDates(taskCommonList.get(0).getCreateTime(), DateUtil.DATA_PATTON_YYYYMMDD2);
        } else {
            timeStr = DateUtil.timestampToDates(1543457426, DateUtil.DATA_PATTON_YYYYMMDD2);
            ;
        }

        //用户该功课今日报数量  未删除
        Long dayNum = appRecordCommonMapper.queryByUuidAndItemIdAndItemIdAndTypeAndTableName(param.getId(),
                0, DateUtil.getYMD(), tableName);
        //用户该功课今日报数量  未删除
        Long monthNum = appRecordCommonMapper.queryByUuidAndItemIdAndItemIdAndTypeAndTableName(param.getId(),
                1, DateUtil.getMonth(), tableName);
        //用户该功课今日报数量  未删除
        Long yearNum = appRecordCommonMapper.queryByUuidAndItemIdAndItemIdAndTypeAndTableName(param.getId(),
                2, DateUtil.getYear(), tableName);
        //用户该功课今日报数量  未删除
        Long totalNum = appRecordCommonMapper.queryByUuidAndItemIdAndItemIdAndTypeAndTableName(param.getId(),
                4, DateUtil.getYMD(), tableName);

        map.put("beginTimeStr", timeStr + "   " + DateUtil.dateToWeek(timeStr, DateUtil.DATA_PATTON_YYYYMMDD2));
        map.put("dayNumStr", NumUtil.getTotalNumStr(new Double(dayNum == null ? 0 : dayNum), "0"));
        map.put("monthNumStr", NumUtil.getTotalNumStr(new Double(monthNum == null ? 0 : monthNum), "0"));
        map.put("yearNumStr", NumUtil.getTotalNumStr(new Double(yearNum == null ? 0 : yearNum), "0"));
        map.put("totalNumStr", NumUtil.getTotalNumStr(new Double(totalNum == null ? 0 : totalNum), "0"));
        return map;
    }


    /**
     * 自修详情历史记录
     *
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> selfRepairHistory(ItemParam param) {
        Map<String, Object> map = new HashMap<>();
        //查询用户功课量词
        String unit = itemUserUnitMapper.queryUnitByUuidAndItemIdAndItemContentIdAndType(param.getUserId(), param.getItemId(), param.getItemContentId(), param.getType());
        String tableName = TableUtil.getTaskRecordTableNameByItemType(param.getType());
        PageHelper.startPage(param.getCurrentPage(), param.getPageSize());
        List<AppRecordCommon> appRecordCommons = appRecordCommonMapper.queryRecordByTaskId(param.getId(), tableName);
        PageInfo pageInfo = new PageInfo(appRecordCommons);
        map.put("recordList", appRecordCommons);
        map.put("total", pageInfo.getTotal());
        map.put("totalPage", pageInfo.getPages());
        map.put("hasNextPage", pageInfo.isHasNextPage());
        map.put("unit", unit == null ? "遍" : unit);
        return map;
    }

    /**
     * 逻辑删除 record   删除app record  更新 app task  done_num
     *
     * @param param
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void removeSelfRepairHistory(ItemParam param) {

        String appRecordTableName = TableUtil.getTaskRecordTableNameByItemType(param.getType());
        String appTaskTableName = TableUtil.getTaskPlanTableNameByItemType(param.getType());
        //删除 app 中的record
        appRecordCommonMapper.removeByPrimaryKey(param.getRecordId(), appRecordTableName);
        //更新 task 中的任务完成数量
        appTaskCommonMapper.updateDoneNumByTaskId(param.getId(), param.getNum(), appTaskTableName, param.getType(),DateUtil.timestampToDates(DateUtil.currentSecond(),DateUtil.DATA_PATTON_YYYYMMDD).equals(param.getYmd())?1:0);

        //逻辑删除  小程序上报记录
        int rows = flockRecordMapper.logicRemoveByPrimaryKey(param.getRecordId(), "t_flock_record_" + param.getYear());

    }


    /**
     * 共修详情  用户群列表
     *
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> itemFlockList(ItemParam param) {
        Map<String, Object> map = new HashMap<>();
        List<Flock> flockList = flockMapper.queryByItemIdAndContentIdAndUuid(param.getItemId(), param.getItemContentId(), param.getType(), param.getUserId());
        map.put("flockList", flockList);
        return map;
    }


    /**
     * 共修详情  共修群功课详情 卡片
     *
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> flockItemMessageCard(ItemParam param) {
        Map<String, Object> map = new HashMap<>();
        Flock flock = flockMapper.queryByPrimaryKey(param.getFlockId());

        String timeStr = DateUtil.timestampToDates(flock.getCreateTime(), DateUtil.DATA_PATTON_YYYYMMDD2);
        WechatUserAccount createUser = flock.getCreateUser();
        if (createUser ==null){
            createUser=new WechatUserAccount();
            createUser.setNickName("师兄");
            createUser.setAvatar("http://upd.everglamming.com:8089/group1/M00/00/13/wKgAHFoBUH-ARZFiAAC0d7SdfuU124.jpg");
        }
        map.put("isAdmin", param.getUserId().equals(createUser.getUuid()) ? 1 : 0);
        map.put("createName", createUser.getNickName());
        map.put("createAvatar", createUser.getAvatar());
        map.put("intro", flock.getIntro());
        map.put("flockId",flock.getId());
        map.put("privacy",flock.getPrivacy());
        map.put("timeStr", timeStr + "   " + DateUtil.dateToWeek(timeStr, DateUtil.DATA_PATTON_YYYYMMDD2));
        try {
            map.put("dayNum", DateUtil.daysBetween(flock.getCreateTime(), DateUtil.currentSecond()) + "天");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return map;
    }


    /**
     * 共修详情 共修群功课  可点赞动态
     *
     * @param
     * @return
     */
    @Override
    public Map<String, Object> flockItemMessageRecord(ItemParam param) {
        return getItemDynamic(param);
    }

    /**
     * 查询点赞用户
     *
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> queryLikeMember(ItemParam param) {
        Map<String, Object> map = new HashMap<>();
        List<ParseRecord> parseRecords = parseRecordMapper.queryLikeMember(param.getId());
        map.put("likeMembers", parseRecords);
        return map;
    }

    /**
     * 点赞取消赞
     *
     * @param param
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void parseRecord(ItemParam param) {
        String tableName = "t_flock_record_" + param.getYear();
        if (param.getType().equals(1)) {   // 1:点赞   0:取消赞
            //点赞操作
            ParseRecord ps = parseRecordMapper.queryByRelationIdAndRelationTypeAndFrom(param.getId(), //记录的id  recordId
                    param.getUserId());
            if (ps == null) {
                ps = new ParseRecord();
                ps.setFrom(param.getUserId());
                ps.setTime(DateUtil.currentSecond());
                ps.setRelationId(param.getId().toString());
                parseRecordMapper.insert(ps);
                //更新record
                flockRecordMapper.updateLikeNumByPrimaryKey(param.getId(), tableName, 1);
            }
        } else {
            //取消赞
            int rows = parseRecordMapper.removeByRelationIdAndUuid(param.getId(), param.getUserId());
            //更新record
            flockRecordMapper.updateLikeNumByPrimaryKey(param.getId(), tableName, -rows);
        }
    }


    /**
     * 获取上报记录动态数据
     *
     * @param param
     * @return
     */
    public Map<String, Object> getItemDynamic(ItemParam param) {
        Map<String, Object> map = new HashMap<>();
        String tableName = "t_flock_record_" + DateUtil.getYear();
        FlockLessonDO lessonDO = itemLessonMapper.queryLessonFromViewByItemIdAndItemContentIdAndType(param.getItemId(), param.getItemContentId(), param.getType());
        PageHelper.startPage(param.getCurrentPage(), param.getPageSize());
        List<FlockRecordDO> flockRecordDOList = new ArrayList<>();
        if (param.getTimeType().equals(0)) {
            flockRecordDOList = flockRecordMapper.queryByFlockIdAndItemIdAndItemContentIdAndType(param.getFlockId(), param.getItemId(), param.getItemContentId(), param.getType(), param.getUserId());
            if (flockRecordDOList.size() != 0) {
                for (FlockRecordDO flockRecordDO : flockRecordDOList) {
                    flockRecordDO.setTimeStr(DateUtil.getTimeStrForWechat(DateUtil.formatIntDate(flockRecordDO.getCreateTime()), new Date()));
                    flockRecordDO.setReportStr(NumUtil.getTotalNumStr(flockRecordDO.getNum(), "0"));
                }
            }
        } else if (param.getTimeType().equals(4)) {
            //查询累计
            flockRecordDOList = countMapper.queryByFlockIdAndItemIdAndItemContentIdAndType(param.getFlockId(), param.getItemId(), param.getItemContentId(), param.getType(), param.getUserId());
            Iterator<FlockRecordDO> iterator = flockRecordDOList.iterator();
            while (iterator.hasNext()) {
                FlockRecordDO recordDO = iterator.next();
                recordDO.setReportStr(NumUtil.getTotalNumStr(recordDO.getNum(), "0"));
            }
            FlockRecordDO userRecord = countMapper.queryByUuidFromItemCount(param.getFlockId(),
                    param.getItemId(), param.getItemContentId(), param.getType(), param.getUserId());
            if (userRecord != null) {
                userRecord.setReportStr(NumUtil.getTotalNumStr(userRecord.getNum(), "0"));
            }
            map.put("userRecord", userRecord == null ? null : userRecord);
        } else {
            flockRecordDOList = flockRecordMapper.queryByFlockIdAndItemIdAndItemContentIdAndTimeType(param.getFlockId(),
                    param.getItemId(), param.getItemContentId(), param.getType(), param.getTimeType(), tableName, DateUtil.getYMD(), DateUtil.getYear(), DateUtil.getMonth());
            Iterator<FlockRecordDO> iterator = flockRecordDOList.iterator();
            while (iterator.hasNext()) {
                FlockRecordDO recordDO = iterator.next();
                recordDO.setReportStr(NumUtil.getTotalNumStr(recordDO.getNum(), ""));
            }
            FlockRecordDO userRecord = flockRecordMapper.queryByUuid(param.getFlockId(),
                    param.getItemId(), param.getItemContentId(), param.getType(), tableName,
                    DateUtil.getYear(), DateUtil.getYMD(), DateUtil.getMonth(), param.getTimeType(), param.getUserId());
            if (userRecord != null) {
                userRecord.setReportStr(NumUtil.getTotalNumStr(userRecord.getNum(), "0"));
            }
            map.put("userRecord", userRecord == null ? null : userRecord);

        }
        if (map.get("userRecord") == null) {
            WechatUserAccount wechatUserAccount = userAccountMapper.queryByUuid(param.getUserId());
            //用户没有自己的上报记录时候
            FlockRecordDO recordDO = new FlockRecordDO();
            recordDO.setOrder(0);
            recordDO.setReportStr("0");
            recordDO.setAvatar(wechatUserAccount.getAvatar());
            recordDO.setNickName(wechatUserAccount.getNickName());
            map.put("userRecord", recordDO);
        }
        String unit = itemUserUnitMapper.queryUnitByUuidAndItemIdAndItemContentIdAndType(param.getUserId(), param.getItemId(),
                param.getItemContentId(), param.getType());
        map.put("unit", unit == null ? "遍" : unit);
        map.put("lessonName", lessonDO.getLessonName());
        map.put("hasNextPage", new PageInfo<>(flockRecordDOList).isHasNextPage());
        map.put("totalPage", new PageInfo<>(flockRecordDOList).getPages());
        map.put("total", new PageInfo<>(flockRecordDOList).getTotal());
        map.put("recordList", flockRecordDOList);
        return map;
    }


    /**
     * 所有功课
     * @param userId
     * @return
     */
    public Map<String, Object> getMyTask(Integer userId) {
        Map<String, Object> map = new HashMap<>();
        List<TaskCommon> taskCommonList = appTaskCommonMapper.queryTaskByUuidFromView(userId, null);
        Integer dayReportItem = 0;
        if (taskCommonList.size()!=0){
            for (TaskCommon taskCommon : taskCommonList) {
                dayReportItem += taskCommon.getTodayCommitNum()==0 ? 0 : 1;
            }
        }
        WechatUserAccount userInfo = userAccountMapper.queryByUuid(userId);
        String strs = DateUtil.format(new Date(), "yyyy年MM月dd日") + "  " + DateUtil.dateToWeek() + "  ";
        LunarCalendar cal = new LunarCalendar();
        map.put("lessonList", taskCommonList);
        map.put("itemNum", taskCommonList.size());
        map.put("dayDoneNum", dayReportItem);
        map.put("userInfo", userInfo);
        map.put("timeStr",strs + cal.getStr());
        return map;
    }


    /**
     * 我的功课列表  分页
     * @param param
     * @return
     */
    public Map<String, Object> getMyTask(ItemParam param) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(param.getCurrentPage(),param.getPageSize());
        List<TaskCommon> taskCommonList = appTaskCommonMapper.queryTaskByUuidFromView(param.getUserId(), null);

        Integer dayReportItem = 0;
        for (TaskCommon taskCommon : taskCommonList) {
            dayReportItem += taskCommon.getTodayCommitNum()==0 ? 0 : 1;
        }
        WechatUserAccount userInfo = userAccountMapper.queryByUuid(param.getUserId());
        PageInfo pageInfo = new PageInfo(taskCommonList);
        map.put("lessonList", taskCommonList);
        map.put("itemNum", taskCommonList.size());
        map.put("dayDoneNum", dayReportItem);
        map.put("userInfo", userInfo);
        map.put("hasNextPage",pageInfo.isHasNextPage());
        map.put("total",pageInfo.getTotal());
        map.put("totalPage",pageInfo.getPages());
        return map;
    }
    /**
     * 我的功课排序
     * @param param
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateitemListOrder(WechatFlockParam param) {
        if (param.getFlockList().size()>0){
            for (FlockRecordVo item : param.getFlockList()) {
                String tableName = null;
                if (item.getType() ==0){
                    tableName = "t_task_plan_lesson";
                }else if (item.getType()==2){
                    tableName = "t_bookcase";
                }else {
                    tableName = "t_task_plan_custom";
                }
        
                itemLessonMapper.updateitemListOrder(item.getItemId(),item.getItemContentId(),item.getType(), item.getOrder(), param.getUUid(),tableName);
        
            }
        }
    }

}
