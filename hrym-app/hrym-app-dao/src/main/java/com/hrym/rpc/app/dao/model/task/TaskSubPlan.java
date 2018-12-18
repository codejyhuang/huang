package com.hrym.rpc.app.dao.model.task;

import java.io.Serializable;

/**
 * Created by mj on 2017/8/24.
 */
public class TaskSubPlan implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer subTaskId;
    private Integer taskId;
    private double  downNum;
    private Integer itemId;

    public Integer getSubTaskId() {
        return subTaskId;
    }

    public void setSubTaskId(Integer subTaskId) {
        this.subTaskId = subTaskId;
    }

    public double getDownNum() {
        return downNum;
    }

    public void setDownNum(double downNum) {
        this.downNum = downNum;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    @Override
    public String toString() {
        return "TaskSubPlan{" +
                "subTaskId=" + subTaskId +
                ", taskId=" + taskId +
                ", downNum=" + downNum +
                ", itemId=" + itemId +
                '}';
    }
}
