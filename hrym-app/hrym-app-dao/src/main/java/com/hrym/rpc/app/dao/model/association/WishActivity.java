package com.hrym.rpc.app.dao.model.association;

import java.io.Serializable;

/**
 * 完成任何活动页
 * Created by hrym13 on 2018/4/8.
 */
public class WishActivity implements Serializable {
    private Integer id;
    private Integer userId;
    private String phone;
    private Integer status;    //活动完成状态：0：未完成；1：完成',
    private Integer createTime;
    private String createTimes;
    private String userName;
    private Integer winner;
    private String wishContent;

    public String getWishContent() {
        return wishContent;
    }

    public void setWishContent(String wishContent) {
        this.wishContent = wishContent;
    }


    public String getCreateTimes() {
        return createTimes;
    }

    public void setCreateTimes(String createTimes) {
        this.createTimes = createTimes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getWinner() {
        return winner;
    }

    public void setWinner(Integer winner) {
        this.winner = winner;
    }

    @Override
    public String toString() {
        return "WishActivity{" +
                "id=" + id +
                ", userId=" + userId +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", createTimes='" + createTimes + '\'' +
                ", userName='" + userName + '\'' +
                ", wishContent='" + wishContent + '\'' +
                ", winner=" + winner +
                '}';
    }
}
