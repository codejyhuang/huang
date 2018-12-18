package com.hrym.wechat.entity;

import java.io.Serializable;

/**
 * Created by hrym13 on 2018/4/12.
 */
public class Meditation implements Serializable {

    private Integer meditationId;
    private  Integer userId;
    private String groupId;                    //群ID
    private Integer createdTime;
    private String meditationName;      //群名称
    private  String meditationIntro;    //群简介
    private String codeUrl;             //群二维码
    private String meditationUrl;       //群头像
    private Integer meditationType;     //群类型
    private  Integer updateTime;
    private  String openGId; //群ID
    private Integer status;
    private String openId;

    public String getOpenGId() {
        return openGId;
    }

    public void setOpenGId(String openGId) {
        this.openGId = openGId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Integer getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getMeditationId() {
        return meditationId;
    }

    public void setMeditationId(Integer meditationId) {
        this.meditationId = meditationId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Integer getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Integer createdTime) {
        this.createdTime = createdTime;
    }

    public String getMeditationName() {
        return meditationName;
    }

    public void setMeditationName(String meditationName) {
        this.meditationName = meditationName;
    }

    public String getMeditationIntro() {
        return meditationIntro;
    }

    public void setMeditationIntro(String meditationIntro) {
        this.meditationIntro = meditationIntro;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }

    public String getMeditationUrl() {
        return meditationUrl;
    }

    public void setMeditationUrl(String meditationUrl) {
        this.meditationUrl = meditationUrl;
    }

    public Integer getMeditationType() {
        return meditationType;
    }

    public void setMeditationType(Integer meditationType) {
        this.meditationType = meditationType;
    }

    @Override
    public String toString() {
        return "Meditation{" +
                "meditationId=" + meditationId +
                ", userId=" + userId +
                ", groupId=" + groupId +
                ", createdTime=" + createdTime +
                ", meditationName='" + meditationName + '\'' +
                ", meditationIntro='" + meditationIntro + '\'' +
                ", codeUrl='" + codeUrl + '\'' +
                ", meditationUrl='" + meditationUrl + '\'' +
                ", meditationType=" + meditationType +
                '}';
    }
}
