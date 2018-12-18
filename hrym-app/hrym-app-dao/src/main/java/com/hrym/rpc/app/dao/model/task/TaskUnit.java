package com.hrym.rpc.app.dao.model.task;

import java.io.Serializable;

/**
 * Created by mj on 2017/7/12.
 */
public class TaskUnit  implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer unitType;
    private String unitDesc;

    public Integer getUnitType() {
        return unitType;
    }

    public void setUnitType(Integer unitType) {
        this.unitType = unitType;
    }

    public String getUnitDesc() {
        return unitDesc;
    }

    public void setUnitDesc(String unitDesc) {
        this.unitDesc = unitDesc;
    }

    @Override
    public String toString() {
        return "TaskUnit{" +
                "unitType=" + unitType +
                ", unitDesc='" + unitDesc + '\'' +
                '}';
    }
}
