package com.hrym.rpc.app.dao.model.view;

import java.io.Serializable;

/**
 * 功课查询关联查询视图
 * Created by mj on 2017/9/12.
 */
public class TaskItemView implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer itemId;
    private String itemName;
    private String itemIntro;
    private Integer orderNum;
    private String itemPic;
    private String titleDesc;
    private Integer catalogueId;
    private String musicName;
    private String musicFile;
    private String musicSubtitle;
    private String singer;
    private String albumName;
    private Integer musicId;
    private String musicPic;
    private Integer typeId;
    private String typeName;
    private String typeDesc;
    private Integer createTime;
    private Integer updateTime;


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

    public Integer getCatalogueId() {
        return catalogueId;
    }

    public void setCatalogueId(Integer catalogueId) {
        this.catalogueId = catalogueId;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getMusicFile() {
        return musicFile;
    }

    public void setMusicFile(String musicFile) {
        this.musicFile = musicFile;
    }

    public String getMusicSubtitle() {
        return musicSubtitle;
    }

    public void setMusicSubtitle(String musicSubtitle) {
        this.musicSubtitle = musicSubtitle;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public Integer getMusicId() {
        return musicId;
    }

    public void setMusicId(Integer musicId) {
        this.musicId = musicId;
    }

    public String getMusicPic() {
        return musicPic;
    }

    public void setMusicPic(String musicPic) {
        this.musicPic = musicPic;
    }

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
        return "TaskItemView{" +
                "itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", itemIntro='" + itemIntro + '\'' +
                ", orderNum=" + orderNum +
                ", itemPic='" + itemPic + '\'' +
                ", titleDesc='" + titleDesc + '\'' +
                ", catalogueId=" + catalogueId +
                ", musicName='" + musicName + '\'' +
                ", musicFile='" + musicFile + '\'' +
                ", musicSubtitle='" + musicSubtitle + '\'' +
                ", singer='" + singer + '\'' +
                ", albumName='" + albumName + '\'' +
                ", musicId=" + musicId +
                ", musicPic='" + musicPic + '\'' +
                ", typeId=" + typeId +
                ", typeName='" + typeName + '\'' +
                ", typeDesc='" + typeDesc + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
