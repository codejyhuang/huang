package com.hrym.rpc.app.dao.model.task;

import java.io.Serializable;

/**
 * Created by mj on 2017/7/18.
 */
public class ThumbsUp implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer userId;
    private Integer topicId;
    private Integer time;
    private Integer beizanrenId;
    private Integer type;
    private Integer topicType;

    private UserInfo userInfo;//关联查询用户昵称

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getBeizanrenId() {
        return beizanrenId;
    }

    public void setBeizanrenId(Integer beizanrenId) {
        this.beizanrenId = beizanrenId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Integer getTopicType() {
        return topicType;
    }

    public void setTopicType(Integer topicType) {
        this.topicType = topicType;
    }

    @Override
    public String toString() {
        return "ThumbsUp{" +
                "userId=" + userId +
                ", topicId=" + topicId +
                ", time=" + time +
                ", beizanrenId=" + beizanrenId +
                ", type=" + type +
                ", topicType=" + topicType +
                ", userInfo=" + userInfo +
                '}';
    }
}
