package com.hrym.rpc.app.common.constant;

import com.hrym.common.base.BaseRequestParam;

import java.io.Serializable;

/**
 * Created by mj on 2017/8/24.
 */
public class MyParam extends BaseRequestParam implements Serializable {

    // 参数data
    private MyData data ;

    public MyData getData() {
        return data;
    }

    public void setData(MyData data) {
        this.data = data;
    }

    public String getAuthCode(){
        return data.getAuthCode();
    }

    public String getPhoneNo(){
        return data.getPhoneNo();
    }

    public String getIdentityCard(){
        return data.getIdentityCard();
    }

    public String getPic1(){
        return data.getPic1();
    }

    public String getPic2(){
        return data.getPic2();
    }

    public String getPic3(){
        return data.getPic3();
    }

    public String getRealName(){
        return data.getRealName();
    }

    public Integer getUserId(){
        return data.getUserId();
    }

    public String getFilterStr() {
        return data.getFilterStr();
    }

    public Integer getPageNo() {
        return data.getPageNo();
    }

    public Integer getType(){
        return data.getType();
    }

    public String getContent() {
        return data.getContent();
    }

    public Integer getUuid() {
        return data.getUuid();
    }

    public String getPhone() {
        return data.getPhone();
    }

    public  Integer getTextId(){
        return data.getTextId();

    }

    public String getToken() {
        return data.getToken();
    }
}

class MyData implements Serializable{

    private String authCode;    //手机认证码
    private String phoneNo;     //手机号
    private String identityCard;//省份证号
    private String pic1;        //身份证正面
    private String pic2;        //身份证反面
    private String pic3;        //手持身份证
    private String realName;     //真是姓名
    private Integer userId;      //用户id
    private String filterStr;    //过滤字段
    private Integer pageNo;      //分页开始页
    private Integer type;        //请求类型；0：经书；1：音乐
    private String content;      //意见反馈内容
    private Integer uuid;       //用户id
    private String phone;       //手机号
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getTextId() {
        return textId;
    }

    public void setTextId(Integer textId) {
        this.textId = textId;
    }

    private  Integer textId;    //大词典ID


    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getPic1() {
        return pic1;
    }

    public void setPic1(String pic1) {
        this.pic1 = pic1;
    }

    public String getPic2() {
        return pic2;
    }

    public void setPic2(String pic2) {
        this.pic2 = pic2;
    }

    public String getPic3() {
        return pic3;
    }

    public void setPic3(String pic3) {
        this.pic3 = pic3;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFilterStr() {
        return filterStr;
    }

    public void setFilterStr(String filterStr) {
        this.filterStr = filterStr;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}