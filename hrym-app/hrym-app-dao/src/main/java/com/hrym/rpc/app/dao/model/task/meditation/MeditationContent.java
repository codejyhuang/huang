package com.hrym.rpc.app.dao.model.task.meditation;

import java.io.Serializable;

/**
 * 禅修功课内容类型
 * Created by mj on 2017/7/6.
 */
public class MeditationContent implements Serializable{

    private static final long serialVersionUID = 1L;

    private Integer contentId;
    private Integer itemId;
    private String contentName;
    private String musicFile;
    private String musicSubtitle;
    private String videoFile;
    private Integer settingTime;
    private Integer fixedMusicTime;
    private Integer fixedVideoTime;
    private String versionId;
    private Integer voiceDown;
    private Integer voiceStart;
    private String endMusicFile;
    private Integer isMusicEdit;
    private Integer createTime;
    private Integer updateTime;


    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
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

    public String getVideoFile() {
        return videoFile;
    }

    public void setVideoFile(String videoFile) {
        this.videoFile = videoFile;
    }

    public Integer getSettingTime() {
        return settingTime;
    }

    public void setSettingTime(Integer settingTime) {
        this.settingTime = settingTime;
    }

    public Integer getFixedMusicTime() {
        return fixedMusicTime;
    }

    public void setFixedMusicTime(Integer fixedMusicTime) {
        this.fixedMusicTime = fixedMusicTime;
    }

    public Integer getFixedVideoTime() {
        return fixedVideoTime;
    }

    public void setFixedVideoTime(Integer fixedVideoTime) {
        this.fixedVideoTime = fixedVideoTime;
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public Integer getVoiceDown() {
        return voiceDown;
    }

    public void setVoiceDown(Integer voiceDown) {
        this.voiceDown = voiceDown;
    }

    public Integer getVoiceStart() {
        return voiceStart;
    }

    public void setVoiceStart(Integer voiceStart) {
        this.voiceStart = voiceStart;
    }

    public String getEndMusicFile() {
        return endMusicFile;
    }

    public void setEndMusicFile(String endMusicFile) {
        this.endMusicFile = endMusicFile;
    }

    public Integer getIsMusicEdit() {
        return isMusicEdit;
    }

    public void setIsMusicEdit(Integer isMusicEdit) {
        this.isMusicEdit = isMusicEdit;
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
        return "MeditationContent{" +
                "contentId=" + contentId +
                ", itemId=" + itemId +
                ", contentName='" + contentName + '\'' +
                ", musicFile='" + musicFile + '\'' +
                ", musicSubtitle='" + musicSubtitle + '\'' +
                ", videoFile='" + videoFile + '\'' +
                ", settingTime=" + settingTime +
                ", fixedMusicTime=" + fixedMusicTime +
                ", fixedVideoTime=" + fixedVideoTime +
                ", versionId='" + versionId + '\'' +
                ", voiceDown=" + voiceDown +
                ", voiceStart=" + voiceStart +
                ", endMusicFile='" + endMusicFile + '\'' +
                ", isMusicEdit=" + isMusicEdit +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
