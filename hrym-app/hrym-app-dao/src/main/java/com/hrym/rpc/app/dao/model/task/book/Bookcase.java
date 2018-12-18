package com.hrym.rpc.app.dao.model.task.book;

import java.io.Serializable;

/**
 * Created by mj on 2018/4/16.
 */
public class Bookcase implements Serializable {

    private Integer indexId;
    private Integer userId;
    private Integer itemId;
    private Integer typeId;
    private Integer doneNum;
    private String percent;
    private Integer createTime;
    private Integer updateTime;
    private Integer isExist;

    public Integer getIndexId() {
        return indexId;
    }

    public void setIndexId(Integer indexId) {
        this.indexId = indexId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getDoneNum() {
        return doneNum;
    }

    public void setDoneNum(Integer doneNum) {
        this.doneNum = doneNum;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
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

    public Integer getIsExist() {
        return isExist;
    }

    public void setIsExist(Integer isExist) {
        this.isExist = isExist;
    }

    @Override
    public String toString() {
        return "Bookcase{" +
                "indexId=" + indexId +
                ", userId=" + userId +
                ", itemId=" + itemId +
                ", typeId=" + typeId +
                ", doneNum=" + doneNum +
                ", percent='" + percent + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isExist=" + isExist +
                '}';
    }
}
