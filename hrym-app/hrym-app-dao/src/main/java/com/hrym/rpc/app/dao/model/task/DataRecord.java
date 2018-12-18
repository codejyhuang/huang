package com.hrym.rpc.app.dao.model.task;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 登录数据统计
 * Created by hrym13 on 2018/3/5.
 */
@Table(name = "t_data_record")
public class DataRecord implements Serializable {
    @Id
    private Integer Id;
    private Integer uuId;
    private String Time;
    private String userName;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getUuId() {
        return uuId;
    }

    public void setUuId(Integer uuId) {
        this.uuId = uuId;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "DataRecord{" +
                "Id=" + Id +
                ", uuId=" + uuId +
                ", Time='" + Time + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
