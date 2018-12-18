package com.hrym.rpc.app.dao.model.task;

import java.io.Serializable;

/**
 * 音频资源字表实体类
 * Created by mj on 2017/9/6.
 */
public class TaskSubResource implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idtSubResource;
    private Integer type;
    private String value;
    private String des;
    private Integer resourceId;

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
        return "TaskSubResource{" +
                "idtSubResource=" + idtSubResource +
                ", type=" + type +
                ", value='" + value + '\'' +
                ", des='" + des + '\'' +
                ", resourceId=" + resourceId +
                '}';
    }
}
