package com.hrym.rpc.app.dao.model.task.custom;

import java.io.Serializable;

/**
 * Created by hrym13 on 2018/6/21.
 */
public class TaskPlanCustom implements Serializable {

    private Integer taskId;
    private Integer uuid;
    private Integer customId;        // 功课id
    private long planTarget;       // 功课目标
    private Integer startTime;       // 开始时间
    private int planPeriod;          // 功课周期，单位天
    private String remainCron;       // 提醒时间表达式
    private long doneNum;          // 已经完成数量
    private long todayCommitNum;   // 今日提交总数
    private Integer countingMethod;  // 计数方式（手动：0，蓝牙：1，音频：2）
    private long autoDoneNum;      // 自动报数
    private long customDoneNum;    // 手动报数
    private Integer musicId;         // 音频ID
    private Integer createTime;      // 创建时间
    private Integer isExit;          // 是否删除0：已删除；1：未删除
    private Integer updateTime;      // 更新时间
    private Integer recentAdd;       // 1表示最近添加  0表示已点击过

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

    public Integer getCustomId() {
        return customId;
    }

    public void setCustomId(Integer customId) {
        this.customId = customId;
    }

    public long getPlanTarget() {
        return planTarget;
    }

    public void setPlanTarget(long planTarget) {
        this.planTarget = planTarget;
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

    public long getDoneNum() {
        return doneNum;
    }

    public void setDoneNum(long doneNum) {
        this.doneNum = doneNum;
    }

    public long getTodayCommitNum() {
        return todayCommitNum;
    }

    public void setTodayCommitNum(long todayCommitNum) {
        this.todayCommitNum = todayCommitNum;
    }

    public Integer getCountingMethod() {
        return countingMethod;
    }

    public void setCountingMethod(Integer countingMethod) {
        this.countingMethod = countingMethod;
    }

    public long getAutoDoneNum() {
        return autoDoneNum;
    }

    public void setAutoDoneNum(long autoDoneNum) {
        this.autoDoneNum = autoDoneNum;
    }

    public long getCustomDoneNum() {
        return customDoneNum;
    }

    public void setCustomDoneNum(long customDoneNum) {
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

    public Integer getRecentAdd() {
        return recentAdd;
    }

    public void setRecentAdd(Integer recentAdd) {
        this.recentAdd = recentAdd;
    }

    @Override
    public String toString() {
        return "TaskPlanCustom{" +
                "taskId=" + taskId +
                ", uuid=" + uuid +
                ", customId=" + customId +
                ", planTarget=" + planTarget +
                ", startTime=" + startTime +
                ", planPeriod=" + planPeriod +
                ", remainCron='" + remainCron + '\'' +
                ", doneNum=" + doneNum +
                ", todayCommitNum=" + todayCommitNum +
                ", countingMethod=" + countingMethod +
                ", autoDoneNum=" + autoDoneNum +
                ", customDoneNum=" + customDoneNum +
                ", musicId=" + musicId +
                ", createTime=" + createTime +
                ", isExit=" + isExit +
                ", updateTime=" + updateTime +
                ", recentAdd=" + recentAdd +
                '}';
    }
}
