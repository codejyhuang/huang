package com.hrym.rpc.auth.dao.mapper;

import com.github.abel533.mapper.Mapper;
import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.task.BluetoothTask;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 蓝牙计数mapper
 * Created by mj on 2017/12/6.
 */
public interface BluetoothTaskMapper extends Mapper<BluetoothTask> {

    /**
     * 根据蓝牙地址更新蓝牙功课信息
     * @param num
     */
    @Update("update t_bluetooth_task set report_num=report_num+#{num},current_num=#{num} " +
            "where bluetooth_address=#{address} and task_id=#{taskId} and status=1")
    void updateReportNumByAddress(@Param("num") double num,@Param("address") String address,@Param("taskId") Integer taskId);

    /**
     * 根据蓝牙地址更新蓝牙功课连接状态
     * @param
     */
    @Update("update t_bluetooth_task set status=0 " +
            "where bluetooth_address=#{address} and task_id=#{taskId} and status=1")
    void updateStatusByAddress(@Param("address") String address,@Param("taskId") Integer taskId);

    /**
     * 根据功课任务id更新蓝牙功课连接状态
     * @param
     */
    @Update("update t_bluetooth_task set status=0 " +
            "where task_id=#{taskId} and status=1")
    void updateStatusByTaskId(@Param("taskId") Integer taskId);

    /**
     * 根据蓝牙地址和用户id查询蓝牙功课信息
     * @param bluetoothTask
     * @return
     */
    @DataSource("slave")
    @Select("select * from t_bluetooth_task where bluetooth_address=#{b.bluetoothAddress} and user_id=#{b.userId} and status=#{b.status}")
    BluetoothTask findOneByAddressAndUserId(@Param("b") BluetoothTask bluetoothTask);

    /**
     * 根据蓝牙地址和用户id查询蓝牙功课信息
     * @param bluetoothTask
     * @return
     */
    @DataSource("slave")
    @Select("select * from t_bluetooth_task " +
            "where bluetooth_address=#{b.bluetoothAddress} and user_id=#{b.userId} " +
            "order by id desc limit 1")
    BluetoothTask findCurrentByAddressAndUserId(@Param("b") BluetoothTask bluetoothTask);

    /**
     * 根据蓝牙地址、任务id和用户id查询蓝牙功课信息
     * @param bluetoothTask
     * @return
     */
    @DataSource("slave")
    @Select("select * from t_bluetooth_task " +
            "where bluetooth_address=#{b.bluetoothAddress} and user_id=#{b.userId} and task_id=#{b.taskId} and status=#{b.status}")
    BluetoothTask findOneByAddressAndUserIdAndTaskId(@Param("b") BluetoothTask bluetoothTask);

    /**
     * 查询用户下该蓝牙地址的最后一条记录
     * @param address
     * @param userId
     * @return
     */
    @DataSource("slave")
    @Select("select * from `t_bluetooth_task` " +
            "WHERE bluetooth_address=#{address} and user_id=#{userId} and item_id=#{itemId} order by id desc limit 1")
    BluetoothTask findOneByAddressAndUserIdAndMaxId(@Param("address") String address,@Param("userId") Integer userId,@Param("itemId") Integer itemId);

    /**
     * 查询用户下该蓝牙地址的最后一条记录
     * @param address
     * @param userId
     * @return
     */
    @DataSource("slave")
    @Select("select * from `t_bluetooth_task` " +
            "WHERE bluetooth_address=#{address} and user_id=#{userId}  order by id desc limit 1")
    BluetoothTask findBluetoothByAddressAndUserId(@Param("address") String address,@Param("userId") Integer userId);

    /**
     * 根据蓝牙地址更新蓝牙功课临时值
     * @param num
     */
    @Update("update t_bluetooth_task set report_num=report_num+#{num},current_num=0 " +
            "where bluetooth_address=#{address} and task_id=#{taskId} and status=1")
    void updateCurrentNumByAddress(@Param("num") double num,@Param("address") String address,@Param("taskId") Integer taskId);
}
