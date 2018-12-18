package com.hrym.wechat.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by hrym13 on 2018/11/9.
 */
@Setter
@Getter
public class FlockRecordLesson implements Serializable {
    
    private Integer recordId;       //功课记录ID
    private Integer taskId;         //功课任务ID
    private Integer userId;         //用户ID
    private long reportNum;      //上报数量
    private Integer reportTime;     //上报时间
    private Integer itemId;         //功课ID
    private Integer recordMethod;   //上报方式
    private Integer itemContentId;  //功课内容id
    
    private Integer isExist;
    private Integer recordStatus;     //  功课计划状态；0：没有功课计划；1：有没有完成；2：有完成了
    private Long planTarget;
    
}
