package com.hrym.wechat.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by hrym13 on 2018/11/9.
 */
@Setter
@Getter
public class FlockRecordBook implements Serializable {
    private Integer recordId;       //功课记录ID
    private Integer taskId;         //功课任务ID
    private Integer userId;         //用户ID
    private Long reportNum;      //上报数量
    private Integer reportTime;     //上报时间
    private Integer itemId;         //功课ID
    private String percent;         //经书上报百分比
    private Integer indexId;        //
    private Integer recordMethod;   // 5:导入记录
}
