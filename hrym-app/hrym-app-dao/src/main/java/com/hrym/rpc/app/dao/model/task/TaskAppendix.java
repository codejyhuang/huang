package com.hrym.rpc.app.dao.model.task;

import java.io.Serializable;

/**
 * 功课附件实体类
 * Created by mj on 2017/7/7.
 */
public class TaskAppendix implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer itemId;
    private Integer appendixId;
    private Integer appendixType;
    private String appendixName;
    private String appendixDesc;
    private String appendixUrl;
    private Integer subAppendixId;
    private String subAppendixUrl;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getAppendixId() {
        return appendixId;
    }

    public void setAppendixId(Integer appendixId) {
        this.appendixId = appendixId;
    }

    public Integer getAppendixType() {
        return appendixType;
    }

    public void setAppendixType(Integer appendixType) {
        this.appendixType = appendixType;
    }

    public String getAppendixName() {
        return appendixName;
    }

    public void setAppendixName(String appendixName) {
        this.appendixName = appendixName;
    }

    public String getAppendixDesc() {
        return appendixDesc;
    }

    public void setAppendixDesc(String appendixDesc) {
        this.appendixDesc = appendixDesc;
    }

    public String getAppendixUrl() {
        return appendixUrl;
    }

    public void setAppendixUrl(String appendixUrl) {
        this.appendixUrl = appendixUrl;
    }

    public Integer getSubAppendixId() {
        return subAppendixId;
    }

    public void setSubAppendixId(Integer subAppendixId) {
        this.subAppendixId = subAppendixId;
    }

    public String getSubAppendixUrl() {
        return subAppendixUrl;
    }

    public void setSubAppendixUrl(String subAppendixUrl) {
        this.subAppendixUrl = subAppendixUrl;
    }


    @Override
    public String toString() {
        return "TaskAppendix{" +
                "itemId=" + itemId +
                ", appendixId=" + appendixId +
                ", appendixType=" + appendixType +
                ", appendixName='" + appendixName + '\'' +
                ", appendixDesc='" + appendixDesc + '\'' +
                ", appendixUrl='" + appendixUrl + '\'' +
                ", subAppendixId=" + subAppendixId +
                ", subAppendixUrl='" + subAppendixUrl + '\'' +
                '}';
    }
}
