package com.hrym.rpc.app.dao.model.task.lesson;

import java.io.Serializable;

/**
 * Created by hrym13 on 2018/6/20.
 */
public class ResourceContentLesson implements Serializable {

    private Integer itemId;             // 功课ID
    private Integer itemContentId;      // 功课内容ID
    private String versionName;         // 版本名称
    private String contentPic;          // 图片文件
    private String text;                // 纯文字
    private String videoFile;           // 视频文件
    private String origin;              // 来源
    private String contentVersion;      // 视频版本(供佛)
    private Integer version;            // 0：藏版；1：汉版
    private Integer createTime;         // 录入时间
    private Integer voiceCount;         // '语音识别；1：有2：无'
    private String voiceName;           // 语音识别名称
    private String voiceDic;            // 语音识别字典文件
    private String voiceLm;             //
    private Integer updateTime;         // 更新时间
    private Integer orderNum;           //定制人数
    private Integer onlineNum;          //正在做人数

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

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getContentPic() {
        return contentPic;
    }

    public void setContentPic(String contentPic) {
        this.contentPic = contentPic;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getVideoFile() {
        return videoFile;
    }

    public void setVideoFile(String videoFile) {
        this.videoFile = videoFile;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getContentVersion() {
        return contentVersion;
    }

    public void setContentVersion(String contentVersion) {
        this.contentVersion = contentVersion;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Integer getVoiceCount() {
        return voiceCount;
    }

    public void setVoiceCount(Integer voiceCount) {
        this.voiceCount = voiceCount;
    }

    public String getVoiceName() {
        return voiceName;
    }

    public void setVoiceName(String voiceName) {
        this.voiceName = voiceName;
    }

    public String getVoiceDic() {
        return voiceDic;
    }

    public void setVoiceDic(String voiceDic) {
        this.voiceDic = voiceDic;
    }

    public String getVoiceLm() {
        return voiceLm;
    }

    public void setVoiceLm(String voiceLm) {
        this.voiceLm = voiceLm;
    }

    public Integer getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getOnlineNum() {
        return onlineNum;
    }

    public void setOnlineNum(Integer onlineNum) {
        this.onlineNum = onlineNum;
    }

    @Override
    public String toString() {
        return "ResourceContentLesson{" +
                "itemId=" + itemId +
                ", itemContentId=" + itemContentId +
                ", versionName='" + versionName + '\'' +
                ", contentPic='" + contentPic + '\'' +
                ", text='" + text + '\'' +
                ", videoFile='" + videoFile + '\'' +
                ", origin='" + origin + '\'' +
                ", contentVersion='" + contentVersion + '\'' +
                ", version=" + version +
                ", createTime=" + createTime +
                ", voiceCount=" + voiceCount +
                ", voiceName='" + voiceName + '\'' +
                ", voiceDic='" + voiceDic + '\'' +
                ", voiceLm='" + voiceLm + '\'' +
                ", updateTime=" + updateTime +
                ", orderNum=" + orderNum +
                ", onlineNum=" + onlineNum +
                '}';
    }
}
