package com.hrym.rpc.app.dao.model.task;

import java.io.Serializable;

/**
 * Created by mj on 2017/8/21.
 */
public class ResourceCatalogue implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer catalogueId;
    private String catalogueName;
    private Integer level;
    private Integer parentLevel;
    private String catalogueDesc;
    private Integer order;
    private String introduceInfo;
    private String img;
    private Integer createTime;
    private String creator;
    private Integer parentTypeId;

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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getParentLevel() {
        return parentLevel;
    }

    public void setParentLevel(Integer parentLevel) {
        this.parentLevel = parentLevel;
    }

    public String getCatalogueDesc() {
        return catalogueDesc;
    }

    public void setCatalogueDesc(String catalogueDesc) {
        this.catalogueDesc = catalogueDesc;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
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

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Integer getParentTypeId() {
        return parentTypeId;
    }

    public void setParentTypeId(Integer parentTypeId) {
        this.parentTypeId = parentTypeId;
    }

    @Override
    public String toString() {
        return "ResourceCatalogueMapper{" +
                "catalogueId=" + catalogueId +
                ", catalogueName='" + catalogueName + '\'' +
                ", level=" + level +
                ", parentLevel=" + parentLevel +
                ", catalogueDesc='" + catalogueDesc + '\'' +
                ", order=" + order +
                ", introduceInfo='" + introduceInfo + '\'' +
                ", img='" + img + '\'' +
                ", createTime=" + createTime +
                ", creator='" + creator + '\'' +
                ", parentTypeId=" + parentTypeId +
                '}';
    }
}
