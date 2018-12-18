package com.hrym.rpc.app.dao.model.task;

import java.io.Serializable;
import java.util.List;

/**
 * 功课实体类
 * Created by mj on 2017/7/8.
 */
public class TaskBookItem implements Serializable{
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


    //关联表和内容
    private String typeName;
    private String introduceInfo;
    private Integer typeId;
    private String catalogueName;
    private List<TaskBookItem> itemList;
    private TaskPlan taskPlan;
    private ResourceCatalogue resourceCatalogue;


    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getIntroduceInfo() {
        return introduceInfo;
    }

    public void setIntroduceInfo(String introduceInfo) {
        this.introduceInfo = introduceInfo;
    }

    public String getCatalogueName() {
        return catalogueName;
    }

    public void setCatalogueName(String catalogueName) {
        this.catalogueName = catalogueName;
    }

    public ResourceCatalogue getResourceCatalogue() {
        return resourceCatalogue;
    }

    public void setResourceCatalogue(ResourceCatalogue resourceCatalogue) {
        this.resourceCatalogue = resourceCatalogue;
    }

    public TaskPlan getTaskPlan() {
        return taskPlan;
    }

    public void setTaskPlan(TaskPlan taskPlan) {
        this.taskPlan = taskPlan;
    }

    public List<TaskBookItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<TaskBookItem> itemList) {
        this.itemList = itemList;
    }

    public TaskMusic getTaskMusic() {return taskMusic;}

    public void setTaskMusic(TaskMusic taskMusic) {this.taskMusic = taskMusic;}

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getCatalogueId() {return catalogueId;}

    public void setCatalogueId(Integer catalogueId) {this.catalogueId = catalogueId;}

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

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
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


    @Override
    public String toString() {
        return "TaskItem{" +
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
                ", typeName='" + typeName + '\'' +
                ", introduceInfo='" + introduceInfo + '\'' +
                ", typeId=" + typeId +
                ", catalogueName='" + catalogueName + '\'' +
                ", itemList=" + itemList +
                ", taskPlan=" + taskPlan +
                ", resourceCatalogue=" + resourceCatalogue +
                '}';
    }
}

