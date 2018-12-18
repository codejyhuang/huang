package com.hrym.rpc.app.dao.model.task.lesson;

import java.io.Serializable;

/**
 * Created by hrym13 on 2018/6/25.
 * 推荐功课实体类
 */
public class TaskAreaLesson implements Serializable {

    private Integer areaId;         // 索引ID
    private String titleName;       // 功课标题
    private Integer itemId;         // 功课ID
    private Integer version;        // 版本内容ID
    private Integer taskTarget;     // 功课目标
    private Integer taskPeriod;     // 功课期限
    private Integer areaType;       // 专区类型（1：功课推荐；2：常用功课专区；3：热门功课））
    private  String articleUrl;     // 文章跳转URL
    private Integer typeId;         // 功课类型ID
    private Integer createTime;     // 创建时间
    private Integer updateTime;     // 更新时间
    private String titleDesc;       // 功课描述


    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getTitleDesc() {
        return titleDesc;
    }

    public void setTitleDesc(String titleDesc) {
        this.titleDesc = titleDesc;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getTaskTarget() {
        return taskTarget;
    }

    public void setTaskTarget(Integer taskTarget) {
        this.taskTarget = taskTarget;
    }

    public Integer getTaskPeriod() {
        return taskPeriod;
    }

    public void setTaskPeriod(Integer taskPeriod) {
        this.taskPeriod = taskPeriod;
    }

    public Integer getAreaType() {
        return areaType;
    }

    public void setAreaType(Integer areaType) {
        this.areaType = areaType;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "TaskAreaLesson{" +
                "areaId=" + areaId +
                ", titleName=" + titleName +
                ", itemId=" + itemId +
                ", version=" + version +
                ", taskTarget=" + taskTarget +
                ", taskPeriod=" + taskPeriod +
                ", areaType=" + areaType +
                ", articleUrl='" + articleUrl + '\'' +
                ", typeId=" + typeId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
