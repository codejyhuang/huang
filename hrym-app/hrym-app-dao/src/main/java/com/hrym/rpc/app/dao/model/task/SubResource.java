package com.hrym.rpc.app.dao.model.task;

import java.io.Serializable;

/**
 * Created by hrym13 on 2017/9/19.
 */
public class SubResource implements Serializable {

    private Integer type;   //  子音频类型
    private String value;   //  子音频URL
    private Integer idtSubResource;   //子音频ID
    private String des;     //子音频描述
    private Integer resourceId; //  父音频ID

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

    @Override
    public String toString() {
        return "SubResource{" +
                "type=" + type +
                ", value='" + value + '\'' +
                ", idtSubResource=" + idtSubResource +
                ", des='" + des + '\'' +
                ", resourceId='" + resourceId + '\'' +
                '}';
    }
}
