package com.hrym.wechat.smallProgram;

import com.hrym.common.base.BaseRequestParam;

import java.io.Serializable;

/**
 * Created by hrym13 on 2018/4/16.
 */
public class WechatUserParam extends BaseRequestParam implements Serializable {

    private wechatUserData data;

    public wechatUserData getData() {
        return data;
    }

    public void setData(wechatUserData data) {
        this.data = data;
    }

    public Integer getUserId() {
        return data.getUserId();
    }

    public String getOpenId() {
        return data.getOpenId();
    }

    public String getNickName() {
        return data.getNickName();
    }

    public Integer getCreateTime() {
        return data.getCreateTime();
    }

    public String getProvince() {
        return data.getProvince();
    }

    public String getAvatarUrl() {
        return data.getAvatarUrl();
    }
    
    public Integer getSex() {
        return data.getSex();
    }
    
    public String getUnionId() {
        return data.getUnionId();
    }
    
    public Integer getUuid() {
        return data.getUuid();
    }
    
    
}

class wechatUserData implements Serializable{

    private Integer userId;
    private String openId;        //openId
    private String nickName;
    private Integer createTime;
    private String province;        //地区
    private String avatarUrl;
    private Integer sex;
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
    
    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getOpenId() {
        return openId;
    }

    public String getNickName() {
        return nickName;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public String getProvince() {
        return province;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}

