package com.hrym.rpc.app.dao.model.VO.customVO;

/**
 * Created by hrym13 on 2018/6/20.
 * 自定义功课实体类
 */
public class ResourceItemCustomVO {

    private Integer customId;
    private String customName;
    private Integer userId;
    private Integer createTime;
    private Integer updateTime;

    public Integer getCustomId() {
        return customId;
    }

    public void setCustomId(Integer customId) {
        this.customId = customId;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
        return "ResourceItemCustom{" +
                "customId=" + customId +
                ", customName='" + customName + '\'' +
                ", userId=" + userId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
