package com.hrym.wechat.smallProgram;

import com.hrym.common.base.BaseRequestParam;

import java.io.Serializable;

/**
 * Created by hrym13 on 2018/4/26.
 */
public class AesParam extends BaseRequestParam implements Serializable {

    private AesParamData data;

    public AesParamData getData() {
        return data;
    }

    public void setData(AesParamData data) {
        this.data = data;
    }

    public String getSessionKey() {
        return data.getSessionKey();
    }

    public String getEncryptedData() {
        return data.getEncryptedData();
    }

    public String getIv() {
        return data.getIv();
    }


    public Integer getStatus() {
        return data.getStatus();
    }

    public String getUserInfo() {
        return data.getUserInfo();
    }

    public String getOpenId() {
        return data.getOpenId();
    }

}
class AesParamData implements Serializable{

    private String sessionKey;
    private String encryptedData;
    private String iv;
    private Integer status;
    private String userInfo;
    private String openId;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getEncryptedData() {
        return encryptedData;
    }

    public void setEncryptedData(String encryptedData) {
        this.encryptedData = encryptedData;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }
}
