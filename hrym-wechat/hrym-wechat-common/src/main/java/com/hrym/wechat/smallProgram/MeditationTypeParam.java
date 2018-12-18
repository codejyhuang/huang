package com.hrym.wechat.smallProgram;

import com.hrym.common.base.BaseRequestParam;

import java.io.Serializable;

/**
 * Created by hrym13 on 2018/4/16.
 */
public class MeditationTypeParam extends BaseRequestParam implements Serializable {

    private MeditationTypeData meditationTypeData;

    public MeditationTypeData getMeditationTypeData() {
        return meditationTypeData;
    }

    public void setMeditationTypeData(MeditationTypeData meditationTypeData) {
        this.meditationTypeData = meditationTypeData;
    }
    public Integer getMeditationTypeId() {
        return meditationTypeData.getMeditationTypeId();
    }

    public String getMeditationTypeName() {
        return meditationTypeData.getMeditationTypeName();
    }

    public String getMeditationTypeIntro() {
        return meditationTypeData.getMeditationTypeIntro();
    }

    public String getAuthor() {
        return meditationTypeData.getAuthor();
    }

    public Integer getCreatedTime() {
        return meditationTypeData.getCreatedTime();
    }

}
class MeditationTypeData implements Serializable{

    private Integer meditationTypeId;
    private String meditationTypeName;
    private String meditationTypeIntro;
    private String author;
    private Integer createdTime;

    public Integer getMeditationTypeId() {
        return meditationTypeId;
    }

    public String getMeditationTypeName() {
        return meditationTypeName;
    }

    public String getMeditationTypeIntro() {
        return meditationTypeIntro;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getCreatedTime() {
        return createdTime;
    }

    public void setMeditationTypeId(Integer meditationTypeId) {
        this.meditationTypeId = meditationTypeId;
    }

    public void setMeditationTypeName(String meditationTypeName) {
        this.meditationTypeName = meditationTypeName;
    }

    public void setMeditationTypeIntro(String meditationTypeIntro) {
        this.meditationTypeIntro = meditationTypeIntro;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCreatedTime(Integer createdTime) {
        this.createdTime = createdTime;
    }
}
