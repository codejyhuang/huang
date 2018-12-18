package com.hrym.rpc.app.dao.model.activity;

import java.io.Serializable;

/**
 * 活动助力或点赞
 * Created by mj on 2018/4/26.
 */
public class ActivityHelp implements Serializable {

    private Integer id;
    private String fromUserId;
    private String toUserId;
    private Integer createTime;
    private Integer type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ActivityHelp{" +
                "id=" + id +
                ", fromUserId=" + fromUserId +
                ", toUserId=" + toUserId +
                ", createTime=" + createTime +
                ", type=" + type +
                '}';
    }
}
