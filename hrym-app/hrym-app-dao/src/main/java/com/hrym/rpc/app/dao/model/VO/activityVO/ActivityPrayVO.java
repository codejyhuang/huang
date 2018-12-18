package com.hrym.rpc.app.dao.model.VO.activityVO;

import com.hrym.rpc.app.dao.model.activity.ActivityPray;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mj on 2018/4/19.
 */
public class ActivityPrayVO extends ActivityPray implements Serializable {

    private Integer status;
    private Integer peopleNum;
    private Integer num;
    private String percent;
    private Integer helpNum;
    private Integer state;
    private String timeStr;
    private List list;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(Integer peopleNum) {
        this.peopleNum = peopleNum;
    }


    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public Integer getHelpNum() {
        return helpNum;
    }

    public void setHelpNum(Integer helpNum) {
        this.helpNum = helpNum;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getTimeStr() {
        return timeStr;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }

    @Override
    public String toString() {
        return "ActivityPrayVO{" +
                "status=" + status +
                ", peopleNum=" + peopleNum +
                ", num=" + num +
                ", percent='" + percent + '\'' +
                ", helpNum=" + helpNum +
                ", state=" + state +
                ", timeStr='" + timeStr + '\'' +
                ", list=" + list +
                '}';
    }
}
