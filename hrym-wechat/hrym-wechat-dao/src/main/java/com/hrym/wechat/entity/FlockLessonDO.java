package com.hrym.wechat.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class FlockLessonDO implements Serializable {

    private Integer itemId;             // 功课ID
    private Integer itemContentId;      // 功课内容ID
    private String lessonName;         // 版本名称
    private Integer uuid;
    private Integer type;
    private String lessonAvatar; //功课头像
    private String nickName; //功课创建人

    private Integer isAdd;

    private Float dayDoneNum; //今日总数

    private Float totalDoneNum; //累计总数


    private String dayNumStr;
    private String totalNumStr; //每日

    private Integer src = 0;

    private Integer flockPeopleNum;
    private String info;

}
