package com.hrym.rpc.app.dao.model.association;

import com.hrym.rpc.app.dao.model.task.UserInfo;

import java.io.Serializable;

/**
 * 社群成员列表
 * Created by mj on 2017/8/17.
 */
public class AssociationMember implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idtAssociation;             //社群id
    private Integer userType;                   //账号类型0:群管理员1:特约嘉宾2:一般人员
    private Integer receiveTopicRole;           //接收主题动态0:所有动态1:@你的动态
    private Integer userId;                     //用户id
    private String intro;                     //自我简介

    private UserInfo userInfo;                  //  关联用户表


    public Integer getUserType() {
        return userType;
    }

    public Integer getIdtAssociation() {
        return idtAssociation;
    }

    public void setIdtAssociation(Integer idtAssociation) {
        this.idtAssociation = idtAssociation;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getReceiveTopicRole() {
        return receiveTopicRole;
    }

    public void setReceiveTopicRole(Integer receiveTopicRole) {
        this.receiveTopicRole = receiveTopicRole;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public String toString() {
        return "AssociationMember{" +
                "idtAssociation=" + idtAssociation +
                ", userType=" + userType +
                ", receiveTopicRole=" + receiveTopicRole +
                ", userId=" + userId +
                ", intro='" + intro + '\'' +
                ", userInfo=" + userInfo +
                '}';
    }
}
