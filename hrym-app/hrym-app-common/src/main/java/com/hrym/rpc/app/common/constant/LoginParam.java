package com.hrym.rpc.app.common.constant;

import com.hrym.common.base.BaseRequestParam;

import java.io.Serializable;

/**
 * Created by hong on 2017/6/23.
 */
public class LoginParam extends BaseRequestParam implements Serializable {

    // 参数data
    private RequestData data ;

    public RequestData getData() {
        return data;
    }

    public void setData(RequestData data) {
        this.data = data;
    }

    public String getPhone() {
        return data.getPhone();
    }

    public String getPhoneCode(){
        return data.getValue();
    }

    public String getNickName(){
        return data.getNick_name();
    }

    public String getToken(){
        return data.getToken();
    }

    public String getAvatar(){
        return data.getAvatar();
    }

    public String getDeviceId(){
        return data.getDevice_id();
    }

    public Integer getType(){
        return data.getType();
    }

    public String getEmail(){
        return data.getEmail();
    }

    public String getSex(){
        return data.getSex();
    }

    public String getYmd(){
        return data.getYmd();
    }

    public String getFile(){
        return data.getFile();
    }

    public String getVersionId(){
        return data.getV();
    }

    public String getProfile(){
        return data.getProfile();
    }

    public Integer getStatus(){
        return data.getStatus();
    }

    public String getSocialUid() {
        return data.getSocialUid();
    }

    public Integer getSource() {
        return data.getSource();
    }

    public Integer getUuid() {
        return data.getUuid();
    }

    public String getInviteCode() { return data.getInvite_code(); }
}

class RequestData implements Serializable{
    private String device_id;
    private String phone;
    private String value;
    private String nick_name;
    private String token;
    private String avatar; //用户头像
    private Integer type;
    private String email;
    private String sex;
    private String ymd;
    private String file;
    private String v;
    private String profile;
    private Integer status;
    private String socialUid; //第三方登录ID
    private Integer source; //注册来源（0->手机，1->微信，2->QQ，3->微博，4->web)
    private Integer uuid;
    private String invite_code; //邀请码

    public String getInvite_code() {
        return invite_code;
    }

    public void setInvite_code(String invite_code) {
        this.invite_code = invite_code;
    }

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getYmd() {
        return ymd;
    }

    public void setYmd(String ymd) {
        this.ymd = ymd;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public String getSocialUid() {
        return socialUid;
    }

    public void setSocialUid(String socialUid) {
        this.socialUid = socialUid;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }
}