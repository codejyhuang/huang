package com.hrym.wechat.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hrym13 on 2018/4/16.
 */
public class MeditationType implements Serializable {

    private Integer meditationTypeId;
    private String meditationTypeName;
    private String meditationTypeIntro;
    private String author;
    private Integer createdTime;
    private String activeHeadUrl;     //活动头像url
    private Integer meditationTypeVersion;      //  共修类型版本：0单版本，1多版本；
    private Integer todayNumber;
    private long dayDown;                //倒计时
    private Integer expectTime;       //预期完成目标结束时间
    private Integer startTime;         //共修开始时间
    private String startTimes;
    private String expectTimes;
    private Integer userNumber;         //参加活动人数
    private Integer isTop;
    private Integer isExit;             // 功课是否可用，1：可用；0：不可用
    private List<MeditationSchedule> medList;

    public Integer getIsExit() {
        return isExit;
    }

    public void setIsExit(Integer isExit) {
        this.isExit = isExit;
    }

    public Integer getIsTop() {
        return isTop;
    }

    public void setIsTop(Integer isTop) {
        this.isTop = isTop;
    }

    public List<MeditationSchedule> getMedList() {
        return medList;
    }

    public void setMedList(List<MeditationSchedule> medList) {
        this.medList = medList;
    }

    public String getActiveHeadUrl() {
        return activeHeadUrl;
    }

    public void setActiveHeadUrl(String activeHeadUrl) {
        this.activeHeadUrl = activeHeadUrl;
    }

    public Integer getMeditationTypeVersion() {
        return meditationTypeVersion;
    }

    public void setMeditationTypeVersion(Integer meditationTypeVersion) {
        this.meditationTypeVersion = meditationTypeVersion;
    }

    public Integer getTodayNumber() {
        return todayNumber;
    }

    public void setTodayNumber(Integer todayNumber) {
        this.todayNumber = todayNumber;
    }

    public long getDayDown() {
        return dayDown;
    }

    public void setDayDown(long dayDown) {
        this.dayDown = dayDown;
    }

    public Integer getExpectTime() {
        return expectTime;
    }

    public void setExpectTime(Integer expectTime) {
        this.expectTime = expectTime;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public String getStartTimes() {
        return startTimes;
    }

    public void setStartTimes(String startTimes) {
        this.startTimes = startTimes;
    }

    public String getExpectTimes() {
        return expectTimes;
    }

    public void setExpectTimes(String expectTimes) {
        this.expectTimes = expectTimes;
    }

    public Integer getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(Integer userNumber) {
        this.userNumber = userNumber;
    }

    public Integer getMeditationTypeId() {
        return meditationTypeId;
    }

    public void setMeditationTypeId(Integer meditationTypeId) {
        this.meditationTypeId = meditationTypeId;
    }

    public String getMeditationTypeName() {
        return meditationTypeName;
    }

    public void setMeditationTypeName(String meditationTypeName) {
        this.meditationTypeName = meditationTypeName;
    }

    public String getMeditationTypeIntro() {
        return meditationTypeIntro;
    }

    public void setMeditationTypeIntro(String meditationTypeIntro) {
        this.meditationTypeIntro = meditationTypeIntro;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Integer createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public String toString() {
        return "MeditationType{" +
                "meditationTypeId=" + meditationTypeId +
                ", meditationTypeName='" + meditationTypeName + '\'' +
                ", meditationTypeIntro='" + meditationTypeIntro + '\'' +
                ", author='" + author + '\'' +
                ", createdTime=" + createdTime +
                ", activeHeadUrl='" + activeHeadUrl + '\'' +
                ", meditationTypeVersion=" + meditationTypeVersion +
                ", todayNumber=" + todayNumber +
                ", dayDown=" + dayDown +
                ", expectTime=" + expectTime +
                ", startTime=" + startTime +
                ", startTimes='" + startTimes + '\'' +
                ", expectTimes='" + expectTimes + '\'' +
                ", userNumber=" + userNumber +
                ", isTop=" + isTop +
                ", isExit=" + isExit +
                ", medList=" + medList +
                '}';
    }
}
