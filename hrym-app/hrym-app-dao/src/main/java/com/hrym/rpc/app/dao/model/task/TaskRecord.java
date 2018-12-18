package com.hrym.rpc.app.dao.model.task;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 功课上报记录实体类
 * Created by mj on 2017/10/30.
 */
@Table(name = "t_task_record")
public class TaskRecord implements Serializable {

    @Id
    private Integer recordId;       //功课记录ID
    private Integer taskId;         //功课任务ID
    private Integer userId;         //用户ID
    private double reportNum;      //上报数量
    private Integer reportTime;     //上报时间
    private Integer itemId;         //功课ID
    private Integer recordMethod;   //上报方式
    private Integer typeId;         //功课类型id
    private String percent;         //经数上报百分比


    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public double getReportNum() {
        return reportNum;
    }

    public void setReportNum(double reportNum) {
        this.reportNum = reportNum;
    }

    public Integer getReportTime() {
        return reportTime;
    }

    public void setReportTime(Integer reportTime) {
        this.reportTime = reportTime;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getRecordMethod() {
        return recordMethod;
    }

    public void setRecordMethod(Integer recordMethod) {
        this.recordMethod = recordMethod;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    @Override
    public String toString() {
        return "TaskRecord{" +
                "recordId=" + recordId +
                ", taskId=" + taskId +
                ", userId=" + userId +
                ", reportNum=" + reportNum +
                ", reportTime=" + reportTime +
                ", itemId=" + itemId +
                ", recordMethod=" + recordMethod +
                ", typeId=" + typeId +
                ", percent='" + percent + '\'' +
                '}';
    }
}
