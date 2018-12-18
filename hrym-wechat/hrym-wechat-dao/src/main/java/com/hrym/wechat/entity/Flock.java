package com.hrym.wechat.entity;

import com.hrym.wechat.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Flock extends BaseEntity {

    public static final Integer DEFAULT_PRIVACY_STATE = 2;  //默认私密状态
    public static final Integer PUBLIC_PRIVACY_STATE = 1;  //公开状态

    public static final Integer NORMAL_STATE = 1;
    public static final Integer DISSOLVE_STATE = 0;



    private String name; //群名称

    private String intro; //群简介

    private Integer privacy = DEFAULT_PRIVACY_STATE; //是否公开  默认私密

    private String dedicationVerses; //群回响文

    private Integer createTime; //创建时间

    private Double dayDoneNum;  //今日统计报数

    private Double totalDoneNum; //群总报数

    private Integer itemJoinNum; //共修人数

    private Integer itemNum; //功课数

    private String avatarUrl; //群头像

    private WechatUserAccount createUser; //群主

    private List<FlockItem> flockItems;


    private String viewStr;  //创建于

    private String lessonStr; //

    private String dayNumStr; //1023万2934遍

    private String totalNumStr; //12亿2882万2832遍
    private Integer state;      //0:解散群
    private String avatar;
    private String nickName;

}
