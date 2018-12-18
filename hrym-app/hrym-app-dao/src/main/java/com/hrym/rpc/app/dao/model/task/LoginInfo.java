package com.hrym.rpc.app.dao.model.task;

import java.io.Serializable;

/**
 * 用户登录信息实体类
 *
 * Created by mj on 2017/6/22.
 */
public class LoginInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer uuid;
    private String token;
    private Integer isSocialLogin;
    private Integer loginTime;
    private String phoneModel;
    private Integer os;
    private String osVersion;

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getIsSocialLogin() {
        return isSocialLogin;
    }

    public void setIsSocialLogin(Integer isSocialLogin) {
        this.isSocialLogin = isSocialLogin;
    }

    public Integer getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Integer loginTime) {
        this.loginTime = loginTime;
    }

    public String getPhoneModel() {
        return phoneModel;
    }

    public void setPhoneModel(String phoneModel) {
        this.phoneModel = phoneModel;
    }

    public Integer getOs() {
        return os;
    }

    public void setOs(Integer os) {
        this.os = os;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    @Override
    public String toString() {
        return "LoginInfo{" +
                "uuid=" + uuid +
                ", token='" + token + '\'' +
                ", isSocialLogin=" + isSocialLogin +
                ", loginTime=" + loginTime +
                ", phoneModel='" + phoneModel + '\'' +
                ", os=" + os +
                ", osVersion='" + osVersion + '\'' +
                '}';
    }
}
