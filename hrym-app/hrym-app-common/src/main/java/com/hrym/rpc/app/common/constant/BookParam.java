package com.hrym.rpc.app.common.constant;

import com.hrym.common.base.BaseRequestParam;

import java.io.Serializable;

/**
 * Created by mj on 2018/4/16.
 */
public class BookParam extends BaseRequestParam implements Serializable {

    private BookData data;

    public BookData getData() {
        return data;
    }

    public void setData(BookData data) {
        this.data = data;
    }

    public String getDevice_id() {
        return data.getDevice_id();
    }

    public String getFilterStr() {
        return data.getFilterStr();
    }

    public Integer getPageNo() {
        return data.getPageNo();
    }

    public Integer getPageSize() {
        return data.getPageSize();
    }

    public String getToken() {
        return data.getToken();
    }

    public Integer getItemId() {
        return data.getItemId();
    }

    public Integer getTypeId() {
        return data.getTypeId();
    }

    public Integer getIndexId() {
        return data.getIndexId();
    }

    public String getPercent() {
        return data.getPercent();
    }

    public Integer getReportNum() {
        return data.getReportNum();
    }

    public Integer getType() {
        return data.getType();
    }
}

class BookData implements Serializable {

    /**
     * 设备号
     */
    private String device_id;
    /**
     * 过滤字段
     */
    private String filterStr;
    /**
     * 分页起始页
     */
    private Integer pageNo;
    /**
     * 每页显示条数
     */
    private Integer pageSize;
    /**
     * 用户登录唯一凭证
     */
    private String token;
    /**
     * 功课id
     */
    private Integer itemId;
    /**
     * 功课类型id
     */
    private Integer typeId;
    /**
     * 经书书架索引id
     */
    private Integer indexId;
    /**
     * 经书完成百分比
     */
    private String percent;
    /**
     * 经书上报数量（多少遍）
     */
    private Integer reportNum;
    /**
     * 经书上报类型
     */
    private Integer type;

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getFilterStr() {
        return filterStr;
    }

    public void setFilterStr(String filterStr) {
        this.filterStr = filterStr;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getIndexId() {
        return indexId;
    }

    public void setIndexId(Integer indexId) {
        this.indexId = indexId;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public Integer getReportNum() {
        return reportNum;
    }

    public void setReportNum(Integer reportNum) {

        this.reportNum = reportNum;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


}
