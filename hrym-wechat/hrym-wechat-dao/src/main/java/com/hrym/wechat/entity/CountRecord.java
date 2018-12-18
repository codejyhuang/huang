package com.hrym.wechat.entity;

import java.io.Serializable;

/**
 * Created by hrym13 on 2018/4/12.
 */
public class CountRecord implements Serializable{

    private Integer countId;            //主键ID
    private Integer scheduleId;        //活动ID
    private Integer createdTime;
    private Integer userId;
    private Integer count;
    private Integer todayNumber;        //今日报数
    private String createdTimes;
    private String law;                 //共修法本预习；
    private Integer meditationTypeId;   //共修类型ID
    private String activeTitle;        //共修内容名称
    private Integer lawTime;           //法本预习时间

    public Integer getLawTime() {
        return lawTime;
    }

    public void setLawTime(Integer lawTime) {
        this.lawTime = lawTime;
    }

    public String getActiveTitle() {
        return activeTitle;
    }

    public String setActiveTitle(String activeTitle) {
        this.activeTitle = activeTitle;
        return activeTitle;
    }

    public String getLaw() {
        return law;
    }

    public String setLaw(String law) {
        this.law = law;
        return law;
    }

    public Integer getMeditationTypeId() {
        return meditationTypeId;
    }

    public Integer setMeditationTypeId(Integer meditationTypeId) {
        this.meditationTypeId = meditationTypeId;
        return meditationTypeId;
    }

    public String getCreatedTimes() {
        return createdTimes;
    }

    public void setCreatedTimes(String createdTimes) {
        this.createdTimes = createdTimes;
    }

    public Integer getCountId() {
        return countId;
    }

    public void setCountId(Integer countId) {
        this.countId = countId;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getTodayNumber() {
        return todayNumber;
    }

    public void setTodayNumber(Integer todayNumber) {
        this.todayNumber = todayNumber;
    }

    @Override
    public String toString() {
        return "CountRecord{" +
                "countId=" + countId +
                ", scheduleId=" + scheduleId +
                ", createdTime=" + createdTime +
                ", userId=" + userId +
                ", count=" + count +
                ", todayNumber=" + todayNumber +
                ", createdTimes='" + createdTimes + '\'' +
                '}';
    }
}
