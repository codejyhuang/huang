package com.hrym.wechat.smallProgram;

import com.hrym.common.base.BaseRequestParam;

import java.io.Serializable;

/**
 * Created by hrym13 on 2018/4/16.
 */
public class MeditationScheduleParam extends BaseRequestParam implements Serializable {


    private MeditationScheduleData data;

    public MeditationScheduleData getData() {
        return data;
    }

    public void setData(MeditationScheduleData data) {
        this.data = data;
    }

    public String getOpenId() {
        return data.getOpenId();
    }

    public Integer getUserId() {
        return data.getUserId();
    }

    public Integer getScheduleId() {
        return data.getScheduleId();
    }

    public Integer getTargetNumber() {
        return data.getTargetNumber();
    }

    public Integer getRealNumber() {
        return data.getRealNumber();
    }

    public Integer getCreatedTime() {
        return data.getCreatedTime();
    }

    public Integer getExpectTime() {
        return data.getExpectTime();
    }

    public String getActiveTitle() {
        return data.getActiveTitle();
    }

    public Integer getGroupStatus() {
        return data.getGroupStatus();
    }

    public Integer getStartTime() {
        return data.getStartTime();
    }

    public String getActiveAuthor() {
        return data.getActiveAuthor();
    }

    public String getActiveHeadUrl() {
        return data.getActiveHeadUrl();
    }

    public Integer getUserNumber() {
        return data.getUserNumber();
    }

    public String getActiveContent() {
        return data.getActiveContent();
    }

    public Integer getMeditationTypeId() {
        return data.getMeditationTypeId();
    }

    public Integer getCountdDown() {
        return data.getCountdDown();
    }

    public String getGroupId() {
        return data.getGroupId();
    }

    public Integer getPageNumber() {
        return data.getPageNumber();
    }

    public Integer getTotalPage() {
        return data.getTotalPage();
    }

    public String getOpenGId() {
        return data.getOpenGId();
    }

    public Integer getTodayStatus() {
        return data.getTodayStatus();
    }

    public String getMeditationTypeIds() {
        return data.getMeditationTypeIds();
    }

    public Integer getIsTop() {
        return data.getIsTop();
    }

}
class MeditationScheduleData implements Serializable{

    private Integer pageNumber;          //查询页码，从1开始
    private Integer totalPage;             //总条数
    private Integer scheduleId;        //主键ID
    private Integer targetNumber;      //目标数
    private Integer realNumber;        //本活动已上报数
    private Integer createdTime;       //创建共修时间
    private Integer expectTime;       //预期完成目标结束时间
    private String activeTitle;        //共修内容名称
    private Integer groupStatus;       //共修活动身份
    private Integer startTime;         //共修开始时间
    private String activeAuthor;       //组建者
    private String activeHeadUrl;     //活动url
    private Integer userNumber;         //参加活动人数
    private String activeContent;
    private Integer meditationTypeId;    //共修活动类型
    private Integer userId;
    private String openId;
    private Integer countdDown;
    private String groupId;//群ID
    private String openGId;
    private Integer todayStatus;     //今日状态，todayStatus=0今日排行
    private String meditationTypeIds;   //接受多参，用来分割；
    private Integer isTop;              //是否置顶；0没有置顶；

    public Integer getIsTop() {
        return isTop;
    }

    public void setIsTop(Integer isTop) {
        this.isTop = isTop;
    }

    public String getMeditationTypeIds() {
        return meditationTypeIds;
    }

    public void setMeditationTypeIds(String meditationTypeIds) {
        this.meditationTypeIds = meditationTypeIds;
    }

    public Integer getTodayStatus() {
        return todayStatus;
    }

    public void setTodayStatus(Integer todayStatus) {
        this.todayStatus = todayStatus;
    }

    public String getOpenGId() {
        return openGId;
    }

    public void setOpenGId(String openGId) {
        this.openGId = openGId;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }


    public Integer getCountdDown() {
        return countdDown;
    }

    public void setCountdDown(Integer countdDown) {
        this.countdDown = countdDown;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public Integer getTargetNumber() {
        return targetNumber;
    }

    public Integer getRealNumber() {
        return realNumber;
    }

    public Integer getCreatedTime() {
        return createdTime;
    }

    public Integer getExpectTime() {
        return expectTime;
    }

    public String getActiveTitle() {
        return activeTitle;
    }

    public Integer getGroupStatus() {
        return groupStatus;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public String getActiveAuthor() {
        return activeAuthor;
    }

    public String getActiveHeadUrl() {
        return activeHeadUrl;
    }

    public Integer getUserNumber() {
        return userNumber;
    }

    public String getActiveContent() {
        return activeContent;
    }

    public Integer getMeditationTypeId() {
        return meditationTypeId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public void setTargetNumber(Integer targetNumber) {
        this.targetNumber = targetNumber;
    }

    public void setRealNumber(Integer realNumber) {
        this.realNumber = realNumber;
    }

    public void setCreatedTime(Integer createdTime) {
        this.createdTime = createdTime;
    }

    public void setExpectTime(Integer expectTime) {
        this.expectTime = expectTime;
    }

    public void setActiveTitle(String activeTitle) {
        this.activeTitle = activeTitle;
    }

    public void setGroupStatus(Integer groupStatus) {
        this.groupStatus = groupStatus;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public void setActiveAuthor(String activeAuthor) {
        this.activeAuthor = activeAuthor;
    }

    public void setActiveHeadUrl(String activeHeadUrl) {
        this.activeHeadUrl = activeHeadUrl;
    }

    public void setUserNumber(Integer userNumber) {
        this.userNumber = userNumber;
    }

    public void setActiveContent(String activeContent) {
        this.activeContent = activeContent;
    }

    public void setMeditationTypeId(Integer meditationTypeId) {
        this.meditationTypeId = meditationTypeId;
    }
}
