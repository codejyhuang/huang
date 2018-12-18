package com.hrym.wechat.entity;

/**
 * Created by hrym13 on 2018/4/27.
 */
public class Aes {
    private String openGId;
    private String openId;
    private String msg;
    private Integer status;

    public String getOpenGId() {
        return openGId;
    }

    public void setOpenGId(String openGId) {
        this.openGId = openGId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Aes{" +
                "openGId='" + openGId + '\'' +
                ", openId='" + openId + '\'' +
                ", msg='" + msg + '\'' +
                ", status=" + status +
                '}';
    }
}
