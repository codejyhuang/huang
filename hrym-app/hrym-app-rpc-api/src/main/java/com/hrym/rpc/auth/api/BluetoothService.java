package com.hrym.rpc.auth.api;

import com.hrym.common.base.BaseResult;
import com.hrym.rpc.app.common.constant.TaskParam;

/**
 * 蓝牙部分service
 * Created by mj on 2017/11/28.
 */
public interface BluetoothService {

    /**
     * 蓝牙部分功课列表
     * @return
     */
    BaseResult taskList(TaskParam param);

    /**
     * 蓝牙部分功课切换
     * @return
     */
    BaseResult taskCheckout(TaskParam param);

    /**
     * 检查蓝牙是否有连接
     * @return
     */
    BaseResult connectionCheck(TaskParam param);

    /**
     * 蓝牙固件更新
     * @return
     */
    BaseResult bluetoothUpdate();

    /**
     * 选择要上报的功课
     * @return
     */
    BaseResult ChoseReportTask(TaskParam param);

    /**
     * 查找上次提醒次数
     * @param param
     * @return
     */
    BaseResult findLastRemindNum(TaskParam param);

    /**
     * IOS保存蓝牙
     * @param param
     * @return
     */
    BaseResult saveBluetoothIOS(TaskParam param);

    /**
     * IOS查找蓝牙
     * @param param
     * @return
     */
    BaseResult findBluetoothIOS(TaskParam param);
}
