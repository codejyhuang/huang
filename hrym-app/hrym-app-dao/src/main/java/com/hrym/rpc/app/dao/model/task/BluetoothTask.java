package com.hrym.rpc.app.dao.model.task;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 蓝牙计数实体类
 * Created by mj on 2017/12/6.
 */
@Table(name = "t_bluetooth_task")
public class BluetoothTask implements Serializable {

    /**
     * 蓝牙功课id
     */
    @Id
    private Integer id;
    /**
     * 蓝牙地址
     */
    private String bluetoothAddress;
    /**
     * 功课任务id
     */
    private Integer taskId;
    /**
     * 功课id
     */
    private Integer itemId;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 功课类型id
     */
    private Integer typeId;
    /**
     * 功课开始时间
     */
    private Integer startTime;
    /**
     * 上报数量
     */
    private double reportNum;
    /**
     * 蓝牙状态0 ：未连接；1已连接
     */
    private Integer status;
    /**
     * 上报提醒次数
     */
    private Integer remindNum;
    /**
     * 上报临时数量
     */
    private Integer currentNum;

    /**
     * 蓝牙上功课序号
     */
    private Integer seqNo;

    public Integer getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBluetoothAddress() {
        return bluetoothAddress;
    }

    public void setBluetoothAddress(String bluetoothAddress) {
        this.bluetoothAddress = bluetoothAddress;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public double getReportNum() {
        return reportNum;
    }

    public void setReportNum(double reportNum) {
        this.reportNum = reportNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRemindNum() {
        return remindNum;
    }

    public void setRemindNum(Integer remindNum) {
        this.remindNum = remindNum;
    }

    public Integer getCurrentNum() {
        return currentNum;
    }

    public void setCurrentNum(Integer currentNum) {
        this.currentNum = currentNum;
    }


    @Override
    public String toString() {
        return "BluetoothTask{" +
                "id=" + id +
                ", bluetoothAddress='" + bluetoothAddress + '\'' +
                ", taskId=" + taskId +
                ", itemId=" + itemId +
                ", userId=" + userId +
                ", typeId=" + typeId +
                ", startTime=" + startTime +
                ", reportNum=" + reportNum +
                ", status=" + status +
                ", remindNum=" + remindNum +
                ", currentNum=" + currentNum +
                ", seqNo=" + seqNo +
                '}';
    }
}
