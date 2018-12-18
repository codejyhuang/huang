package com.hrym.rpc.auth.service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import com.hrym.common.base.BaseConstants;
import com.hrym.common.base.BaseResult;
import com.hrym.common.base.PageInfo;
import com.hrym.common.util.DateUtil;
import com.hrym.common.util.NumUtil;
import com.hrym.common.util.RedisUtil;
import com.hrym.common.util.StringUtil;
import com.hrym.rpc.app.common.constant.TaskParam;
import com.hrym.rpc.app.dao.model.task.*;
import com.hrym.rpc.app.dao.model.view.TaskHomeView;
import com.hrym.rpc.auth.dao.mapper.BluetoothTaskMapper;
import com.hrym.rpc.auth.api.BluetoothService;
import com.hrym.rpc.auth.dao.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by mj on 2017/11/28.
 */
public class BluetoothServiceImpl implements BluetoothService {

    //蓝牙提醒次数
    private static final Integer REMIND_NUM = 108;

    @Autowired
    private TaskPlanMapper taskPlanMapper;
    @Autowired
    private BluetoothTaskMapper bluetoothTaskMapper;
    @Autowired
    private TaskItemMapper taskItemMapper;
    @Autowired
    private TaskHomeViewMapper taskHomeViewMapper;
    @Autowired
    private BluetoothVersionMapper bluetoothVersionMapper;
    @Autowired
    private TaskTypeMapper taskTypeMapper;
    @Autowired
    private BluetoothMapper bluetoothMapper;
    @Autowired
    private BluetoothIOSMapper bluetoothIOSMapper;


    /**
     * 蓝牙部分功课列表
     *
     * @return
     */
    @Override
    public BaseResult taskList(TaskParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        //Redis中获取用户对象
        UserInfo user = JSON.parseObject(RedisUtil.get(param.getToken()), UserInfo.class);
        // 核心分页代码
        PageHelper.startPage(param.getPageNo(), BaseConstants.PAGE_SIZE);
        List<TaskHomeView> thv = taskHomeViewMapper.findByUuidAndIsExit(user.getUuid());
        List<Map<String, Object>> list = new ArrayList<>();
        if (0 < thv.size()) {
            for (TaskHomeView t : thv) {
                String percent = "0%";
                if (t.getDoneNum() != 0) {
                    percent = NumUtil.getPercent(t.getDoneNum(), t.getPlanTarget());
                }
                int status = 1;
                int currentTime = DateUtil.currentSecond();
                String newStartTime = null;
                if (null != t.getStartTime()) {
                    //将int时间转换为string类型
                    String startTime = DateUtil.timestampToDates(t.getStartTime(), DateUtil.DATA_PATTON_YYYYMMDD2);
                    //n天后时间
                    String afterAnyDay = DateUtil.getAfterAnyDay(startTime, t.getPlanPeriod(), DateUtil.DATA_PATTON_YYYYMMDD2);
                    long newAfterAnyDay = DateUtil.DateToLinuxTime(afterAnyDay, DateUtil.DATA_PATTON_YYYYMMDD2);
                    //判断开始时间是否过期
                    if (newAfterAnyDay >= currentTime) {
                        status = 1;
                    } else {
                        status = 2;
                        newStartTime = "已逾期";
                    }
                }

                Map<String, Object> maps = Maps.newHashMap();

                maps.put("countingMethod", t.getCountingMethod());
                maps.put("percent", percent);
                maps.put("typeId", t.getTypeId());
                maps.put("taskId", t.getTaskId());
                maps.put("startTime", newStartTime);
                maps.put("status", status);
                maps.put("typeName", t.getTypeName());
                maps.put("doneNum", NumUtil.formatFloatNumber(t.getDoneNum()));
                maps.put("planTarget", NumUtil.formatFloatNumber(t.getPlanTarget()));
                maps.put("isChose", t.getMusicId());
                maps.put("remainCron", t.getRemainCron());
                maps.put("todayCommitNum", t.getTodayCommitNum());
                if (null == t.getRecentAdd()){
                    t.setRecentAdd(0);
                }
                maps.put("recentAdd",t.getRecentAdd());
                //拼接功课名称和做功课进度
                String expression = null;
                String nameStr = null;

                if (t.getTypeId() == 10001) {
                    expression = "第" + (int) t.getDoneNum() + "/" + (int) t.getPlanTarget() + t.getUnitDesc();
                    nameStr = t.getItemName();
                } else if (t.getTypeId() == 10002) {

                    expression = "第" + (int) t.getDoneNum() + "/" + (int) t.getPlanTarget() + t.getUnitDesc();
                    nameStr = t.getItemName();
                } else if (t.getTypeId() == 10004) {

                    expression = "第" + (int) t.getDoneNum() + "/" + (int) t.getPlanTarget() + t.getUnitDesc();
                    nameStr = t.getTypeName() + "·" + t.getItemName();
                } else if (t.getTypeId() == 10005) {

                    expression = "第" + (int) t.getDoneNum() + "/" + (int) t.getPlanTarget() + t.getUnitDesc();
                    nameStr = t.getTypeName() + "·" + t.getItemName();
                } else if (t.getTypeId() == 10010) {
                    t.setItemId(t.getCustomId());
                    t.setItemName(t.getCustomName());
                    expression = "第" + (int) t.getDoneNum() + "/" + (int) t.getPlanTarget() + t.getUnitDesc();
                    nameStr = t.getCustomName();
                }
                maps.put("expression", expression);
                maps.put("nameStr", nameStr);
                maps.put("itemId", t.getItemId());
                maps.put("itemName", t.getItemName());
                list.add(maps);
            }
        }
        PageInfo pageInfo = new PageInfo(thv);

        Map<String, Object> map = Maps.newHashMap();
        map.put("list", list);
        map.put("hasNextPage", pageInfo.isHasNextPage());

        return new BaseResult(code, message, map);
    }

    /**
     * 蓝牙部分功课切换
     *
     * @return
     */
    @Override
    public BaseResult taskCheckout(TaskParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        BluetoothTask bt = new BluetoothTask();
        bt.setBluetoothAddress(param.getBluetoothAddress());
        bt.setUserId(param.getUuid());
        bt.setStatus(1);
        //查找该蓝牙设备下的信息
        BluetoothTask blue = bluetoothTaskMapper.findOneByAddressAndUserId(bt);

        bt.setTaskId(param.getTaskId());
        bt.setItemId(param.getItemId());
        bt.setTypeId(param.getTypeId());
        bt.setRemindNum(param.getRemindNum());
        bt.setBluetoothAddress(param.getBluetoothAddress());
        bt.setStartTime(DateUtil.getDateToLinuxTime(param.getStartTime(), DateUtil.TIME_PATTON_DEFAULT3));
        bt.setSeqNo(param.getSeq_no());

        Bluetooth bluetooth = new Bluetooth();
        bluetooth.setBluetoothAddress(param.getBluetoothAddress());
        //查询库里是否有蓝牙信息
        Bluetooth tooth = bluetoothMapper.selectOne(bluetooth);
        bluetooth.setBluetoothPassword(param.getBluetoothPassword());
        if (null == tooth) {
            bluetooth.setCreateTime(DateUtil.currentSecond());
            bluetooth.setUpdateTime(DateUtil.currentSecond());
            bluetoothMapper.insert(bluetooth);
        } else {
            bluetooth.setUpdateTime(DateUtil.currentSecond());
            bluetoothMapper.updateBluetoothByAddress(bluetooth);
        }

        if (null == blue) {
            //保存蓝牙数据
            bluetoothTaskMapper.insert(bt);
        } else if (param.getTaskId() != blue.getTaskId()) {
            bluetoothTaskMapper.updateStatusByAddress(param.getBluetoothAddress(), blue.getTaskId());
            //保存蓝牙数据
            bluetoothTaskMapper.insert(bt);
        }

        return new BaseResult(code, message, null);
    }

    /**
     * 检查蓝牙是否有连接
     *
     * @return
     */
    @Override
    public BaseResult connectionCheck(TaskParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        //Redis中获取用户对象
        UserInfo user = JSON.parseObject(RedisUtil.get(param.getToken()), UserInfo.class);

        BluetoothTask bluetooth = new BluetoothTask();
        bluetooth.setBluetoothAddress(param.getBluetoothAddress());
        bluetooth.setUserId(user.getUuid());
        //查找该蓝牙设备下的信息
        BluetoothTask bt = bluetoothTaskMapper.findCurrentByAddressAndUserId(bluetooth);
        Map<String, Object> map = Maps.newHashMap();
        if (null != bt) {
            TaskPlan plan = taskPlanMapper.selectByPrimaryKey(bt.getTaskId());
            //1表示有连接0表示无连接
            map.put("status", bt.getStatus());
            map.put("downNum", plan.getDoneNum());
            map.put("startTime", DateUtil.timestampToDates(bt.getStartTime(), DateUtil.TIME_PATTON_DEFAULT3));
            map.put("taskId", bt.getTaskId());
            map.put("itemId", bt.getItemId());
            map.put("userId", bt.getUserId());
            map.put("typeId", bt.getTypeId());
            map.put("reportNum", bt.getReportNum());
            map.put("currentNum",bt.getCurrentNum());
            String nameStr = null;
            TaskType taskType = taskTypeMapper.selectByPrimaryKey(bt.getTypeId());
            TaskItem taskItem = taskItemMapper.findTaskItemById(bt.getItemId(), bt.getTypeId());
            if (bt.getTypeId() == 10001) {
                nameStr = taskItem.getItemName();
            } else if (bt.getTypeId() == 10002) {
                nameStr = taskItem.getItemName();
            } else if (bt.getTypeId() == 10004) {
                nameStr = taskType.getTypeName() + "·" + taskItem.getItemName();
            } else if (bt.getTypeId() == 10005) {
                nameStr = taskType.getTypeName() + "·" + taskItem.getItemName();
            }
            map.put("nameStr", nameStr);
        } else {
            map.put("downNum", null);
            map.put("startTime", null);
            map.put("taskId", null);
            map.put("itemId", null);
            map.put("userId", null);
            map.put("typeId", null);
            map.put("reportNum", null);
            map.put("currentNum",null);
            map.put("nameStr", null);
            map.put("status", "0");
        }

        return new BaseResult(code, message, map);
    }

    /**
     * 蓝牙固件更新
     *
     * @return
     */
    @Override
    public BaseResult bluetoothUpdate() {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        List<BluetoothVersion> list = bluetoothVersionMapper.findMaxIdInfo();
        BluetoothVersion blue = null;
        if(list.size()>0)
            blue = list.get(0);
        else
            blue = new BluetoothVersion();

        return new BaseResult(code, message, blue);
    }

    /**
     * 选择要上报的功课
     *
     * @return
     */
    @Override
    public BaseResult ChoseReportTask(TaskParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        //Redis中获取用户对象
        UserInfo user = JSON.parseObject(RedisUtil.get(param.getToken()), UserInfo.class);
        List<TaskHomeView> thv = taskHomeViewMapper.findByUuidAndIsExit(user.getUuid());
        List<Map<String, Object>> list = new ArrayList<>();
        if (0 < thv.size()) {
            for (TaskHomeView t : thv) {

                Map<String, Object> maps = Maps.newHashMap();

                maps.put("typeId", t.getTypeId());
                maps.put("taskId", t.getTaskId());
                maps.put("typeName", t.getTypeName());
                //拼接功课名称和做功课进度
                String nameStr = null;

                if (t.getTypeId() == 10001) {

                    nameStr = t.getItemName();
                } else if (t.getTypeId() == 10002) {

                    nameStr = t.getItemName();
                } else if (t.getTypeId() == 10004) {

                    nameStr = t.getTypeName() + "·" + t.getItemName();
                } else if (t.getTypeId() == 10005) {

                    nameStr = t.getTypeName() + "·" + t.getItemName();
                } else if (t.getTypeId() == 10010) {

                    t.setItemId(t.getCustomId());
                    t.setItemName(t.getCustomName());
                    nameStr = t.getItemName();
                }

                maps.put("nameStr", nameStr);
                maps.put("itemId", t.getItemId());
                maps.put("itemName", t.getItemName());
                list.add(maps);
            }
        }

        Map<String, Object> map = Maps.newHashMap();
        map.put("list", list);

        return new BaseResult(code, message, map);
    }

    /**
     * 查找上次提醒次数
     * @param param
     * @return
     */
    @Override
    public BaseResult findLastRemindNum(TaskParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        //Redis中获取用户对象
        UserInfo user = JSON.parseObject(RedisUtil.get(param.getToken()), UserInfo.class);
        //查询该蓝牙地址下这类功课的上次的提醒次数
        BluetoothTask task = bluetoothTaskMapper.findOneByAddressAndUserIdAndMaxId(param.getBluetoothAddress(),user.getUuid(),param.getItemId());
        Map<String, Object> map = Maps.newHashMap();

        map.put("remindNum",REMIND_NUM);
        if (null != task){
            if (null != task.getRemindNum()){
                map.put("remindNum",task.getRemindNum());
            }
        }

        //查询该用户，该蓝牙最后一次连接情况
        task = bluetoothTaskMapper.findBluetoothByAddressAndUserId(param.getBluetoothAddress(),user.getUuid());

        if (null != task){
            if(null != task.getSeqNo())
                map.put("seq_no", task.getSeqNo());
            else
                map.put("seq_no", 0);
            map.put("task_id",task.getTaskId());

        }else {
            map.put("seq_no",0);
            map.put("task_id",0);
        }

        return new BaseResult(code,message,map);
    }


    /**
     * IOS保存蓝牙
     * @param param
     * @return
     */
    @Override
    public BaseResult saveBluetoothIOS(TaskParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        //Redis中获取用户对象
        UserInfo user = JSON.parseObject(RedisUtil.get(param.getToken()), UserInfo.class);

        BluetoothIOS bluetoothIOS = new BluetoothIOS();
        bluetoothIOS.setBluetoothAddress(param.getBluetoothAddress());
        bluetoothIOS.setUserId(user.getUuid());
        //库里查找有没有这个记录
        BluetoothIOS IOS = bluetoothIOSMapper.selectOne(bluetoothIOS);

        if (null == IOS){
            bluetoothIOS.setCreateTime(DateUtil.currentSecond());
            bluetoothIOS.setUpdateTime(DateUtil.currentSecond());
            bluetoothIOSMapper.insert(bluetoothIOS);
        }else {
            bluetoothIOS.setUpdateTime(DateUtil.currentSecond());
            bluetoothIOS.setId(IOS.getId());
            bluetoothIOSMapper.updateByPrimaryKeySelective(bluetoothIOS);
        }

        return new BaseResult(code,message,null);
    }

    /**
     * IOS查找蓝牙
     * @param param
     * @return
     */
    @Override
    public BaseResult findBluetoothIOS(TaskParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        //Redis中获取用户对象
        UserInfo user = JSON.parseObject(RedisUtil.get(param.getToken()), UserInfo.class);
        BluetoothIOS bluetoothIOS = bluetoothIOSMapper.findByUserIdAndCurrentTime(user.getUuid());

        return new BaseResult(code,message,bluetoothIOS);
    }

}
