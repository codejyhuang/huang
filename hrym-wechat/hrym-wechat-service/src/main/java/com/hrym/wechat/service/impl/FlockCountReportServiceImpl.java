package com.hrym.wechat.service.impl;

import com.google.common.collect.Lists;
import com.hrym.common.util.DateUtil;
import com.hrym.common.util.TableUtil;
import com.hrym.wechat.entity.*;
import com.hrym.wechat.mapper.*;
import com.hrym.wechat.service.IFlockCountReportService;
import com.hrym.wechat.smallProgram.FlockCountReportParam;
import com.hrym.wechat.smallProgram.ItemParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hrym13 on 2018/11/9.
 */
@Service
public class FlockCountReportServiceImpl implements IFlockCountReportService {

    @Autowired
    private ItemCustomMapper itemCustomMapper;
    @Autowired
    private ItemLessonMapper itemLessonMapper;
    @Autowired
    private FlockItemBookMapper itemBookMapper;
    @Autowired
    private FlockItemMapper flockItemMapper;
    @Autowired
    private FlockMapper flockMapper;
    @Autowired
    private FlockItemUserCountMapper flockItemUserCountMapper;
    @Autowired
    private FlockRecordMapper flockRecordMapper;
    @Autowired
    private TaskCommonMapper taskCommonMapper;
    @Autowired
    private FlockUserMapper flockUserMapper;

    /**
     * 共修报数
     *
     * @param param
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Map<String, Object> flockCountReport(FlockCountReportParam param) {

        Map<String, Object> map = new HashMap<>();
//        List<Integer> flockIds = new ArrayList<>();
        List<FlockRecordDO> list = new ArrayList<>();
        Integer recordId = null;
        String tableName = "t_flock_record_" + DateUtil.getYear();
//        FlockItemUserCount itemUserCount = null;
        int isDelete = 0;
        if (param.getFlockId() != null) {
            // 判断是否在群
            FlockItem flockUser = flockUserMapper.queryByFlockIdAndUuid(param.getFlockId(), param.getUuid());
            if (flockUser == null) {
                isDelete = 1;
                map.put("isDelete", isDelete);
                return map;
            }
        }

        //判断app是否有任务
        appIsTaskId(param);


        //1：自建'0：系统功课；2：经书
        if (param.getType() == 2) {
            recordId = bookReport(param);
        } else {
            recordId = lessonReport(param);
        }

        //查询这个功课对应的所有群
        List<Integer> flock2Report = Lists.newArrayList();
        if (param.getFlockId() == null) {

            //群列表或者功课列表中上报
            //根据功课ID，内容版本ID，功课类型查找共修群ID
            flock2Report = flockItemMapper.findFlockLessonItemById(param.getItemId(), param.getItemContentId(), param.getType(), param.getUuid());
        } else {

            //在群里上报
            flock2Report.add(param.getFlockId());
        }

        //查询这个人的所有统计表信息
        List<Integer> flock2Count = flockItemMapper.getFlockIdByItemAndUuid(param.getItemId()
                , param.getType()
                , param.getItemContentId()
                , param.getUuid());

        for (Integer flockId : flock2Report) {

            //整合需要添加的record
            FlockRecordDO record = new FlockRecordDO();
            record.setCreateTime(DateUtil.currentSecond());
            record.setItemContentId(param.getItemContentId() == null ? 0 : param.getItemContentId());
            record.setItemId(param.getItemId() == null ? 0 : param.getItemId());
            record.setLat(param.getLat());
            record.setLon(param.getLon());
            record.setNum(param.getNum());
            record.setUuid(param.getUuid());
            record.setParentId(recordId);
            record.setType(param.getType());
            record.setFlockId(flockId);

            //遍历flock2Report,找出需要插入count的flock_id
            if (!flock2Count.contains(flockId)) {

                flockItemUserCountMapper.insert(record);
            }

            list.add(record);
        }

        if (list.size()!=0){
            //插入上报明细表
            flockRecordMapper.insertFlockRecord(list, tableName);
        }


        map.put("isDelete", isDelete);
        map.put("recordId", recordId);

        return map;
    }


    /**
     * 导入历史数据
     *
     * @param param
     */
    @Override
    public void importHistory(FlockCountReportParam param) {
        // 判断前端报数是否大于零
        if (param.getNum() != null && param.getNum() >= 0) {
            if (param.getType() == 2) {
                Integer reportNum = 0;
                String percent = "0%";
                reportNum = Double.valueOf(param.getNum()).intValue();
                FlockRecordBook book = itemBookMapper.findFlockRecordBookByUuid(param.getItemId(), param.getUuid());
                Integer num = itemBookMapper.queryHistoryRecord(param.getTaskId());
                if (num == null) {
                    FlockRecordBook record = new FlockRecordBook();
                    record.setTaskId(book.getIndexId());
                    record.setUserId(param.getUuid());
                    record.setReportNum(Long.valueOf(reportNum));
                    record.setPercent(percent);
                    record.setReportTime(DateUtil.currentSecond());
                    record.setItemId(param.getItemId());
                    record.setRecordMethod(param.getRecordMethod());
                    //插入上报数据
                    itemBookMapper.saveRecord(record);
                    //第一次导入  直接更新 report_num ++
                    itemBookMapper.updateReport(reportNum, percent, book.getIndexId(), DateUtil.currentSecond());
                } else {
                    itemBookMapper.updateHistoryRecordNum(reportNum, param.getTaskId());
                    //修改导入数据
                    itemBookMapper.updateReport(reportNum - num, percent, book.getIndexId(), DateUtil.currentSecond());
                }

            } else {
                long reportNum = Double.valueOf(param.getNum()).longValue();
                if (param.getType() == 1) {
                    Long num = itemCustomMapper.queryHistoryRecordNum(param.getTaskId());
                    if (num==null){
                        FlockRecordCustom custom = new FlockRecordCustom();
                        custom.setTaskId(param.getTaskId());
                        custom.setItemId(param.getItemId());
                        custom.setUserId(param.getUuid());
                        custom.setReportNum(reportNum);
                        custom.setRecordMethod(param.getRecordMethod());
                        custom.setReportTime(DateUtil.currentSecond());
                        custom.setRecordStatus(0);
                        itemCustomMapper.saveTaskRecord(custom);  //首次保存  导入record
                        itemCustomMapper.updateImportDoneNumByTaskId(reportNum,DateUtil.currentSecond(),param.getTaskId());  //更新  task
                    }else {
                        itemCustomMapper.updateImportRecordNum(reportNum,param.getTaskId()); //覆盖 导入 record
                        itemCustomMapper.updateImportDoneNumByTaskId(reportNum-num,DateUtil.currentSecond(),param.getTaskId()); //更新 task
                    }
                } else {
                    Long num = itemLessonMapper.queryHistoryRecordNum(param.getTaskId());
                    if (num==null){
                        FlockRecordLesson lesson = new FlockRecordLesson();
                        lesson.setTaskId(param.getTaskId());
                        lesson.setItemId(param.getItemId());
                        lesson.setItemContentId(param.getItemContentId());
                        lesson.setUserId(param.getUuid());
                        lesson.setReportNum(reportNum);
                        lesson.setRecordMethod(param.getRecordMethod());
                        lesson.setReportTime(DateUtil.currentSecond());
                        lesson.setRecordStatus(0);
                        itemLessonMapper.saveTaskRecord(lesson); //首次保存  导入record
                        itemLessonMapper.updateImportDoneNumByTaskId(reportNum,DateUtil.currentSecond(),param.getTaskId());  //更新  task
                    }else {
                        itemLessonMapper.updateImportRecordNum(reportNum,param.getTaskId()); //覆盖 导入 record
                        itemLessonMapper.updateImportDoneNumByTaskId(reportNum-num,DateUtil.currentSecond(),param.getTaskId());  //更新 task
                    }
                }
            }
        }
    }


    /**
     * 功课上报
     *
     * @param
     */
    public Integer lessonReport(FlockCountReportParam param) {
        int statu = 0;
        long reportNum = Double.valueOf(param.getNum()).longValue();
        Integer taskId = null;
        if (param.getNum() > 0) {
            if (param.getType() == 1) {
                ItemCustom vo = itemCustomMapper.findTaskPlanCustomById(param.getUuid(), param.getItemId());
                System.out.println(vo);
                taskId = vo.getTaskId();
                if (vo.getPlanTarget() != null && vo.getPlanTarget() > 0) {
                    if (vo.getPlanTarget() <= vo.getPlanTargetValue()) {
                        //已完成
                        itemCustomMapper.updateDoneNumByTaskId(reportNum, 0, DateUtil.currentSecond(), vo.getTaskId());

                    } else {
                        // 未完成
                        statu = 1;
                        itemCustomMapper.updateDoneNumByTaskId(reportNum, reportNum, DateUtil.currentSecond(), vo.getTaskId());
                        //功课计划完成清零
                        if ((vo.getPlanTargetValue() + reportNum) >= vo.getPlanTarget()) {
                            statu = 2;
                            ItemCustom custom = new ItemCustom();
                            //功课计划完成时间
                            custom.setCompeteTime(DateUtil.currentSecond());
                            custom.setTaskId(vo.getTaskId());
                            custom.setUpdateTime(DateUtil.currentSecond());
                            itemCustomMapper.updateTaskPlanCustom(custom);

                        }
                    }
                } else {
                    itemCustomMapper.updateDoneNumByTaskId(reportNum, 0, DateUtil.currentSecond(), vo.getTaskId());
                }
            } else {
                ItemLessonTaskPlan vo = itemLessonMapper.findTaskPlanLessonByTaskId(param.getUuid(), param.getItemContentId(), param.getItemId());
                System.out.println(vo);
                if (vo != null) {
                    taskId = vo.getTaskId();
                }
                if (vo != null && vo.getPlanTarget() != null && vo.getPlanTarget() > 0) {
                    if (vo.getPlanTargetValue() != null && vo.getPlanTarget() <= vo.getPlanTargetValue()) {
                        // 已完成
                        itemLessonMapper.updateDoneNumByTaskId(reportNum, 0, DateUtil.currentSecond(), 1, vo.getTaskId());
                    } else {
                        // 未完成
                        itemLessonMapper.updateDoneNumByTaskId(reportNum, reportNum, DateUtil.currentSecond(), 1, vo.getTaskId());
                        statu = 1;
                        //功课计划完成清零
                        if ((vo.getPlanTargetValue() + reportNum) >= vo.getPlanTarget()) {
                            //修行记录状态为已完成且有计划
                            statu = 2;

                            ItemLessonTaskPlan lesson = new ItemLessonTaskPlan();

                            //功课计划完成时间
                            lesson.setCompeteTime(DateUtil.currentSecond());
                            lesson.setTaskId(vo.getTaskId());
                            lesson.setUpdateTime(DateUtil.currentSecond());
                            itemLessonMapper.updateTaskPlanLesson(lesson);

                        }
                    }
                } else {
                    itemLessonMapper.updateDoneNumByTaskId(reportNum, 0, DateUtil.currentSecond(), 1, vo.getTaskId());
                }
            }
        }
        Integer recordId = record(param, statu, taskId);

        return recordId;
    }

    //上报历史记录//record
    public Integer record(FlockCountReportParam param, Integer statu, Integer taskId) {

        long reportNum = Double.valueOf(param.getNum()).longValue();
        Integer recordId = null;
        // 判断前端报数是否大于零
        if (param.getNum() >= 0) {
            if (param.getType() == 1) {
                FlockRecordCustom custom = new FlockRecordCustom();
                custom.setTaskId(taskId);
                custom.setItemId(param.getItemId());
                custom.setUserId(param.getUuid());
                custom.setReportNum(reportNum);
                //1手动报数
                if (param.getRecordMethod() != null) {
                    custom.setRecordMethod(param.getRecordMethod());
                } else {
                    custom.setRecordMethod(0);
                }
                custom.setReportTime(DateUtil.currentSecond());
                custom.setRecordStatus(statu);

                itemCustomMapper.saveTaskRecord(custom);
            } else {
                FlockRecordLesson lesson = new FlockRecordLesson();
                lesson.setTaskId(taskId);
                lesson.setItemId(param.getItemId());
                lesson.setItemContentId(param.getItemContentId());
                lesson.setUserId(param.getUuid());
                lesson.setReportNum(reportNum);
                if (param.getRecordMethod() != null) {
                    lesson.setRecordMethod(param.getRecordMethod());
                } else {
                    lesson.setRecordMethod(0);
                }
                lesson.setReportTime(DateUtil.currentSecond());
                lesson.setRecordStatus(statu);
                itemLessonMapper.saveTaskRecord(lesson);

            }
            recordId = itemBookMapper.getLastId();
        }
        return recordId;
    }

    /**
     * 经书上报
     *
     * @param param
     */
    public Integer bookReport(FlockCountReportParam param) {

        Integer reportNum = 0;
        String percent = "0%";
        if (param.getNum() != null) {
            reportNum = Double.valueOf(param.getNum()).intValue();
        }
        FlockRecordBook book = itemBookMapper.findFlockRecordBookByUuid(param.getItemId(), param.getUuid());
        itemBookMapper.updateReport(reportNum, percent, book.getIndexId(), DateUtil.currentSecond());

        FlockRecordBook record = new FlockRecordBook();
        record.setTaskId(book.getIndexId());
        record.setUserId(param.getUuid());
        record.setReportNum(Long.valueOf(reportNum));
        record.setPercent(percent);
        record.setReportTime(DateUtil.currentSecond());
        record.setItemId(param.getItemId());
        record.setRecordMethod(0);
        //插入上报数据
        itemBookMapper.saveRecord(record);
        Integer recordId = itemBookMapper.getLastId();
        return recordId;
    }

    // 判断app任务表里是否有建功课任务
    public void appIsTaskId(FlockCountReportParam param) {

        String tableName = null;
        if (param.getType().equals(0)) {
            tableName = "t_task_plan_lesson";
        } else if (param.getType().equals(1)) {
            tableName = "t_task_plan_custom";
        } else {
            tableName = "t_bookcase";
        }
        int count = taskCommonMapper.queryCountByItemIdAndItemContentIdAndTableNameAndUuid(param.getType(), param.getItemId(), param.getItemContentId(), param.getUuid(), tableName, null);
        //未删除的为零
        if (count == 0) {
//            if (count == 0) {
            //全部状态为0  创建一条
            TaskCommon taskCommon = new TaskCommon();
            taskCommon.setItemId(param.getItemId());
            taskCommon.setItemContentId(param.getItemContentId());
            taskCommon.setCreateTime(DateUtil.currentSecond());
            taskCommon.setUuid(param.getUuid());
            taskCommonMapper.insert(taskCommon, tableName, param.getType());
//            }
        } else {
            int existCount = taskCommonMapper.queryCountByItemIdAndItemContentIdAndTableNameAndUuid(param.getType(), param.getItemId(), param.getItemContentId(), param.getUuid(), tableName, 1);
            if (existCount == 0) {
                if (param.getType().equals(2)) {
                    //有未删除   更新
                    taskCommonMapper.updateBookIsExist(param.getType(), param.getItemId(), param.getItemContentId(), param.getUuid(), tableName);
                } else {
                    //有未删除   更新
                    taskCommonMapper.updateIsExist(param.getType(), param.getItemId(), param.getItemContentId(), param.getUuid(), tableName);
                }
            }

        }
    }


}
