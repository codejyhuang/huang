package com.hrym.rpc.app.dao.model.task;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 自定义功课实体类
 * Created by mj on 2018/1/8.
 */
@Table(name = "t_custom_item")
public class CustomTask implements Serializable {

    @Id
    private Integer customId;          //自定义功课表主键id

    private String customName;         //自定义功课名称

    private Integer userId;            //用户id

    private Integer createTime;         //创建时间

    private Integer updateTime;         //更新时间

    private Integer typeId;

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

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    @Override
    public String toString() {
        return "CustomTask{" +
                "customId=" + customId +
                ", customName='" + customName + '\'' +
                ", userId=" + userId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", typeId=" + typeId +
                '}';
    }
}
