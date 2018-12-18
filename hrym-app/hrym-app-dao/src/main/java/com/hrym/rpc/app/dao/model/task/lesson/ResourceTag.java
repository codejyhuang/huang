package com.hrym.rpc.app.dao.model.task.lesson;

import java.io.Serializable;

/**
 * Created by mj on 2018/6/25.
 */
public class ResourceTag implements Serializable {

    private Integer tagId;
    private String tagName;
    private Integer tagWeight;
    private Integer tagType;
    private Integer createTime;
    private Integer updateTime;

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Integer getTagWeight() {
        return tagWeight;
    }

    public void setTagWeight(Integer tagWeight) {
        this.tagWeight = tagWeight;
    }

    public Integer getTagType() {
        return tagType;
    }

    public void setTagType(Integer tagType) {
        this.tagType = tagType;
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
        return "ResourceTag{" +
                "tagId=" + tagId +
                ", tagName='" + tagName + '\'' +
                ", tagWeight=" + tagWeight +
                ", tagType=" + tagType +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
