package com.hrym.rpc.app.dao.model.activity;

import java.io.Serializable;

/**
 * Created by mj on 2018/4/19.
 */
public class ActivityPray implements Serializable {

    private Integer id;
    private String fromUserId;
    private String toUserName;
    private String prayContent;
    private Integer createTime;
    private Integer updateTime;
    private String fromUserName;
    private Integer source;
    private String fromUserAvatar;

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

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

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getPrayContent() {
        return prayContent;
    }

    public void setPrayContent(String prayContent) {
        this.prayContent = prayContent;
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

    public String getFromUserAvatar() {
        return fromUserAvatar;
    }

    public void setFromUserAvatar(String fromUserAvatar) {
        this.fromUserAvatar = fromUserAvatar;
    }

    @Override
    public String toString() {
        return "ActivityPray{" +
                "id=" + id +
                ", fromUserId=" + fromUserId +
                ", toUserName='" + toUserName + '\'' +
                ", prayContent='" + prayContent + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", fromUserName='" + fromUserName + '\'' +
                ", source=" + source +
                ", fromUserAvatar='" + fromUserAvatar + '\'' +
                '}';
    }
}
