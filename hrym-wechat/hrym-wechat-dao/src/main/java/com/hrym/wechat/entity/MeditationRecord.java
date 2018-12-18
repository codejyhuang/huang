package com.hrym.wechat.entity;

import java.io.Serializable;

/**
 * Created by hrym13 on 2018/4/12.
 */
public class MeditationRecord implements Serializable {

    private Integer Id;
    private Integer meditationTypeId;             //共修类型ID
    private Integer scheduleId;         //共修活动ID
    private Integer status;             //共修群身份0:管理员；1:成员
    private Integer createdTime;
    private Integer userId;
    private Integer todayNumber;
    private Integer isTop;              // 置顶；0：表示没有；

    public Integer getIsTop() {
        return isTop;
    }

    public void setIsTop(Integer isTop) {
        this.isTop = isTop;
    }

    public Integer getTodayNumber() {
        return todayNumber;
    }

    public void setTodayNumber(Integer todayNumber) {
        this.todayNumber = todayNumber;
    }

    public Integer getMeditationTypeId() {
        return meditationTypeId;
    }

    public void setMeditationTypeId(Integer meditationTypeId) {
        this.meditationTypeId = meditationTypeId;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Integer createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "MeditationRecord{" +
                "Id=" + Id +
                ", meditationTypeId=" + meditationTypeId +
                ", scheduleId=" + scheduleId +
                ", status=" + status +
                ", createdTime=" + createdTime +
                ", userId=" + userId +
                ", todayNumber=" + todayNumber +
                ", isTop=" + isTop +
                '}';
    }
}
