package com.hrym.rpc.app.dao.model.task;

import com.hrym.rpc.app.dao.model.VO.TaskMusicVO;

import java.io.Serializable;
import java.util.List;

/**
 * 功课内容类型
 * Created by mj on 2017/7/6.
 */
public class TaskContent implements Serializable{
    private static final long serialVersionUID = 1L;

    private Integer itemId;
    private Integer itemContentId;
    private String versionName;
    private Integer years;
    private String source;
    private  String origin;
    private String author;
    private String translator;
    private String fileTxt;
    private String text;
    private String picVersionName;
    private String filePic;
    private Integer order;
    private TaskType taskType;
    private TaskItem taskItem;
    private VersionInfo versionInfo;
    private ResourceCatalogue resourceCatalogue;
    private String yearsStr;
    private String videoFile;
    private String contentVersion;
    private Integer version;
    private Integer createTime;
    private Integer voiceCount;
    private String voiceName;
    private String voiceDic;
    private String voiceLm;
    private String voiceText;
    //内容相关的音频
    private List<TaskMusic> musicList;
    private List<TaskMusicVO> musicVos;
    private Integer typeId;

    public List<TaskMusicVO> getMusicVos() {
        return musicVos;
    }

    public void setMusicVos(List<TaskMusicVO> musicVos) {
        this.musicVos = musicVos;
    }

    public List<TaskMusic> getMusicList() {
        return musicList;
    }

    public void setMusicList(List<TaskMusic> musicList) {
        this.musicList = musicList;
    }

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

    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTranslator() {
        return translator;
    }

    public void setTranslator(String translator) {
        this.translator = translator;
    }

    public String getFileTxt() {
        return fileTxt;
    }

    public void setFileTxt(String fileTxt) {
        this.fileTxt = fileTxt;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPicVersionName() {
        return picVersionName;
    }

    public void setPicVersionName(String picVersionName) {
        this.picVersionName = picVersionName;
    }

    public String getFilePic() {
        return filePic;
    }

    public void setFilePic(String filePic) {
        this.filePic = filePic;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public TaskItem getTaskItem() {
        return taskItem;
    }

    public void setTaskItem(TaskItem taskItem) {
        this.taskItem = taskItem;
    }

    public VersionInfo getVersionInfo() {return versionInfo;}

    public void setVersionInfo(VersionInfo versionInfo) {this.versionInfo = versionInfo;}

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public ResourceCatalogue getResourceCatalogue() {return resourceCatalogue;}

    public void setResourceCatalogue(ResourceCatalogue resourceCatalogue) {this.resourceCatalogue = resourceCatalogue;}

    public String getYearsStr() {
        return yearsStr;
    }

    public void setYearsStr(String yearsStr) {
        this.yearsStr = yearsStr;
    }

    public String getVideoFile() {
        return videoFile;
    }

    public void setVideoFile(String videoFile) {
        this.videoFile = videoFile;
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

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getVoiceText() {
        return voiceText;
    }

    public void setVoiceText(String voiceText) {
        this.voiceText = voiceText;
    }
}
