package com.hrym.rpc.app.dao.model.task;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * Created by mj on 2018/1/2.
 */
@Table(name = "t_area")
public class TaskArea implements Serializable {

    /**
     * 专区表id
     */
    @Id
    private Integer areaId;
    /**
     * 专区名称
     */
    private String areaName;
    /**
     * 功课id
     */
    private Integer itemId;
    /**
     * 内容版本
     */
    private String version;
    /**
     * 功课目标
     */
    private Integer taskTarget;
    /**
     * 功课期限
     */
    private Integer taskPeriod;

    /**
     * 专区类型(1：常用功课专区；2：bsa功课专区；3：修行专区；4：热门功课)
     */
    private Integer areaType;
    /**
     * 创建时间
     */
    private Integer createTime;
    /**
     * 修改时间
     */
    private Integer updateTime;
    /**
     * 功课类型id
     */
    private Integer typeId;

    @Transient
    private String typeDesc;
    @Transient
    private String itemName;
    @Transient
    private Integer donePeopleNum;
    @Transient
    private Integer currentPeopleNum;
    @Transient
    private String unitDesc;
    @Transient
    private String typeName;
    @Transient
    private String versionName;
    @Transient
    private String isAdd;


    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getTaskTarget() {
        return taskTarget;
    }

    public void setTaskTarget(Integer taskTarget) {
        this.taskTarget = taskTarget;
    }

    public Integer getTaskPeriod() {
        return taskPeriod;
    }

    public void setTaskPeriod(Integer taskPeriod) {
        this.taskPeriod = taskPeriod;
    }

    public Integer getAreaType() {
        return areaType;
    }

    public void setAreaType(Integer areaType) {
        this.areaType = areaType;
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

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    public Integer getDonePeopleNum() {
        return donePeopleNum;
    }

    public void setDonePeopleNum(Integer donePeopleNum) {
        this.donePeopleNum = donePeopleNum;
    }

    public Integer getCurrentPeopleNum() {
        return currentPeopleNum;
    }

    public void setCurrentPeopleNum(Integer currentPeopleNum) {
        this.currentPeopleNum = currentPeopleNum;
    }

    public String getUnitDesc() {
        return unitDesc;
    }

    public void setUnitDesc(String unitDesc) {
        this.unitDesc = unitDesc;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }


    public String getIsAdd() {
        return isAdd;
    }

    public void setIsAdd(String isAdd) {
        this.isAdd = isAdd;
    }

    @Override
    public String toString() {
        return "TaskArea{" +
                "areaId=" + areaId +
                ", areaName='" + areaName + '\'' +
                ", itemId=" + itemId +
                ", version='" + version + '\'' +
                ", taskTarget=" + taskTarget +
                ", taskPeriod=" + taskPeriod +
                ", areaType=" + areaType +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", typeId=" + typeId +
                ", typeDesc='" + typeDesc + '\'' +
                ", itemName='" + itemName + '\'' +
                ", donePeopleNum=" + donePeopleNum +
                ", currentPeopleNum=" + currentPeopleNum +
                ", unitDesc='" + unitDesc + '\'' +
                ", typeName='" + typeName + '\'' +
                ", isAdd='" + isAdd + '\'' +
                ", versionName='" + versionName + '\'' +
                '}';
    }
}
