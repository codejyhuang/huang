package com.hrym.rpc.app.dao.model.task.meditation;

import java.io.Serializable;

/**
 * 禅修计划
 * Created by mj on 2018/5/29.
 */
public class MeditationPlan implements Serializable {

    private Integer id;
    private Integer downNum;
    private Integer itemId;
    private Integer userId;
    private Integer contentId;
    private Integer timeNum;
    private Integer createTime;
    private Integer updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDownNum() {
        return downNum;
    }

    public void setDownNum(Integer downNum) {
        this.downNum = downNum;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public Integer getTimeNum() {
        return timeNum;
    }

    public void setTimeNum(Integer timeNum) {
        this.timeNum = timeNum;
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

    @Override
    public String toString() {
        return "MeditationPlan{" +
                "id=" + id +
                ", downNum=" + downNum +
                ", itemId=" + itemId +
                ", userId=" + userId +
                ", contentId=" + contentId +
                ", timeNum=" + timeNum +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
