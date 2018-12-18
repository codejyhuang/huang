package com.hrym.wechat.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by hrym13 on 2018/11/8.
 */
@Table(name = "t_user_account")
@Setter
@Getter
public class WechatUserAccount implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private int uuid;
    private String socialUid;
    private String name;
    private String nickName;
    private String mobile;
    private String email;
    private String passWord;
    private Integer sex;
    private Integer source;
    private String avatar;
    private Integer createdTime;
    private Integer updatedTime;
    private Integer status;         //使用状态1：正常使用；0：已注销
    private Integer ymd;
    private String deviceId;
    private String profile;
    private Integer identityAuthState;
    private Integer phoneAuthState;
    private String realName;
    private Integer userTatus;          //1:普通用户；2：上师；
    private Integer recentTime;
    private Integer endTime;
    private Integer startTime;
    private String endTimeis;
    private String startTimeis;
    private String province;
}
