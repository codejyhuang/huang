package com.hrym.wechat.smallProgram;

import com.hrym.common.base.BaseRequestParam;
import net.sf.json.JSON;

import java.io.Serializable;
import java.util.List;

/**
 * 计数上报接受实体类
 * Created by hrym13 on 2018/4/16.
 */
public class CountRecordParam extends BaseRequestParam implements Serializable {

    private CountRecordData data;

    public CountRecordData getData() {
        return data;
    }

    public void setData(CountRecordData data) {
        this.data = data;
    }

    public Integer getCountId() {
        return data.getCountId();
    }

    public Integer getScheduleId() {
        return data.getScheduleId();
    }

    public Integer getCreatedTime() {
        return data.getCreatedTime();
    }

    public Integer getUserId() {
        return data.getUserId();
    }

    public Integer getCount() {
        return data.getCount();
    }

    public Integer getTodayNumber() {
        return data.getTodayNumber();
    }

    public String getOpenId() {
        return data.getOpenId();
    }

    public Integer getPageNumber() {
        return data.getPageNumber();
    }

    public String getTotalPage() {
        return data.getTotalPage();
    }

    public Integer getMeditationTypeId() {
        return data.getMeditationTypeId();
    }

    public List getScheduleIdList() {
        return data.getScheduleIdList();
    }

    public String getLaw() {
        return data.getLaw();
    }

    public String getStringList() {
        return data.getStringList();
    }

}

class CountRecordData implements Serializable{

    private Integer countId;            //主键ID
    private Integer scheduleId;        //活动ID
    private Integer createdTime;
    private Integer userId;
    private Integer count;
    private Integer todayNumber;        //今日报数
    private String openId;
    private  Integer pageNumber;        //开始页树
    private String totalPage;           //总页数
    private Integer meditationTypeId;    //共修活动类型
    private String law;                  //法本预习
    private List scheduleIdList;           //共修类型ID集合
    private String stringList;            //接收前端json


    public String getStringList() {
        return stringList;
    }

    public void setStringList(String stringList) {
        this.stringList = stringList;
    }

    public String getLaw() {
        return law;
    }

    public void setLaw(String law) {
        this.law = law;
    }

    public List getScheduleIdList() {
        return scheduleIdList;
    }

    public void setScheduleIdList(List scheduleIdList) {
        this.scheduleIdList = scheduleIdList;
    }

    public Integer getMeditationTypeId() {
        return meditationTypeId;
    }

    public void setMeditationTypeId(Integer meditationTypeId) {
        this.meditationTypeId = meditationTypeId;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(String totalPage) {
        this.totalPage = totalPage;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Integer getCountId() {
        return countId;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public Integer getCreatedTime() {
        return createdTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setCountId(Integer countId) {
        this.countId = countId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public void setCreatedTime(Integer createdTime) {
        this.createdTime = createdTime;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setTodayNumber(Integer todayNumber) {
        this.todayNumber = todayNumber;
    }

    public Integer getCount() {
        return count;
    }

    public Integer getTodayNumber() {
        return todayNumber;
    }
}
