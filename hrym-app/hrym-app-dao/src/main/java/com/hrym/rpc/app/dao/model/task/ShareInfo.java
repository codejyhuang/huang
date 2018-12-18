package com.hrym.rpc.app.dao.model.task;


import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 分享实体类
 * Created by mj on 2017/10/30.
 */
@Table(name = "t_share")
public class ShareInfo implements Serializable {

    @Id
    private Integer shareId;        //分享ID
    private Integer shareType;      //分享类型（0：今日功课；1：全部功课；2：社群；）
    private Integer shareTypeValue; //类型对应的ID值
    private Integer taskId;         //任务ID


    public Integer getShareId() {
        return shareId;
    }

    public void setShareId(Integer shareId) {
        this.shareId = shareId;
    }

    public Integer getShareType() {
        return shareType;
    }

    public void setShareType(Integer shareType) {
        this.shareType = shareType;
    }

    public Integer getShareTypeValue() {
        return shareTypeValue;
    }

    public void setShareTypeValue(Integer shareTypeValue) {
        this.shareTypeValue = shareTypeValue;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    @Override
    public String toString() {
        return "ShareInfo{" +
                "shareId=" + shareId +
                ", shareType=" + shareType +
                ", shareTypeValue=" + shareTypeValue +
                ", taskId=" + taskId +
                '}';
    }
}
