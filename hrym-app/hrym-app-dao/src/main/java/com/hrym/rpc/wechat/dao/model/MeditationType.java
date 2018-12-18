package com.hrym.rpc.wechat.dao.model;

import java.io.Serializable;

/**
 * Created by hrym13 on 2018/4/16.
 */
public class MeditationType implements Serializable {

    private Integer meditationTypeId;       // 主键ID
    private String meditationTypeName;      // 共修类型名称
    private String meditationTypeIntro;     // 共修简介
    private String author;                  // 组织者
    private Integer createdTime;            // 恭喜类型创建时间
    private Integer updateTime;             // 共修类型更新时间
    private String activeHeadUrl;           // 共修类型头像
    private Integer startTime;              // 共修类型开始时间
    private Integer expectTime;             // 共修类型结束时间
    private Integer meditationTypeVersion;  // 共修类型版本选择标志：0：表示单版本；1:表示多版本
    private Integer count;                  // 参加人数


    private String createdTimes;
    private String updateTimes;
    private String startTimes;
    private String expectTimes;
    private Integer medCount;

    public Integer getMedCount() {
        return medCount;
    }

    public void setMedCount(Integer medCount) {
        this.medCount = medCount;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public Integer getExpectTime() {
        return expectTime;
    }

    public void setExpectTime(Integer expectTime) {
        this.expectTime = expectTime;
    }

    public Integer getMeditationTypeVersion() {
        return meditationTypeVersion;
    }

    public void setMeditationTypeVersion(Integer meditationTypeVersion) {
        this.meditationTypeVersion = meditationTypeVersion;
    }

    public String getActiveHeadUrl() {
        return activeHeadUrl;
    }

    public void setActiveHeadUrl(String activeHeadUrl) {
        this.activeHeadUrl = activeHeadUrl;
    }

    public String getCreatedTimes() {
        return createdTimes;
    }

    public void setCreatedTimes(String createdTimes) {
        this.createdTimes = createdTimes;
    }

    public String getUpdateTimes() {
        return updateTimes;
    }

    public void setUpdateTimes(String updateTimes) {
        this.updateTimes = updateTimes;
    }

    public Integer getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
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
                ", updateTime=" + updateTime +
                ", activeHeadUrl='" + activeHeadUrl + '\'' +
                ", startTime=" + startTime +
                ", expectTime=" + expectTime +
                ", meditationTypeVersion=" + meditationTypeVersion +
                ", count=" + count +
                ", createdTimes='" + createdTimes + '\'' +
                ", updateTimes='" + updateTimes + '\'' +
                ", startTimes='" + startTimes + '\'' +
                ", expectTimes='" + expectTimes + '\'' +
                ", medCount=" + medCount +
                '}';
    }
}
