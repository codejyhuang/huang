package com.hrym.rpc.app.dao.model.task.meditation;

import com.hrym.rpc.app.dao.model.task.ResourceCatalogue;
import com.hrym.rpc.app.dao.model.task.TaskMusic;
import com.hrym.rpc.app.dao.model.task.TaskPlan;
import com.hrym.rpc.app.dao.model.task.TaskType;

import java.io.Serializable;
import java.util.List;

/**
 * 禅修功课实体类
 * Created by mj on 2017/7/8.
 */
public class MeditationItem implements Serializable{
    private static final long serialVersionUID = 1L;

    private Integer itemId;
    private String itemName;
    private String itemIntro;
    private Integer orderNum;
    private String itemPic;
    private String titleDesc;
    private String aliasName;
    private Integer catalogueId;
    private TaskMusic taskMusic;
    private TaskType type;
    private Integer updateTime;
    private Integer createTime;
    private Integer isSupport;
    private Integer index = 0;
    private Integer isMusicOrVideo;
    private String backgroundPic;


    private List<MeditationContent> contentList;
    private Integer sort=0;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemIntro() {
        return itemIntro;
    }

    public void setItemIntro(String itemIntro) {
        this.itemIntro = itemIntro;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getItemPic() {
        return itemPic;
    }

    public void setItemPic(String itemPic) {
        this.itemPic = itemPic;
    }

    public String getTitleDesc() {
        return titleDesc;
    }

    public void setTitleDesc(String titleDesc) {
        this.titleDesc = titleDesc;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public Integer getCatalogueId() {
        return catalogueId;
    }

    public void setCatalogueId(Integer catalogueId) {
        this.catalogueId = catalogueId;
    }

    public TaskMusic getTaskMusic() {
        return taskMusic;
    }

    public void setTaskMusic(TaskMusic taskMusic) {
        this.taskMusic = taskMusic;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public Integer getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Integer getIsSupport() {
        return isSupport;
    }

    public void setIsSupport(Integer isSupport) {
        this.isSupport = isSupport;
    }

    public List<MeditationContent> getContentList() {
        return contentList;
    }

    public void setContentList(List<MeditationContent> contentList) {
        this.contentList = contentList;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getIsMusicOrVideo() {
        return isMusicOrVideo;
    }

    public void setIsMusicOrVideo(Integer isMusicOrVideo) {
        this.isMusicOrVideo = isMusicOrVideo;
    }

    public String getBackgroundPic() {
        return backgroundPic;
    }

    public void setBackgroundPic(String backgroundPic) {
        this.backgroundPic = backgroundPic;
    }

    @Override
    public String toString() {
        return "MeditationItem{" +
                "itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", itemIntro='" + itemIntro + '\'' +
                ", orderNum=" + orderNum +
                ", itemPic='" + itemPic + '\'' +
                ", titleDesc='" + titleDesc + '\'' +
                ", aliasName='" + aliasName + '\'' +
                ", catalogueId=" + catalogueId +
                ", taskMusic=" + taskMusic +
                ", type=" + type +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                ", isSupport=" + isSupport +
                ", index=" + index +
                ", isMusicOrVideo=" + isMusicOrVideo +
                ", backgroundPic='" + backgroundPic + '\'' +
                ", contentList=" + contentList +
                ", sort=" + sort +
                '}';
    }
}

