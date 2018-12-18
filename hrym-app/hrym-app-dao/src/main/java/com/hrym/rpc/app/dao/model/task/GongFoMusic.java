package com.hrym.rpc.app.dao.model.task;

import java.io.Serializable;

/**
 * Created by mj on 2017/10/24.
 */
public class GongFoMusic implements Serializable {

    private Integer musicId;   //供佛用音频ID
    private String musicFile;   //供佛音频汉版
    private Integer version;   //供佛音频藏版
    private String musicVersion;

    public Integer getMusicId() {
        return musicId;
    }

    public void setMusicId(Integer musicId) {
        this.musicId = musicId;
    }

    public String getMusicFile() {
        return musicFile;
    }

    public void setMusicFile(String musicFile) {
        this.musicFile = musicFile;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getMusicVersion() {
        return musicVersion;
    }

    public void setMusicVersion(String musicVersion) {
        this.musicVersion = musicVersion;
    }

    @Override
    public String toString() {
        return "GongFoMusic{" +
                "musicId=" + musicId +
                ", musicFile='" + musicFile + '\'' +
                ", version=" + version +
                ", musicVersion='" + musicVersion + '\'' +
                '}';
    }
}
