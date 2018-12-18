package com.hrym.rpc.app.dao.model.task.lesson;

/**
 * Created by hrym13 on 2018/6/20.
 * 功课实体类
 */
public class ResourceItemLesson {

    private Integer itemId;         // 索引ID
    private String itemName;        // 功课名称
    private String itemIntro;       // 功课简介
    private Integer orderNum;       // 定制数量
    private String aliasName;       // 别名
    private String itemPic;         // 图片
    private String titleDesc;       // 功课描述
    private Integer createTime;     // 创建时间
    private Integer updateTime;     // 更新时间
    private Integer isSupport;      // 是否可供 供佛用（0：不可供；1：可供）
    private String tags;            // 功课标签，以，分割
    private Integer onlineNum;      // 正在做的人数
    private Integer tagsNum;        // 标签数

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

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
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

    public Integer getIsSupport() {
        return isSupport;
    }

    public void setIsSupport(Integer isSupport) {
        this.isSupport = isSupport;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Integer getOnlineNum() {
        return onlineNum;
    }

    public void setOnlineNum(Integer onlineNum) {
        this.onlineNum = onlineNum;
    }

    public Integer getTagsNum() {
        return tagsNum;
    }

    public void setTagsNum(Integer tagsNum) {
        this.tagsNum = tagsNum;
    }

    @Override
    public String toString() {
        return "ResourceItemLesson{" +
                "itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", itemIntro='" + itemIntro + '\'' +
                ", orderNum=" + orderNum +
                ", aliasName='" + aliasName + '\'' +
                ", itemPic='" + itemPic + '\'' +
                ", titleDesc='" + titleDesc + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isSupport=" + isSupport +
                ", tags='" + tags + '\'' +
                ", onlineNum=" + onlineNum +
                ", tagsNum=" + tagsNum +
                '}';
    }
}
