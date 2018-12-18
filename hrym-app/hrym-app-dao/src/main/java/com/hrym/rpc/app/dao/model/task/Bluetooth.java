package com.hrym.rpc.app.dao.model.task;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by mj on 2017/12/23.
 */
@Table(name = "t_bluetooth")
public class Bluetooth implements Serializable {

    @Id
    private Integer bluetoothId;

    private String bluetoothAddress;

    private String bluetoothPassword;

    private Integer createTime;

    private Integer updateTime;

    public Integer getBluetoothId() {
        return bluetoothId;
    }

    public void setBluetoothId(Integer bluetoothId) {
        this.bluetoothId = bluetoothId;
    }

    public String getBluetoothAddress() {
        return bluetoothAddress;
    }

    public void setBluetoothAddress(String bluetoothAddress) {
        this.bluetoothAddress = bluetoothAddress;
    }

    public String getBluetoothPassword() {
        return bluetoothPassword;
    }

    public void setBluetoothPassword(String bluetoothPassword) {
        this.bluetoothPassword = bluetoothPassword;
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
        return "Bluetooth{" +
                "bluetoothId=" + bluetoothId +
                ", bluetoothAddress='" + bluetoothAddress + '\'' +
                ", bluetoothPassword='" + bluetoothPassword + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
