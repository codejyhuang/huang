package com.hrym.rpc.app.dao.model.task;

import java.io.Serializable;

/**
 * Created by hrym13 on 2017/9/12.
 */
public class FeedBack implements Serializable {

    private Integer idtFeedBack;
    private String phone;
    private Integer state;      //0：未处理；1：已阅读；2；已处理
    private String content;
    private String resolution;
    private String feedbackType;        //反馈类型
    private Integer createTime;
    private Integer updateTime;
    private Integer userId;


    //接受参数
    private String createTimeis;
    private String updateTimeis;

    public String getCreateTimeis() {
        return createTimeis;
    }

    public void setCreateTimeis(String createTimeis) {
        this.createTimeis = createTimeis;
    }

    public String getUpdateTimeis() {
        return updateTimeis;
    }

    public void setUpdateTimeis(String updateTimeis) {
        this.updateTimeis = updateTimeis;
    }

    public String getFeedbackType() {
        return feedbackType;
    }

    public void setFeedbackType(String feedbackType) {
        this.feedbackType = feedbackType;
    }

    public Integer getIdtFeedBack() {
        return idtFeedBack;
    }

    public void setIdtFeedBack(Integer idtFeedBack) {
        this.idtFeedBack = idtFeedBack;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "FeedBack{" +
                "idtFeedBack=" + idtFeedBack +
                ", phone='" + phone + '\'' +
                ", state=" + state +
                ", content='" + content + '\'' +
                ", resolution='" + resolution + '\'' +
                ", feedbackType='" + feedbackType + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", userId=" + userId +
                ", createTimeis='" + createTimeis + '\'' +
                ", updateTimeis='" + updateTimeis + '\'' +
                '}';
    }
}
