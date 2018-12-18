package com.hrym.wechat.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigInteger;

@Setter
@Getter
public class ItemCustom implements Serializable {

    private Integer taskId;
    private Integer itemId;
    private Integer userId;
    private String customName;
    private String unit;
    private String intro;
    private Integer planTarget;                 //功课计划目标
    private Integer startTime;
    private Integer planPeriod;
    private String remainCron;
    private Integer doneNum;                 // 已经完成数量
    private Integer todayCommitNum;         //今日提交总数
    private Integer countingMethod;            //'0:手动 ；1:音频计数； 2:语音计数 ；3：语速计数',
    private Integer autoDonenum;           //'自动报数',
    private Integer customDoneNum;        //'手动报数',
    private Integer isExit;                   //'是否删除0：已删除；1：未删除',
    private Integer createTime;               //创建时间',
    private Integer updateTime;                //'更新时间',
    private Integer recentAdd;                //'1表示最近添加  0表示已点击过',
    private Integer dayTarget;                //'每日目标',
    private Integer planTargetValue;      // '功课计划完成值',
    private Integer targetTime;               //'功课计划设定时间',
    private Integer competeTime;              //'功课计划完成时间',
    private String info;

    private Integer isAdd;
}
