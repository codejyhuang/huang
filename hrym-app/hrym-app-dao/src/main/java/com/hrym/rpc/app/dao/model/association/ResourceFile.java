package com.hrym.rpc.app.dao.model.association;

import java.io.Serializable;

/**
 * 资源文件实体类
 * Created by mj on 2017/9/14.
 */
public class ResourceFile implements Serializable {

    private Integer idtResource;        //资源id
    private String resourceName;        //资源名称
    private String resourceUrl;         //资源url
    private Integer resourceRole;       //0:共享资源\n1:社群资源\n2:私有资源
    private String resourceDesc;        //资源描述
    private String resourceDisplayName; //资源显示名称，包括后缀
    private Integer associationId;      //社群id
    private Integer visitTimes;         //访问次数
    private Integer downTimes;          //下载次数
    private Integer createTime;         //创建时间
    private Integer createUserId;       //创建者ID
    private String createUserName;      //创建者名称
    private Integer resourceType;       //0:音乐；1典籍

    public Integer getIdtResource() {
        return idtResource;
    }

    public void setIdtResource(Integer idtResource) {
        this.idtResource = idtResource;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public Integer getResourceRole() {
        return resourceRole;
    }

    public void setResourceRole(Integer resourceRole) {
        this.resourceRole = resourceRole;
    }

    public String getResourceDesc() {
        return resourceDesc;
    }

    public void setResourceDesc(String resourceDesc) {
        this.resourceDesc = resourceDesc;
    }

    public String getResourceDisplayName() {
        return resourceDisplayName;
    }

    public void setResourceDisplayName(String resourceDisplayName) {
        this.resourceDisplayName = resourceDisplayName;
    }

    public Integer getAssociationId() {
        return associationId;
    }

    public void setAssociationId(Integer associationId) {
        this.associationId = associationId;
    }

    public Integer getVisitTimes() {
        return visitTimes;
    }

    public void setVisitTimes(Integer visitTimes) {
        this.visitTimes = visitTimes;
    }

    public Integer getDownTimes() {
        return downTimes;
    }

    public void setDownTimes(Integer downTimes) {
        this.downTimes = downTimes;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Integer getResourceType() {
        return resourceType;
    }

    public void setResourceType(Integer resourceType) {
        this.resourceType = resourceType;
    }

    @Override
    public String toString() {
        return "ResourceFile{" +
                "idtResource=" + idtResource +
                ", resourceName='" + resourceName + '\'' +
                ", resourceUrl='" + resourceUrl + '\'' +
                ", resourceRole=" + resourceRole +
                ", resourceDesc='" + resourceDesc + '\'' +
                ", resourceDisplayName='" + resourceDisplayName + '\'' +
                ", associationId=" + associationId +
                ", visitTimes=" + visitTimes +
                ", downTimes=" + downTimes +
                ", createTime=" + createTime +
                ", createUserId=" + createUserId +
                ", createUserName='" + createUserName + '\'' +
                ", resourceType=" + resourceType +
                '}';
    }
}
