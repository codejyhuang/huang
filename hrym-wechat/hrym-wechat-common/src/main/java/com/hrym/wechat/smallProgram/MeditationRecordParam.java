package com.hrym.wechat.smallProgram;

import com.hrym.common.base.BaseRequestParam;

import java.io.Serializable;

/**
 * Created by hrym13 on 2018/4/16.
 */
public class MeditationRecordParam extends BaseRequestParam implements Serializable {

    private MeditationRecordData data;

    public MeditationRecordData getData() {
        return data;
    }

    public void setData(MeditationRecordData data) {
        this.data = data;
    }

    public Integer getId() {
        return data.getId();
    }

    public Integer getScheduleId() {
        return data.getScheduleId();
    }

    public Integer getStatus() {
        return data.getStatus();
    }

    public Integer getCreatedTime() {
        return data.getCreatedTime();
    }

    public Integer getUserId() {
        return data.getUserId();
    }

    public String getOpenId() {
        return data.getOpenId();
    }

}
class MeditationRecordData implements Serializable{

    private Integer Id;
    private Integer scheduleId;         //共修活动ID
    private Integer status;             //共修群身份0:管理员；1:成员
    private Integer createdTime;
    private Integer userId;
    private String openId;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Integer getId() {
        return Id;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public Integer getStatus() {
        return status;
    }

    public Integer getCreatedTime() {
        return createdTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setCreatedTime(Integer createdTime) {
        this.createdTime = createdTime;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
