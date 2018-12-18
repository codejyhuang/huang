package com.hrym.rpc.app.dao.model.system;

import javax.persistence.Table;
import javax.xml.crypto.Data;
import java.io.Serializable;

/**
 * Created by hrym13 on 2018/3/9.
 */
public class DiurnalData implements Serializable {

    private String tableName;
    private Integer Id;
    private Integer uuid;
    private Integer createdTime;
    private Integer recentTime;
    private String realName;
    private String nickName;
    private String recentTimes;
    private String createdTimes;
    private Integer count;
    private Integer recount;
    private UserData userData;


    public Integer getRecount() {
        return recount;
    }

    public void setRecount(Integer recount) {
        this.recount = recount;
    }

    public String getRecentTimes() {
        return recentTimes;
    }

    public void setRecentTimes(String recentTimes) {
        this.recentTimes = recentTimes;
    }

    public String getCreatedTimes() {
        return createdTimes;
    }

    public void setCreatedTimes(String createdTimes) {
        this.createdTimes = createdTimes;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public Integer getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Integer createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getRecentTime() {
        return recentTime;
    }

    public void setRecentTime(Integer recentTime) {
        this.recentTime = recentTime;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "DiurnalData{" +
                "tableName='" + tableName + '\'' +
                ", Id=" + Id +
                ", uuid=" + uuid +
                ", createdTime=" + createdTime +
                ", recentTime=" + recentTime +
                ", realName='" + realName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", recentTimes='" + recentTimes + '\'' +
                ", createdTimes='" + createdTimes + '\'' +
                ", count=" + count +
                ", recount=" + recount +
                ", userData=" + userData +
                '}';
    }
}
