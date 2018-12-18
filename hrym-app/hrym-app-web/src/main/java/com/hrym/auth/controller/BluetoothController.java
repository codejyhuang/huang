package com.hrym.auth.controller;

import com.hrym.common.base.BaseConstants;
import com.hrym.common.base.BaseController;
import com.hrym.common.base.BaseResult;
import com.hrym.rpc.app.common.constant.TaskParam;
import com.hrym.rpc.auth.api.BluetoothService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 蓝牙部分控制层
 * Created by mj on 2017/11/28.
 */
@Controller
@RequestMapping(value = "/hrym/api")
public class BluetoothController extends BaseController{

    @Autowired
    private BluetoothService bluetoothService;

    @RequestMapping(value = "/bluetooth")
    @ResponseBody
    public BaseResult doBluetooth(@RequestBody TaskParam param){

        String cmd = param.getCmd();
        if ("taskList".equals(cmd)){
            return taskList(param);
        }
        if ("taskCheckout".equals(cmd)){
            return taskCheckout(param);
        }
        if ("connectionCheck".equals(cmd)){
            return connectionCheck(param);
        }
        if ("bluetoothUpdate".equals(cmd)){
            return bluetoothUpdate();
        }
        if ("ChoseReportTask".equals(cmd)){
            return ChoseReportTask(param);
        }
        if ("getRemindNum".equals(cmd)){
            return findLastRemindNum(param);
        }
        if ("saveConnect".equals(cmd)){
            return saveBluetoothIOS(param);
        }
        if ("getConnect".equals(cmd)){
            return findBluetoothIOS(param);
        }

        return new BaseResult(BaseConstants.GWSCODE4007,BaseConstants.GWSMSG4007,null);
    }

    /**
     * 蓝牙部分功课列表
     * @return
     */
    public BaseResult taskList(TaskParam param) {

        return bluetoothService.taskList(param);
    }

    /**
     * 蓝牙部分功课切换
     * @return
     */
    public BaseResult taskCheckout(TaskParam param) {

        return bluetoothService.taskCheckout(param);
    }

    /**
     * 检查蓝牙是否有连接
     * @param param
     * @return
     */
    public BaseResult connectionCheck(TaskParam param) {

        return bluetoothService.connectionCheck(param);
    }

    /**
     * 蓝牙固件更新
     * @return
     */
    public BaseResult bluetoothUpdate() {

        return bluetoothService.bluetoothUpdate();
    }

    /**
     * 选择要上报的功课
     * @return
     */
    BaseResult ChoseReportTask(TaskParam param){

        return bluetoothService.ChoseReportTask(param);
    }

    /**
     * 查找上次提醒次数
     * @param param
     * @return
     */
    public BaseResult findLastRemindNum(TaskParam param) {

        return bluetoothService.findLastRemindNum(param);
    }

    /**
     * IOS保存蓝牙 自动连接
     * @param param
     * @return
     */
    public BaseResult saveBluetoothIOS(TaskParam param) {

        return bluetoothService.saveBluetoothIOS(param);
    }

    /**
     * IOS查找蓝牙 自动连接
     * @param param
     * @return
     */
    public BaseResult findBluetoothIOS(TaskParam param) {

        return bluetoothService.findBluetoothIOS(param);
    }
}
