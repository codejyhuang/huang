package com.hrym.rpc.app.dao.model.task;

import java.io.Serializable;

/**
 * Created by xiaohan on 2017/11/8.
 *
 * 大词典实体类
 */
public class Duden implements Serializable {

    private  Integer textId;
    private String textName;
    private  String textType;
    private  String textContent;
    private  String textFrom;
    private  Integer typeId;

    public String getTextFrom() {
        return textFrom;
    }

    public void setTextFrom(String textFrom) {
        this.textFrom = textFrom;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }



    public Integer getTextId() {
        return textId;
    }

    public void setTextId(Integer textId) {
        this.textId = textId;
    }

    public String getTextType() {
        return textType;
    }

    public void setTextType(String textType) {
        this.textType = textType;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public String getTextName() {
        return textName;
    }

    public void setTextName(String textName) {
        this.textName = textName;
    }

    @Override
    public String toString() {
        return "Duden{" +
                "textId=" + textId +
                ", textName='" + textName + '\'' +
                ", textType='" + textType + '\'' +
                ", textContent='" + textContent + '\'' +
                ", textFrom='" + textFrom + '\'' +
                ", typeId=" + typeId +
                '}';
    }
}
