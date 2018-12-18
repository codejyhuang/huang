package com.hrym.wechat.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hrym13 on 2018/12/3.
 */
@Setter
@Getter
public class FlockUserBackJson implements Serializable {
    
    private List<FlockItem> list;
    private Integer uuid;
    
    private String avatarUrl;
    private String name;
    private String nickname;
    private String ymd;
    private String time;
    private Integer flockId;
    private Integer itemJoinNum;
    private String weeks;
    private String timeStr;
    private String info;
    
}
