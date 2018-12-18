package com.hrym.common.message;

/**
 * 消息bean
 *
 * Created by hong on 2017/8/31.
 */
public class MessageBean {

    /**
     * 消息主键
     */
    private long msgId;

    /**
     * 消息主题
     */
    private String title;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 发送时间秒数
     */
    private long createTime;

    /**
     * 发送方式(1:广播，2：标签，3：点对点)
     */
    private int sendType;

    /**
     * 广播：空串
     * 标签：标签名称
     * 点对点：用户id
     */
    private String sendTypeValue;

    /**
     * 消息类型(1:系统；2：社群；3：功课;4:用户认证)
     */
    private int msgType;

    /**
     * 消息类型值(如社群id，功课id；用户id)
     *
     * 社群：社群id
     * 功课：功课id
     * 用户认证：用户id
     */
    private String msgTypeValue;

    /**
     * 消息状态
     * 社群：0:社群申请，1: 审批通过，2: 审批驳回，3:退出群
     * 用户管理：0:认证中，1:审批通过，2:审批驳回
     */
    private int status;

    /**
     * 消息子类型
     */
    private String subMsgType;

    /**
     * 消息子类型值
     */
    private String subMsgTypeValue;

    private String cmd;
    private String platform;
    private String appName;
    private String appVersion;

    public int getSendType() {
        return sendType;
    }

    public void setSendType(int sendType) {
        this.sendType = sendType;
    }

    public String getSendTypeValue() {
        return sendTypeValue;
    }

    public void setSendTypeValue(String sendTypeValue) {
        this.sendTypeValue = sendTypeValue;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getMsgId() {
        return msgId;
    }

    public void setMsgId(long msgId) {
        this.msgId = msgId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }

    public String getMsgTypeValue() {
        return msgTypeValue;
    }

    public void setMsgTypeValue(String msgTypeValue) {
        this.msgTypeValue = msgTypeValue;
    }

    public String getSubMsgType() {
        return subMsgType;
    }

    public void setSubMsgType(String subMsgType) {
        this.subMsgType = subMsgType;
    }

    public String getSubMsgTypeValue() {
        return subMsgTypeValue;
    }

    public void setSubMsgTypeValue(String subMsgTypeValue) {

        this.subMsgTypeValue = subMsgTypeValue;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    @Override
    public String toString() {
        return "MessageBean{" +
                "msgId=" + msgId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", sendType=" + sendType +
                ", sendTypeValue='" + sendTypeValue + '\'' +
                ", msgType=" + msgType +
                ", msgTypeValue='" + msgTypeValue + '\'' +
                ", status=" + status +
                ", subMsgType='" + subMsgType + '\'' +
                ", subMsgTypeValue='" + subMsgTypeValue + '\'' +
                ", cmd='" + cmd + '\'' +
                ", platform='" + platform + '\'' +
                ", appName='" + appName + '\'' +
                ", appVersion='" + appVersion + '\'' +
                '}';
    }
}
