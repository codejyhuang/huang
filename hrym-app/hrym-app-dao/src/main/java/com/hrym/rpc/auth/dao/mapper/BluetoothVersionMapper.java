package com.hrym.rpc.auth.dao.mapper;

import com.github.abel533.mapper.Mapper;
import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.task.BluetoothVersion;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 蓝牙版本mapper
 * Created by mj on 2017/12/14.
 */
public interface BluetoothVersionMapper extends Mapper<BluetoothVersion> {

    /**
     * 查询最大id
     *
     * @return
     */
    @DataSource("slave")
    @Select("select * from t_bluetooth_version order by id desc")
    List<BluetoothVersion> findMaxIdInfo();


}
