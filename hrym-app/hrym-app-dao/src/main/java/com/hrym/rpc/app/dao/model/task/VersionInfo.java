package com.hrym.rpc.app.dao.model.task;

import java.io.Serializable;

/**
 * Created by mj on 2017/7/4.
 */
public class VersionInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer versionId;
    private int forceUpdate;
    private String iosDownloadUrl;
    private String androidDownloadUrl;
    private String iosVersionNum;
    private String androidVersionNum;
    private String content;
    private String iosContent;
    private Integer iosForceUpdate;

    public String getIosContent() {
        return iosContent;
    }

    public void setIosContent(String iosContent) {
        this.iosContent = iosContent;
    }

    public Integer getIosForceUpdate() {
        return iosForceUpdate;
    }

    public void setIosForceUpdate(Integer iosForceUpdate) {
        this.iosForceUpdate = iosForceUpdate;
    }

    public Integer getVersionId() {
        return versionId;
    }

    public void setVersionId(Integer versionId) {
        this.versionId = versionId;
    }

    public int getForceUpdate() {
        return forceUpdate;
    }

    public void setForceUpdate(int forceUpdate) {
        this.forceUpdate = forceUpdate;
    }

    public String getIosDownloadUrl() {
        return iosDownloadUrl;
    }

    public void setIosDownloadUrl(String iosDownloadUrl) {
        this.iosDownloadUrl = iosDownloadUrl;
    }

    public String getAndroidDownloadUrl() {
        return androidDownloadUrl;
    }

    public void setAndroidDownloadUrl(String androidDownloadUrl) {
        this.androidDownloadUrl = androidDownloadUrl;
    }

    public String getIosVersionNum() {
        return iosVersionNum;
    }

    public void setIosVersionNum(String iosVersionNum) {
        this.iosVersionNum = iosVersionNum;
    }

    public String getAndroidVersionNum() {
        return androidVersionNum;
    }

    public void setAndroidVersionNum(String androidVersionNum) {
        this.androidVersionNum = androidVersionNum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "VersionInfo{" +
                "versionId=" + versionId +
                ", forceUpdate=" + forceUpdate +
                ", iosDownloadUrl='" + iosDownloadUrl + '\'' +
                ", androidDownloadUrl='" + androidDownloadUrl + '\'' +
                ", iosVersionNum='" + iosVersionNum + '\'' +
                ", androidVersionNum='" + androidVersionNum + '\'' +
                ", content='" + content + '\'' +
                ", iosContent='" + iosContent + '\'' +
                ", iosForceUpdate=" + iosForceUpdate +
                '}';
    }
}
