package com.hrym.wechat.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by hrym13 on 2018/11/9.
 */
@Setter
@Getter
public class ItemLessonTaskPlan implements Serializable {
    
    private Integer taskId;
    private Integer uuid;
    private Integer itemId;        // 功课id
    private Long planTarget;       // 功课目标
    private Integer startTime;       // 开始时间
    private int planPeriod;          // 功课周期，单位天
    private String remainCron;       // 提醒时间表达式
    private Long doneNum;          // 已经完成数量
    private Long todayCommitNum;   // 今日提交总数
    private Integer countingMethod;  // 计数方式（手动：0，蓝牙：1，音频：2）
    private Integer itemContentId;      // 功课内容ID
    private Long autoDoneNum;      // 自动报数
    private Long customDoneNum;    // 手动报数
    private Integer createTime;      // 创建时间
    private Integer isExit;          // 是否删除0：已删除；1：未删除
    private Integer updateTime;      // 更新时间
    private Integer recentAdd;       // 1表示最近添加  0表示已点击过
    private Long dayTarget;       // 今日目标
    private Integer musicId;
    private String backgroundPic;
    private Long planTargetValue;  //功课计划目标数量
    private Integer targetTime;
    private Integer competeTime;
    
}
