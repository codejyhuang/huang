package com.hrym.rpc.app.dao.model.VO;

import java.io.Serializable;

/**
 * Created by mj on 2017/11/4.
 */
public class AssociationVO implements Serializable {

    private Integer associationId;      //社群id
    private String associationIntro;        //社群简介
    private String associationName;     //社群名称
    private Integer memberNum;          //社群人数
    private String avatarUrl;       //社群图标
    private Integer status;         //0：申请加群；1：已经加入；2：审核中


    public Integer getAssociationId() {
        return associationId;
    }

    public void setAssociationId(Integer associationId) {
        this.associationId = associationId;
    }

    public String getAssociationIntro() {
        return associationIntro;
    }

    public void setAssociationIntro(String associationIntro) {
        this.associationIntro = associationIntro;
    }

    public String getAssociationName() {
        return associationName;
    }

    public void setAssociationName(String associationName) {
        this.associationName = associationName;
    }

    public Integer getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(Integer memberNum) {
        this.memberNum = memberNum;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
