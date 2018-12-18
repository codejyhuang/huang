package com.hrym.rpc.app.dao.model.task;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

/**
 * 功课类型
 * Created by mj on 2017/7/6.
 */
@Table(name = "t_task_type")
public class TaskType implements Serializable {

    @Id
    private Integer typeId;
    private String typeName;
    private String typeDesc;
    private Integer sort;
    @Column(name = "introduceInfo")
    private String introduceInfo;
    private String img;
    @Transient
    private boolean hasNextPage;
    @Transient
    private List<TaskItem> taskItems;


    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    public Integer getOrder() {
        return sort;
    }

    public void setOrder(Integer order) {
        this.sort = order;
    }

    public String getIntroduceInfo() {
        return introduceInfo;
    }

    public void setIntroduceInfo(String introduceInfo) {
        this.introduceInfo = introduceInfo;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public List<TaskItem> getTaskItems() {
        return taskItems;
    }

    public void setTaskItems(List<TaskItem> taskItems) {
        this.taskItems = taskItems;
    }

    @Override
    public String toString() {
        return "TaskType{" +
                "typeId=" + typeId +
                ", typeName='" + typeName + '\'' +
                ", typeDesc='" + typeDesc + '\'' +
                ", sort=" + sort +
                ", introduceInfo='" + introduceInfo + '\'' +
                ", img='" + img + '\'' +
                ", hasNextPage=" + hasNextPage +
                ", taskItems=" + taskItems +
                '}';
    }
}
