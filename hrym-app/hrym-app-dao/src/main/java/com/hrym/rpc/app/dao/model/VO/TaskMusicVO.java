package com.hrym.rpc.app.dao.model.VO;

import com.hrym.common.util.DateUtil;
import com.hrym.rpc.app.dao.model.task.*;

import java.io.Serializable;

import static com.hrym.common.util.DateUtil.TIME_PATTON_DEFAULT;

/**
 * Created by hrym13 on 2017/9/20.
 */
public class TaskMusicVO implements Serializable {

    private Integer musicId;
    private Integer itemContentId;
    private String musicName;
    private String musicFile;
    private String musicSubtitle;
    private Integer endTime;
    private Integer startTime;
    private Integer startNum;
    private Integer step;
    private Integer settingTime;
    private String singer;
    private String albumName;
    private String diskNumber;
    private String composer;
    private Integer pushTime;
    private String orbitalNumber;
    private String style;
    private String versionId;
    private Integer itemId;
    private String musicPic;          //'音频图片',
    private Integer needSetTime;
    private Integer shouEndTime;
    private Integer roundTime;
    private Integer downTime;
    private Integer voiceDown;
    private Integer voiceStart;

    private TaskSubPlan taskSubPlan;
    private TaskItem taskItem;
    private TaskSubResource taskSubResource;
    private TaskType taskType;
    private TaskContent taskContent;
    private String musicFileAndroid;//安卓音频文件
    private Integer musicVersion;
    private String itemName;
    private String versionName;

    //子音频
    private Integer type;   //  子音频类型
    private String value;   //  子音频URL
    private Integer idtSubResource;   //子音频ID
    private String des;     //子音频描述
    private Integer resourceId; //  父音频ID


    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public Integer getMusicVersion() {
        return musicVersion;
    }

    public void setMusicVersion(Integer musicVersion) {
        this.musicVersion = musicVersion;
    }

    public String getMusicFileAndroid() {
        return musicFileAndroid;
    }

    public void setMusicFileAndroid(String musicFileAndroid) {
        this.musicFileAndroid = musicFileAndroid;
    }

    public Integer getMusicId() {
        return musicId;
    }

    public void setMusicId(Integer musicId) {
        this.musicId = musicId;
    }

    public Integer getItemContentId() {
        return itemContentId;
    }

    public void setItemContentId(Integer itemContentId) {
        this.itemContentId = itemContentId;
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

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public Integer getStartNum() {
        return startNum;
    }

    public void setStartNum(Integer startNum) {
        this.startNum = startNum;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public Integer getSettingTime() {
        return settingTime;
    }

    public void setSettingTime(Integer settingTime) {
        this.settingTime = settingTime;
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

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public Integer getPushTime() {
        return pushTime;
    }
    public String getPushTimeDis() {
        //String pushTimess = DateUtil.timestampToDates(pushTime,TIME_PATTON_DEFAULT);
        return DateUtil.timestampToDates(pushTime,TIME_PATTON_DEFAULT);
    }

    public void setPushTime(Integer pushTime) {
        this.pushTime = pushTime;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getMusicPic() {
        return musicPic;
    }

    public void setMusicPic(String musicPic) {
        this.musicPic = musicPic;
    }

    public Integer getNeedSetTime() {
        return needSetTime;
    }

    public void setNeedSetTime(Integer needSetTime) {
        this.needSetTime = needSetTime;
    }

    public Integer getShouEndTime() {
        return shouEndTime;
    }

    public void setShouEndTime(Integer shouEndTime) {
        this.shouEndTime = shouEndTime;
    }

    public Integer getRoundTime() {
        return roundTime;
    }

    public void setRoundTime(Integer roundTime) {
        this.roundTime = roundTime;
    }

    public Integer getDownTime() {
        return downTime;
    }

    public void setDownTime(Integer downTime) {
        this.downTime = downTime;
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

    public TaskSubPlan getTaskSubPlan() {
        return taskSubPlan;
    }

    public void setTaskSubPlan(TaskSubPlan taskSubPlan) {
        this.taskSubPlan = taskSubPlan;
    }

    public TaskItem getTaskItem() {
        return taskItem;
    }

    public void setTaskItem(TaskItem taskItem) {
        this.taskItem = taskItem;
    }

    public TaskSubResource getTaskSubResource() {
        return taskSubResource;
    }

    public void setTaskSubResource(TaskSubResource taskSubResource) {
        this.taskSubResource = taskSubResource;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public TaskContent getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(TaskContent taskContent) {
        this.taskContent = taskContent;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getIdtSubResource() {
        return idtSubResource;
    }

    public void setIdtSubResource(Integer idtSubResource) {
        this.idtSubResource = idtSubResource;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public String getDiskNumber() {
        return diskNumber;
    }

    public void setDiskNumber(String diskNumber) {
        this.diskNumber = diskNumber;
    }

    public String getOrbitalNumber() {
        return orbitalNumber;
    }

    public void setOrbitalNumber(String orbitalNumber) {
        this.orbitalNumber = orbitalNumber;
    }

    @Override
    public String toString() {
        return "TaskMusicVO{" +
                "musicId=" + musicId +
                ", itemContentId=" + itemContentId +
                ", musicName='" + musicName + '\'' +
                ", musicFile='" + musicFile + '\'' +
                ", musicSubtitle='" + musicSubtitle + '\'' +
                ", endTime=" + endTime +
                ", startTime=" + startTime +
                ", startNum=" + startNum +
                ", step=" + step +
                ", settingTime=" + settingTime +
                ", singer='" + singer + '\'' +
                ", albumName='" + albumName + '\'' +
                ", diskNumber='" + diskNumber + '\'' +
                ", composer='" + composer + '\'' +
                ", pushTime=" + pushTime +
                ", orbitalNumber='" + orbitalNumber + '\'' +
                ", style='" + style + '\'' +
                ", versionId='" + versionId + '\'' +
                ", itemId=" + itemId +
                ", musicPic='" + musicPic + '\'' +
                ", needSetTime=" + needSetTime +
                ", shouEndTime=" + shouEndTime +
                ", roundTime=" + roundTime +
                ", downTime=" + downTime +
                ", voiceDown=" + voiceDown +
                ", voiceStart=" + voiceStart +
                ", taskSubPlan=" + taskSubPlan +
                ", taskItem=" + taskItem +
                ", taskSubResource=" + taskSubResource +
                ", taskType=" + taskType +
                ", taskContent=" + taskContent +
                ", musicFileAndroid='" + musicFileAndroid + '\'' +
                ", musicVersion=" + musicVersion +
                ", itemName='" + itemName + '\'' +
                ", versionName='" + versionName + '\'' +
                ", type=" + type +
                ", value='" + value + '\'' +
                ", idtSubResource=" + idtSubResource +
                ", des='" + des + '\'' +
                ", resourceId=" + resourceId +
                '}';
    }
}
