package com.hrym.wechat.smallProgram;

import com.hrym.common.base.BaseRequestParam;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created on 2018/11/08.
 */
public class ItemParam extends BaseRequestParam {

    private ItemDataParam data;

    public ItemDataParam getData() {
        return data;
    }

    public void setData(ItemDataParam data) {
        this.data = data;
    }


    public Integer getUserId() {
        return data.getUserId();
    }

    public String getIntro() {
        return data.getIntro();
    }

    public String getLessonName() {
        return data.getLessonName();
    }

    public String getUnit() {
        return data.getUnit();
    }

    public Integer getFlockId() {
        return data.getFlockId();
    }

    public Integer getItemId() {
        return data.getItemId();
    }

    public Integer getItemContentId() {
        return data.getItemContentId();
    }

    public Integer getType() {
        return data.getType();
    }

    public Integer getTimeType() {
        return data.getTimeType();
    }

    public Integer getCurrentPage() {
        return data.getCurrentPage();
    }

    public Integer getPageSize() {
        return data.getPageSize();
    }

    public Integer getId() {
        return data.getId();
    }

    public Integer getYear() {
        return data.getYear();
    }
    public String getYmd() {
        return data.getYmd();
    }

    public Integer getRecordId() {
        return data.getRecordId();
    }

    public Integer getNum(){
        return data.getNum();
    }
    
    public String getInfo() {
        return data.getInfo();
    }
}

@Setter
@Getter
class ItemDataParam implements Serializable {

    private Integer userId;

    private String intro;

    private String lessonName;

    private String unit;

    private Integer flockId;

    private Integer itemId;

    private Integer itemContentId;

    private Integer type;

    private Integer timeType;  // 0:今日  1:本月  2:本年  3:累计


    private Integer year;

    private String ymd;

    private Integer num;

    private Integer recordId;

    private Integer currentPage;

    private Integer pageSize;

    private Integer id;
    private String info;
    
}

