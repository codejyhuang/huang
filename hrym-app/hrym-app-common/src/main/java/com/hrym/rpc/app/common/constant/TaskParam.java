package com.hrym.rpc.app.common.constant;

import com.hrym.common.base.BaseRequestParam;

import java.io.Serializable;

/**
 * 功课请求参数
 * Created by mj on 2017/7/6.
 */
public class TaskParam extends BaseRequestParam implements Serializable{

    private TaskData data;

    public TaskData getData() {
        return data;
    }

    public String getGompaFlag() {
        return data.getGompaFlag();
    }

    public String getSubTaskId(){
        return data.getSubTaskId();
    }

    public void setData(TaskData data) {
        this.data = data;
    }

    public String getDeviceId(){
        return data.getDevice_id();
    }

    public Integer getPageNo(){return data.getPageNo();}

    public Integer getPageSize(){
        return data.getPageSize();
    }

    public String getToken(){
        return data.getToken();
    }

    public Integer getTypeId(){
        return data.getType_id();
    }

    public Integer getItemId(){
        return data.getItem_id();
    }

    public Integer getPlanTarget(){
        return data.getPlan_target();
    }

    public Integer getPlanPeriod(){
        return data.getPlan_period();
    }

    public String getRemainCron(){
        return data.getRemain_cron();
    }

    public String getStartTime(){
        return data.getStart_time();
    }

    public Integer getTaskId(){
        return data.getTask_id();
    }

    public String getItemName(){
        return data.getItem_name();
    }

    public Integer getCountingMethod(){
        return data.getCounting_method();
    }

    public Integer getAppendixId(){
        return data.getAppendix_id();
    }

    public double getNum(){
        return data.getNum();
    }

    public Integer getTopicId(){
        return data.getTopicId();
    }

    public Integer getUserId(){
        return data.getUserId();
    }

    public Integer getBeizanrenId(){
        return data.getBeizanren_id();
    }

    public Integer getType(){
        return data.getType();
    }

    public Integer getItemContentId(){
        return data.getItemContentId();
    }

    public Integer getMusicId(){
        return data.getMusicId();
    }

    public Integer getTopicType() {
        return data.getTopicType();
    }

    public Integer getShareType() {
        return data.getShareType();
    }

    public Integer getShareTypeValue() {
        return data.getShareTypeValue();
    }

    public Integer getUuid() {
        return data.getUuid();
    }

    public String getBluetoothAddress() {
        return data.getBluetoothAddress();
    }

    public String getHistoryTime() {
        return data.getHistoryTime();
    }

    public String getBluetoothPassword() {
        return data.getBluetoothPassword();
    }

    public String getCustomName() {
        return data.getCustomName();
    }

    public Integer getRemindNum() {
        return data.getRemindNum();
    }

    public Integer getSort() {
        return data.getSort();
    }

    public Integer getSeq_no() {
        return data.getSeq_no();
    }
}



class TaskData implements Serializable {

    private String device_id;
    private Integer pageNo;
    private Integer pageSize;
    private String token;
    private Integer type_id;
    private Integer item_id;
    private Integer plan_period;
    private Integer plan_target;
    private String remain_cron;
    private String start_time;
    private Integer task_id;
    private String item_name;
    private Integer counting_method;
    private Integer appendix_id;
    private double num;
    private Integer topicId;
    private Integer userId;
    private Integer beizanren_id;
    private Integer type;
    private Integer itemContentId;
    private Integer topicType;
    private Integer shareType;      //分享类型（0：今日功课；1：全部功课；2：社群；）
    private Integer shareTypeValue; //功课（功课ID、任务ID；社群就传社群ID）
    private Integer uuid;
    private String bluetoothAddress;
    private String historyTime;
    private String bluetoothPassword;
    private String customName;
    private Integer remindNum;
    private Integer sort;
    private Integer seq_no;//蓝牙上的功课序号

    public Integer getSeq_no() {
        return seq_no;
    }

    public void setSeq_no(Integer seq_no) {
        this.seq_no = seq_no;
    }

    /**
     * 禅修用功课子id
     */
    private String subTaskId;

    public String getGompaFlag() {
        return gompaFlag;
    }

    public void setGompaFlag(String gompaFlag) {
        this.gompaFlag = gompaFlag;
    }

    /**
     * 禅修状态位
     */
    private String gompaFlag;

    private Integer musicId;

    public String getSubTaskId() {
        return subTaskId;
    }

    public void setSubTaskId(String subTaskId) {
        this.subTaskId = subTaskId;
    }


    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getType_id() {
        return type_id;
    }

    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }

    public Integer getItem_id() {
        return item_id;
    }

    public void setItem_id(Integer item_id) {
        this.item_id = item_id;
    }

    public Integer getPlan_period() {
        return plan_period;
    }

    public void setPlan_period(Integer plan_period) {
        this.plan_period = plan_period;
    }

    public Integer getPlan_target() {
        return plan_target;
    }

    public void setPlan_target(Integer plan_target) {
        this.plan_target = plan_target;
    }

    public String getRemain_cron() {
        return remain_cron;
    }

    public void setRemain_cron(String remain_cron) {
        this.remain_cron = remain_cron;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public Integer getTask_id() {
        return task_id;
    }

    public void setTask_id(Integer task_id) {
        this.task_id = task_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public Integer getCounting_method() {
        return counting_method;
    }

    public void setCounting_method(Integer counting_method) {
        this.counting_method = counting_method;
    }

    public Integer getAppendix_id() {
        return appendix_id;
    }

    public void setAppendix_id(Integer appendix_id) {
        this.appendix_id = appendix_id;
    }

    public double getNum() {
        return num;
    }

    public void setNum(double num) {
        this.num = num;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBeizanren_id() {
        return beizanren_id;
    }

    public void setBeizanren_id(Integer beizanren_id) {
        this.beizanren_id = beizanren_id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getItemContentId() {
        return itemContentId;
    }

    public void setItemContentId(Integer itemContentId) {
        this.itemContentId = itemContentId;
    }

    public Integer getMusicId() {
        return musicId;
    }

    public void setMusicId(Integer musicId) {
        this.musicId = musicId;
    }

    public Integer getTopicType() {
        return topicType;
    }

    public void setTopicType(Integer topicType) {
        this.topicType = topicType;
    }

    public Integer getShareType() {
        return shareType;
    }

    public void setShareType(Integer shareType) {
        this.shareType = shareType;
    }

    public Integer getShareTypeValue() {
        return shareTypeValue;
    }

    public void setShareTypeValue(Integer shareTypeValue) {

        this.shareTypeValue = shareTypeValue;
    }

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public String getBluetoothAddress() {
        return bluetoothAddress;
    }

    public void setBluetoothAddress(String bluetoothAddress) {
        this.bluetoothAddress = bluetoothAddress;
    }

    public String getHistoryTime() {
        return historyTime;
    }

    public void setHistoryTime(String historyTime) {

        this.historyTime = historyTime;
    }

    public String getBluetoothPassword() {
        return bluetoothPassword;
    }

    public void setBluetoothPassword(String bluetoothPassword) {

        this.bluetoothPassword = bluetoothPassword;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {

        this.customName = customName;
    }

    public Integer getRemindNum() {
        return remindNum;
    }

    public void setRemindNum(Integer remindNum) {

        this.remindNum = remindNum;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
