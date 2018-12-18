package com.hrym.rpc.auth.dao.mapper;

import com.github.abel533.mapper.Mapper;
import com.hrym.rpc.app.dao.model.task.Bluetooth;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * Created by mj on 2017/12/23.
 */
public interface BluetoothMapper extends Mapper<Bluetooth> {

    /**
     * 通过蓝牙地址更新蓝牙信息
     */
    @Update("update t_bluetooth set bluetooth_address=#{b.bluetoothAddress},bluetooth_password=#{b.bluetoothPassword}," +
            "update_time=#{b.updateTime} where bluetooth_address=#{b.bluetoothAddress}")
    void updateBluetoothByAddress(@Param("b") Bluetooth bluetooth);
}
