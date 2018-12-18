package com.hrym.rpc.app.dao.model.system;

import java.io.Serializable;

/**
 * Created by hrym13 on 2018/3/14.
 */
public class UserData implements Serializable {
    private String tableName;
    private Integer count;
    private Integer number;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "tableName='" + tableName + '\'' +
                ", count=" + count +
                ", number=" + number +
                '}';
    }
}
