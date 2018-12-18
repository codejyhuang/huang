package com.hrym.wechat.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hrym13 on 2018/11/11.
 */
@Setter
@Getter
public class FlockRecordVo implements Serializable {
    private Integer flockId;
    private Integer itemContentId;
    private Integer itemId;
    private Integer type;
    private Integer uuid;
    private String startTimes;
    private String endTimes;
    private Integer startTime;
    private Integer endTime;
    private String nickname;
    private String avatar;
    private Double num;
    private Integer createTime;
    private String createTimes;
    private List<FlockRecordVo> flockRecordVo;
    private long totalPages;
    private String numStr;      //多少遍
    private long total;         //多少条
    private long peopleNum;  //多少人
    private String itemName;      //功课名称
    private String versionName;   // 版本内容名称
    private boolean isHasNextPage;
    private Integer currentPage;
    private String flockName;
    private Integer order;
}
