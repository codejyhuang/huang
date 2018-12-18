package com.hrym.rpc.app.dao.model.association;

import java.io.Serializable;

/**
 * 社群实体类
 * Created by mj on 2017/8/17.
 */
public class Association implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idtAssociation;    //社群表id
    private String name;               //社群名称
    private Integer type;              //0:系统学习佛学 1:交流群
    private Integer createTime;        //创建时间
    private String intro;              //社群介绍
    private String rqCodeUrl;          //二维码url
    private String avatarUrl;          //头像url
    private Integer userId;            //创建者id
    private String userName;           //创建者
    private Integer updateTime;        //更新时间
    private Integer num;               //社群人数

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getIdtAssociation() {
        return idtAssociation;
    }

    public void setIdtAssociation(Integer idtAssociation) {
        this.idtAssociation = idtAssociation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getRqCodeUrl() {
        return rqCodeUrl;
    }

    public void setRqCodeUrl(String rqCodeUrl) {
        this.rqCodeUrl = rqCodeUrl;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Association{" +
                "idtAssociation=" + idtAssociation +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", createTime=" + createTime +
                ", intro='" + intro + '\'' +
                ", rqCodeUrl='" + rqCodeUrl + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", updateTime=" + updateTime +
                ", num=" + num +
                '}';
    }
}
