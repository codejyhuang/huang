package com.hrym.rpc.app.common.constant;

import com.hrym.common.base.BaseRequestParam;

import java.io.Serializable;

/**
 * Created by mj on 2017/12/29.
 */
public class RecordParam extends BaseRequestParam implements Serializable {

    // 参数data
    private RecordData data;

    public RecordData getData() {
        return data;
    }

    public void setData(RecordData data) {
        this.data = data;
    }

    public String getToken() {
        return data.getToken();
    }

    public Integer getItemId() {
        return data.getItemId();
    }

    public Integer getPageNo() {
        return data.getPageNo();
    }

    public Integer getTypeId() {
        return data.getTypeId();
    }
}
class RecordData implements Serializable {

    //用户token
    private String token;
    //功课id
    private Integer itemId;

    private Integer pageNo;

    private Integer typeId;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
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
}
