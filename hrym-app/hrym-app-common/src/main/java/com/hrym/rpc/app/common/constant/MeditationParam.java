package com.hrym.rpc.app.common.constant;

import com.hrym.common.base.BaseRequestParam;

import java.io.Serializable;

/**
 * 禅修请求参数封装
 * Created by mj on 2018/5/28.
 */
public class MeditationParam extends BaseRequestParam implements Serializable {

    private MeditationData data;

    public MeditationData getData() {
        return data;
    }

    public void setData(MeditationData data) {
        this.data = data;
    }

    public Integer getContentId() {
        return data.getContentId();
    }

    public Integer getItemId() {
        return data.getItemId();
    }

    public Integer getTimeNum() {
        return data.getTimeNum();
    }

    public String getToken() {
        return data.getToken();
    }

    public Integer getPageNo() {
        return data.getPageNo();
    }

    public Integer getPageSize() {
        return data.getPageSize();
    }

    public Integer getReportNum() {
        return data.getReportNum();
    }
}
class MeditationData implements Serializable {

    /**
     * 禅修内容id
     */
    private Integer contentId;
    /**
     * 禅修功课id
     */
    private Integer itemId;
    /**
     * 禅修设置时长
     */
    private Integer timeNum;
    /**
     * 用户token
     */
    private String token;
    /**
     * 分页起始页
     */
    private Integer pageNo;
    /**
     * 每页显示条数
     */
    private Integer pageSize;

    private Integer reportNum;

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

    public Integer getTimeNum() {
        return timeNum;
    }

    public void setTimeNum(Integer timeNum) {
        this.timeNum = timeNum;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getReportNum() {
        return reportNum;
    }

    public void setReportNum(Integer reportNum) {
        this.reportNum = reportNum;
    }
}
