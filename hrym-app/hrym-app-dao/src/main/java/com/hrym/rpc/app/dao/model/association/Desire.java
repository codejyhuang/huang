package com.hrym.rpc.app.dao.model.association;

import java.io.Serializable;

/**
 * Created by hrym13 on 2018/4/3.
 */
public class Desire implements Serializable {

    private Integer Id;
    private String desire;
    private Integer createdTime;
    private String createdTimes;
    private Integer count;

    public String getCreatedTimes() {
        return createdTimes;
    }

    public void setCreatedTimes(String createdTimes) {
        this.createdTimes = createdTimes;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getDesire() {
        return desire;
    }

    public void setDesire(String desire) {
        this.desire = desire;
    }

    public Integer getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Integer createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Desire{" +
                "Id=" + Id +
                ", desire='" + desire + '\'' +
                ", createdTime=" + createdTime +
                ", createdTimes='" + createdTimes + '\'' +
                ", count=" + count +
                '}';
    }
}
