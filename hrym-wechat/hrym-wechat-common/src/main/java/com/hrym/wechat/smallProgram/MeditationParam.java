package com.hrym.wechat.smallProgram;

import com.hrym.common.base.BaseRequestParam;

import java.io.Serializable;

/**
 * Created by hrym13 on 2018/4/16.
 */
public class MeditationParam extends BaseRequestParam implements Serializable {

    private MeditationData data;

    public MeditationData getData() {
        return data;
    }

    public void setData(MeditationData data) {
        this.data = data;
    }

    public Integer getMeditationId() {
        return data.getMeditationId();
    }

    public String getOpenId() {
        return data.getOpenId();
    }

    public Integer getUserId() {
        return data.getUserId();
    }

    public String getGroupId() {
        return data.getGroupId();
    }

    public Integer getUpdateTime() {
        return data.getUpdateTime();
    }

    public Integer getCreatedTime() {
        return data.getCreatedTime();
    }

    public String getMeditationName() {
        return data.getMeditationName();
    }

    public String getMeditationIntro() {
        return data.getMeditationIntro();
    }

    public String getCodeUrl() {
        return data.getCodeUrl();
    }

    public String getMeditationUrl() {
        return data.getMeditationUrl();
    }

    public Integer getMeditationType() {
        return data.getMeditationType();
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

    public String getOpenGId() {
        return data.getOpenGId();
    }

    public String getJscode() {
        return data.getJscode();
    }

}
class MeditationData implements Serializable{

    private Integer meditationId;
    private  Integer userId;
    private String groupId;                    //群ID
    private Integer createdTime;
    private String meditationName;      //群名称
    private  String meditationIntro;    //群简介
    private String codeUrl;             //群二维码
    private String meditationUrl;       //群头像
    private Integer meditationType;     //群类型
    private String openId;
    private  Integer updateTime;

    private String sessionKey;
    private String encryptedData;
    private String iv;
    private Integer status;
    private String userInfo;
    private String openGId;
    private String jscode;
    private String unionId;
    private Integer uuid;
    
    public String getUnionId() {
        return unionId;
    }
    
    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }
    
    public Integer getUuid() {
        return uuid;
    }
    
    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }
    
    public String getJscode() {
        return jscode;
    }

    public void setJscode(String jscode) {
        this.jscode = jscode;
    }

    public String getOpenGId() {
        return openGId;
    }

    public void setOpenGId(String openGId) {
        this.openGId = openGId;
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

    public Integer getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public void setMeditationId(Integer meditationId) {
        this.meditationId = meditationId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setCreatedTime(Integer createdTime) {
        this.createdTime = createdTime;
    }

    public void setMeditationName(String meditationName) {
        this.meditationName = meditationName;
    }

    public void setMeditationIntro(String meditationIntro) {
        this.meditationIntro = meditationIntro;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }

    public void setMeditationUrl(String meditationUrl) {
        this.meditationUrl = meditationUrl;
    }

    public void setMeditationType(Integer meditationType) {
        this.meditationType = meditationType;
    }

    public Integer getMeditationId() {
        return meditationId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getCreatedTime() {
        return createdTime;
    }

    public String getMeditationName() {
        return meditationName;
    }

    public String getMeditationIntro() {
        return meditationIntro;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public String getMeditationUrl() {
        return meditationUrl;
    }

    public Integer getMeditationType() {
        return meditationType;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
