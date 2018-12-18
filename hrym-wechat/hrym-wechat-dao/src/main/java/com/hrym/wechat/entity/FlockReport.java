package com.hrym.wechat.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 上报
 * Created by hrym13 on 2018/11/9.
 */
@Setter
@Getter
public class FlockReport implements Serializable {
    
    private Integer id;
    private Integer parentId;               // 用户在功课上报中的recordid
    private Integer createTime;            // '创建时间',
    private Integer uuid;                   //'用户id',
    private  Double num;                     // '上报数',
    private String ymd;                     //'yyyymmdd\n',
    private Integer year;                   // '年',
    private Integer month;                  //'月',
    private  Float lon;                     //'经度',
    private  Float lat;                     // '纬度',
    private Integer state;                  //'0：已删除\n1：正常',
    private Integer itemId;
    private Integer itemContentId;
    private Integer flockId;               //'群id',
    private Double dayDoneNum; //今日总数
    private Double totalDoneNum; //累计总数
    
}
