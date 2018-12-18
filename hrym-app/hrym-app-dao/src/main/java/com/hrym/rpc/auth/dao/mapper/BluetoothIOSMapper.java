package com.hrym.rpc.auth.dao.mapper;

import com.github.abel533.mapper.Mapper;
import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.task.BluetoothIOS;
import org.apache.ibatis.annotations.Select;

/**
 * Created by mj on 2018/2/6.
 */
public interface BluetoothIOSMapper extends Mapper<BluetoothIOS> {

    /**
     * 根据用户id查找最近记录
     * @param userId
     * @return
     */
    @DataSource("slave")
    @Select("select * from t_bluetooth_ios " +
            "WHERE user_id=#{userId} order by update_time desc limit 1")
    BluetoothIOS findByUserIdAndCurrentTime(Integer userId);
}
