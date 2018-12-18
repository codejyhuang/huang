package com.hrym.rpc.app.dao.model.association;

import java.io.Serializable;

/**
 * 主题实体类
 * Created by mj on 2017/9/21.
 */
public class Topic implements Serializable {

    private Integer idtTopic;           //主题序号id
    private Integer userId;             //用户id
    private String topicDesc;           //主题描述
    private Integer topicType;          //0:功课相关\n1:社群相关
    private Integer topicTypeValue;      //主题类型描述
    private String url1;
    private String url2;
    private String url3;
    private String url4;
    private String url5;
    private String url6;
    private String url7;
    private String url8;
    private String url9;
    private Integer agreeNum;           //赞同数
    private Integer discussNum;         //评论数
    private Integer createTime;         //创建时间
    private Integer updateTime;         //更新时间

    public Integer getIdtTopic() {
        return idtTopic;
    }

    public void setIdtTopic(Integer idtTopic) {
        this.idtTopic = idtTopic;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTopicDesc() {
        return topicDesc;
    }

    public void setTopicDesc(String topicDesc) {
        this.topicDesc = topicDesc;
    }

    public Integer getTopicType() {
        return topicType;
    }

    public void setTopicType(Integer topicType) {
        this.topicType = topicType;
    }

    public Integer getTopicTypeValue() {
        return topicTypeValue;
    }

    public void setTopicTypeValue(Integer topicTypeValue) {
        this.topicTypeValue = topicTypeValue;
    }

    public String getUrl1() {
        return url1;
    }

    public void setUrl1(String url1) {
        this.url1 = url1;
    }

    public String getUrl2() {
        return url2;
    }

    public void setUrl2(String url2) {
        this.url2 = url2;
    }

    public String getUrl3() {
        return url3;
    }

    public void setUrl3(String url3) {
        this.url3 = url3;
    }

    public String getUrl4() {
        return url4;
    }

    public void setUrl4(String url4) {
        this.url4 = url4;
    }

    public String getUrl5() {
        return url5;
    }

    public void setUrl5(String url5) {
        this.url5 = url5;
    }

    public String getUrl6() {
        return url6;
    }

    public void setUrl6(String url6) {
        this.url6 = url6;
    }

    public String getUrl7() {
        return url7;
    }

    public void setUrl7(String url7) {
        this.url7 = url7;
    }

    public String getUrl8() {
        return url8;
    }

    public void setUrl8(String url8) {
        this.url8 = url8;
    }

    public String getUrl9() {
        return url9;
    }

    public void setUrl9(String url9) {
        this.url9 = url9;
    }

    public Integer getAgreeNum() {
        return agreeNum;
    }

    public void setAgreeNum(Integer agreeNum) {
        this.agreeNum = agreeNum;
    }

    public Integer getDiscussNum() {
        return discussNum;
    }

    public void setDiscussNum(Integer discussNum) {
        this.discussNum = discussNum;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "idtTopic=" + idtTopic +
                ", userId=" + userId +
                ", topicDesc='" + topicDesc + '\'' +
                ", topicType=" + topicType +
                ", topicTypeValue='" + topicTypeValue + '\'' +
                ", url1='" + url1 + '\'' +
                ", url2='" + url2 + '\'' +
                ", url3='" + url3 + '\'' +
                ", url4='" + url4 + '\'' +
                ", url5='" + url5 + '\'' +
                ", url6='" + url6 + '\'' +
                ", url7='" + url7 + '\'' +
                ", url8='" + url8 + '\'' +
                ", url9='" + url9 + '\'' +
                ", agreeNum=" + agreeNum +
                ", discussNum=" + discussNum +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
