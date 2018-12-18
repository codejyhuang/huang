package com.hrym.wechat.entity;

import java.io.Serializable;

/**
 * Created by hrym13 on 2018/4/12.
 */
public class MeditationTask implements Serializable{

    private Integer Id;            //主键ID
    private Integer meditationTypeId;         //恭修类型ID
    private Integer scheduleId;        //活动ID
    private Integer createdTime;
    private Integer userId;
    private Integer count;
    private Integer todayNumber;        //今日报数
    private String createdTimes;
    private Integer updateTime;
//    接受
    private Integer SerialNumber;
    private String nickName;        //用户昵称
    private String avatarUrl;      //微信头像
    private String province;        //地区
    private String activeTitle;        //活动名称
    private String groupId;             //群ID
    private Integer rowNum;
//    private Integer sum;            //个人今日总数


    public Integer getMeditationTypeId() {
        return meditationTypeId;
    }

    public void setMeditationTypeId(Integer meditationTypeId) {
        this.meditationTypeId = meditationTypeId;
    }

    public Integer getRowNum() {
        return rowNum;
    }

    public void setRowNum(Integer rowNum) {
        this.rowNum = rowNum;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getActiveTitle() {
        return activeTitle;
    }

    public void setActiveTitle(String activeTitle) {
        this.activeTitle = activeTitle;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Integer getSerialNumber() {
        return SerialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        SerialNumber = serialNumber;
    }

    public Integer getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreatedTimes() {
        return createdTimes;
    }

    public void setCreatedTimes(String createdTimes) {
        this.createdTimes = createdTimes;
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
        return "MeditationTask{" +
                "Id=" + Id +
                ", scheduleId=" + scheduleId +
                ", createdTime=" + createdTime +
                ", userId=" + userId +
                ", count=" + count +
                ", todayNumber=" + todayNumber +
                ", createdTimes='" + createdTimes + '\'' +
                ", updateTime=" + updateTime +
                ", SerialNumber=" + SerialNumber +
                ", nickName='" + nickName + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", province='" + province + '\'' +
                '}';
    }
}
