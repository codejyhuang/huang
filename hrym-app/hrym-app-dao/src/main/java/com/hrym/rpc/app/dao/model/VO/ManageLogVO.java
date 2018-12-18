package com.hrym.rpc.app.dao.model.VO;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * 日志实体类
 * Created by hrym13 on 2018/2/2.
 */
@Table(name = "t_manage_log")
public class ManageLogVO implements Serializable {
    @Id
    private Integer logId;      //日志ID
    private Integer itemId;     //功课ID
    private Integer itemContentId;//功课内容ID
    private Integer userId;     //用户ID
    private Integer createTime; //创建时间
    private String itemName;    //功课名称
    private String versionName; //功课内容名称
    private Integer catalogueId; //功课ID
    private String catalogueName;   //类目名称
    private Integer musicId;        //音频ID
    private String musicName;       //音频名称
    @Transient
    private String username;    //用户名
    @Transient
    private String createTimeis;

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getItemId() {
        return itemId;

    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getItemContentId() {
        return itemContentId;
    }

    public void setItemContentId(Integer itemContentId) {
        this.itemContentId = itemContentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public Integer getCatalogueId() {
        return catalogueId;
    }

    public void setCatalogueId(Integer catalogueId) {
        this.catalogueId = catalogueId;
    }

    public String getCatalogueName() {
        return catalogueName;
    }

    public void setCatalogueName(String catalogueName) {
        this.catalogueName = catalogueName;
    }

    public String getCreateTimeis() {
        return createTimeis;
    }

    public void setCreateTimeis(String createTimeis) {
        this.createTimeis = createTimeis;
    }

    public Integer getMusicId() {
        return musicId;
    }

    public void setMusicId(Integer musicId) {
        this.musicId = musicId;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    @Override
    public String toString() {
        return "ManageLogVO{" +
                "logId=" + logId +
                ", itemId=" + itemId +
                ", itemContentId=" + itemContentId +
                ", userId=" + userId +
                ", createTime=" + createTime +
                ", itemName='" + itemName + '\'' +
                ", versionName='" + versionName + '\'' +
                ", catalogueId=" + catalogueId +
                ", catalogueName='" + catalogueName + '\'' +
                ", musicId=" + musicId +
                ", musicName='" + musicName + '\'' +
                ", username='" + username + '\'' +
                ", createTimeis='" + createTimeis + '\'' +
                '}';
    }
}
