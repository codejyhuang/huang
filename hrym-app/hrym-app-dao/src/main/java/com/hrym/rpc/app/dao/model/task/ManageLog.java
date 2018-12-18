package com.hrym.rpc.app.dao.model.task;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by hrym13 on 2018/2/2.
 * 后台管理日志实体类
 */
@Table(name = "t_manage_log")
public class ManageLog implements Serializable {

    @Id
    private Integer logId;      //日志ID
    private Integer itemId;     //功课ID
    private Integer itemContentId;//功课内容ID
    private Integer musicId;    //音频ID
    private Integer userId;     //用户ID
    private String userName;    //用户名
    private Integer createTime; //创建时间
    private String itemName;    //功课名称
    private String versionName; //功课内容名称
    private Integer catalogueId; //功课ID
    private String catalogueName;   //类目名称
    private String musicName;       //音频名称
    private Integer contentId;

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getCatalogueName() {
        return catalogueName;
    }

    public void setCatalogueName(String catalogueName) {
        this.catalogueName = catalogueName;
    }

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
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

    public Integer getMusicId() {
        return musicId;
    }

    public void setMusicId(Integer musicId) {
        this.musicId = musicId;
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

    public Integer getCreateTime() {
        return createTime;
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

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Integer getCatalogueId() {
        return catalogueId;
    }

    public void setCatalogueId(Integer catalogueId) {
        this.catalogueId = catalogueId;
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    @Override
    public String toString() {
        return "ManageLog{" +
                "logId=" + logId +
                ", itemId=" + itemId +
                ", itemContentId=" + itemContentId +
                ", musicId=" + musicId +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", createTime=" + createTime +
                ", itemName='" + itemName + '\'' +
                ", versionName='" + versionName + '\'' +
                ", catalogueId=" + catalogueId +
                ", catalogueName='" + catalogueName + '\'' +
                ", musicName='" + musicName + '\'' +
                ", contentId=" + contentId +
                '}';
    }
}
