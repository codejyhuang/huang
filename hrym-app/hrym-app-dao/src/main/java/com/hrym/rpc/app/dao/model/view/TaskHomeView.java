package com.hrym.rpc.app.dao.model.view;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * 功课主页视图实体类
 * Created by mj on 2017/12/12.
 */
@Table(name = "task_home_view")
public class TaskHomeView implements Serializable {

    private Integer taskId;
    private Integer uuid;
    private Integer itemId;
    private double planTarget;
    private Integer unitType;
    private Integer startTime;
    private int planPeriod;
    private String remainCron;
    private double doneNum;
    private Integer lastCommitTime;
    private double lastCommitNum;
    private double todayCommitNum;
    private Integer countingMethod;
    private Integer typeId;
    private Integer thumbsUpNum;
    private Integer itemContentId;
    private double autoDoneNum;
    private double customDoneNum;
    private Integer musicId;
    private Integer createTime;
    private Integer isExit;
    private Integer updateTime;
    private String itemName;
    private String itemIntro;
    private String itemPic;
    private String titleDesc;
    private String typeName;
    private String typeDesc;
    private Integer isSupport;
    private Integer customId;
    private String customName;
    private String unitDesc;
    private Integer recentAdd;

    @Transient
    private Integer targetNum;


    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public double getPlanTarget() {
        return planTarget;
    }

    public void setPlanTarget(double planTarget) {
        this.planTarget = planTarget;
    }

    public Integer getUnitType() {
        return unitType;
    }

    public void setUnitType(Integer unitType) {
        this.unitType = unitType;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public int getPlanPeriod() {
        return planPeriod;
    }

    public void setPlanPeriod(int planPeriod) {
        this.planPeriod = planPeriod;
    }

    public String getRemainCron() {
        return remainCron;
    }

    public void setRemainCron(String remainCron) {
        this.remainCron = remainCron;
    }

    public double getDoneNum() {
        return doneNum;
    }

    public void setDoneNum(double doneNum) {
        this.doneNum = doneNum;
    }

    public Integer getLastCommitTime() {
        return lastCommitTime;
    }

    public void setLastCommitTime(Integer lastCommitTime) {
        this.lastCommitTime = lastCommitTime;
    }

    public double getLastCommitNum() {
        return lastCommitNum;
    }

    public void setLastCommitNum(double lastCommitNum) {
        this.lastCommitNum = lastCommitNum;
    }

    public double getTodayCommitNum() {
        return todayCommitNum;
    }

    public void setTodayCommitNum(double todayCommitNum) {
        this.todayCommitNum = todayCommitNum;
    }

    public Integer getCountingMethod() {
        return countingMethod;
    }

    public void setCountingMethod(Integer countingMethod) {
        this.countingMethod = countingMethod;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getThumbsUpNum() {
        return thumbsUpNum;
    }

    public void setThumbsUpNum(Integer thumbsUpNum) {
        this.thumbsUpNum = thumbsUpNum;
    }

    public Integer getItemContentId() {
        return itemContentId;
    }

    public void setItemContentId(Integer itemContentId) {
        this.itemContentId = itemContentId;
    }

    public double getAutoDoneNum() {
        return autoDoneNum;
    }

    public void setAutoDoneNum(double autoDoneNum) {
        this.autoDoneNum = autoDoneNum;
    }

    public double getCustomDoneNum() {
        return customDoneNum;
    }

    public void setCustomDoneNum(double customDoneNum) {
        this.customDoneNum = customDoneNum;
    }

    public Integer getMusicId() {
        return musicId;
    }

    public void setMusicId(Integer musicId) {
        this.musicId = musicId;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Integer getIsExit() {
        return isExit;
    }

    public void setIsExit(Integer isExit) {
        this.isExit = isExit;
    }

    public Integer getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemIntro() {
        return itemIntro;
    }

    public void setItemIntro(String itemIntro) {
        this.itemIntro = itemIntro;
    }

    public String getItemPic() {
        return itemPic;
    }

    public void setItemPic(String itemPic) {
        this.itemPic = itemPic;
    }

    public String getTitleDesc() {
        return titleDesc;
    }

    public void setTitleDesc(String titleDesc) {
        this.titleDesc = titleDesc;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    public Integer getTargetNum() {
        return targetNum;
    }

    public void setTargetNum(Integer targetNum) {
        this.targetNum = targetNum;
    }

    public Integer getIsSupport() {
        return isSupport;
    }

    public void setIsSupport(Integer isSupport) {
        this.isSupport = isSupport;
    }

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

    public String getUnitDesc() {
        return unitDesc;
    }

    public void setUnitDesc(String unitDesc) {
        this.unitDesc = unitDesc;
    }

    public Integer getRecentAdd() {
        return recentAdd;
    }

    public void setRecentAdd(Integer recentAdd) {
        this.recentAdd = recentAdd;
    }

    @Override
    public String toString() {
        return "TaskHomeView{" +
                "taskId=" + taskId +
                ", uuid=" + uuid +
                ", itemId=" + itemId +
                ", planTarget=" + planTarget +
                ", unitType=" + unitType +
                ", startTime=" + startTime +
                ", planPeriod=" + planPeriod +
                ", remainCron='" + remainCron + '\'' +
                ", doneNum=" + doneNum +
                ", lastCommitTime=" + lastCommitTime +
                ", lastCommitNum=" + lastCommitNum +
                ", todayCommitNum=" + todayCommitNum +
                ", countingMethod=" + countingMethod +
                ", typeId=" + typeId +
                ", thumbsUpNum=" + thumbsUpNum +
                ", itemContentId=" + itemContentId +
                ", autoDoneNum=" + autoDoneNum +
                ", customDoneNum=" + customDoneNum +
                ", musicId=" + musicId +
                ", createTime=" + createTime +
                ", isExit=" + isExit +
                ", updateTime=" + updateTime +
                ", itemName='" + itemName + '\'' +
                ", itemIntro='" + itemIntro + '\'' +
                ", itemPic='" + itemPic + '\'' +
                ", titleDesc='" + titleDesc + '\'' +
                ", typeName='" + typeName + '\'' +
                ", typeDesc='" + typeDesc + '\'' +
                ", isSupport=" + isSupport +
                ", customId=" + customId +
                ", customName='" + customName + '\'' +
                ", unitDesc='" + unitDesc + '\'' +
                ", recentAdd=" + recentAdd +
                ", targetNum=" + targetNum +
                '}';
    }
}
