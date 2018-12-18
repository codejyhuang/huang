package com.hrym.wechat.entity;

import com.hrym.common.util.NumUtil;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class TaskCommon implements Serializable {

    private Integer taskId;

    private Integer uuid;

    private Integer itemId; //

    private Integer itemContentId; //内容id

    private Integer type; //功课类型

    private Integer createTime;

    private Integer doneNum;

    private Integer todayCommitNum; //今日完成数量

    private String lessonName; //功课名称


    private String doneNumStr; //前台显示

    private String todayCommitNumStr; //前台显示
    private String nickName;
    private Integer count;      //功课数量
    private Integer isExit;     //1:未删


    public String getDoneNumStr() {
        return NumUtil.getTotalNumStr(new Double(doneNum==null?0:doneNum),"0");
    }

    public String getTodayCommitNumStr() {
        return NumUtil.getTotalNumStr(new Double(todayCommitNum==null?0:todayCommitNum),"0");
    }
}
