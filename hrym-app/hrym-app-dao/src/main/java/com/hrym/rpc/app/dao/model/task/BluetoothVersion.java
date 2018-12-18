package com.hrym.rpc.app.dao.model.task;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 蓝牙版本实体类
 * Created by mj on 2017/12/14.
 */
@Table(name = "t_bluetooth_version")
public class BluetoothVersion implements Serializable{

    @Id
    private Integer id;     //主键id
    private String bluetoothVersion;        //蓝牙版本
    private String address;     //固件a下载地址
    private Integer createTime;     //创建时间
    private Integer isUpdate;       //0：不更新；1：需要更新
    private String bluetoothName;   //蓝牙名称
    private String address2; //固件b下载地址


    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBluetoothVersion() {
        return bluetoothVersion;
    }

    public void setBluetoothVersion(String bluetoothVersion) {
        this.bluetoothVersion = bluetoothVersion;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Integer getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Integer isUpdate) {
        this.isUpdate = isUpdate;
    }

    public String getBluetoothName() {
        return bluetoothName;
    }

    public void setBluetoothName(String bluetoothName) {
        this.bluetoothName = bluetoothName;
    }

    @Override
    public String toString() {
        return "BluetoothVersion{" +
                "id=" + id +
                ", bluetoothVersion='" + bluetoothVersion + '\'' +
                ", address='" + address + '\'' +
                ", createTime=" + createTime +
                ", isUpdate=" + isUpdate +
                ", bluetoothName='" + bluetoothName + '\'' +
                ", address2='" + address2 + '\'' +
                '}';
    }
}
