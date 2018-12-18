package com.hrym.rpc.app.dao.model.VO;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by hrym13 on 2018/1/12.
 * 用户实体类
 */
@Table(name = "t_user_account")
public class UserInfoVO implements Serializable {

        private static final long serialVersionUID = 1L;
        @Id
        private int uuid;
        private String socialUid;
        private String name;
        private String nickName;
        private String mobile;
        private String email;
        private String passWord;
        private Integer sex;
        private Integer source;
        private String avatar;
        private Integer createdTime;
        private Integer updatedTime;
        private Integer status;         //使用状态1：正常使用；0：已注销
        private Integer ymd;
        private String deviceId;
        private String profile;
        private String identityCardNo;
        private String identityCardUrl1;
        private String identityCardUrl2;
        private String identityCardUrl3;
        private Integer identityAuthState;
        private Integer phoneAuthState;
        private String realName;
        private Integer userTatus;          //1:普通用户；2：上师；
        private Integer recentTime;
        private Integer endTime;
        private Integer startTime;
        private String endTimeis;
        private String startTimeis;

    public String getEndTimeis() {
        return endTimeis;
    }

    public void setEndTimeis(String endTimeis) {
        this.endTimeis = endTimeis;
    }

    public String getStartTimeis() {
        return startTimeis;
    }

    public void setStartTimeis(String startTimeis) {
        this.startTimeis = startTimeis;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getUuid() {
        return uuid;
    }

    public void setUuid(int uuid) {
        this.uuid = uuid;
    }

    public String getSocialUid() {
        return socialUid;
    }

    public void setSocialUid(String socialUid) {
        this.socialUid = socialUid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Integer createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Integer updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getYmd() {
        return ymd;
    }

    public void setYmd(Integer ymd) {
        this.ymd = ymd;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getIdentityCardNo() {
        return identityCardNo;
    }

    public void setIdentityCardNo(String identityCardNo) {
        this.identityCardNo = identityCardNo;
    }

    public String getIdentityCardUrl1() {
        return identityCardUrl1;
    }

    public void setIdentityCardUrl1(String identityCardUrl1) {
        this.identityCardUrl1 = identityCardUrl1;
    }

    public String getIdentityCardUrl2() {
        return identityCardUrl2;
    }

    public void setIdentityCardUrl2(String identityCardUrl2) {
        this.identityCardUrl2 = identityCardUrl2;
    }

    public String getIdentityCardUrl3() {
        return identityCardUrl3;
    }

    public void setIdentityCardUrl3(String identityCardUrl3) {
        this.identityCardUrl3 = identityCardUrl3;
    }

    public Integer getIdentityAuthState() {
        return identityAuthState;
    }

    public void setIdentityAuthState(Integer identityAuthState) {
        this.identityAuthState = identityAuthState;
    }

    public Integer getPhoneAuthState() {
        return phoneAuthState;
    }

    public void setPhoneAuthState(Integer phoneAuthState) {
        this.phoneAuthState = phoneAuthState;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getUserTatus() {
        return userTatus;
    }

    public void setUserTatus(Integer userTatus) {
        this.userTatus = userTatus;
    }

    public Integer getRecentTime() {
        return recentTime;
    }

    public void setRecentTime(Integer recentTime) {
        this.recentTime = recentTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "UserInfoVO{" +
                "uuid=" + uuid +
                ", socialUid='" + socialUid + '\'' +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", passWord='" + passWord + '\'' +
                ", sex=" + sex +
                ", source=" + source +
                ", avatar='" + avatar + '\'' +
                ", createdTime=" + createdTime +
                ", updatedTime=" + updatedTime +
                ", status=" + status +
                ", ymd=" + ymd +
                ", deviceId='" + deviceId + '\'' +
                ", profile='" + profile + '\'' +
                ", identityCardNo='" + identityCardNo + '\'' +
                ", identityCardUrl1='" + identityCardUrl1 + '\'' +
                ", identityCardUrl2='" + identityCardUrl2 + '\'' +
                ", identityCardUrl3='" + identityCardUrl3 + '\'' +
                ", identityAuthState=" + identityAuthState +
                ", phoneAuthState=" + phoneAuthState +
                ", realName='" + realName + '\'' +
                ", userTatus=" + userTatus +
                ", recentTime=" + recentTime +
                ", endTime=" + endTime +
                ", startTime=" + startTime +
                ", endTimeis='" + endTimeis + '\'' +
                ", startTimeis='" + startTimeis + '\'' +
                '}';
    }
}
