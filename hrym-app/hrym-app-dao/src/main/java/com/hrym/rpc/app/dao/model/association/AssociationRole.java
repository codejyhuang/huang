package com.hrym.rpc.app.dao.model.association;

import java.io.Serializable;

/**
 * 社群权限
 * Created by mj on 2017/9/10.
 */
public class AssociationRole implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idtAssociationRole;     //ID
    private Integer associationId;          //社群id
    private Integer userId;                 //用户id
    private Integer approvalRole;           //成员加入是否需要审批\n0:不需要\n1:需要审批\n
    private Integer scanRole;               //允许圈外用户搜索圈子和内容\n0:允许\n1:不允许
    private Integer shareRole;              //是否允许分享\n0:允许\n1:不允许
    private Integer publishTopicRole;       //发主题权限\n0:所有人\n1:仅群主\n2:群主、嘉宾、管理员
    private Integer privateChatRole;        //是否允许私聊\n0:允许\n1:不允许
    private Integer memberListRole;         //开放圈子成员列表\n0:开放\n1:不开放
    private String receiveTopicRole;        //接收主题动态\n0:所有动态\n1:@你的动态

    public Integer getIdtAssociationRole() {
        return idtAssociationRole;
    }

    public void setIdtAssociationRole(Integer idtAssociationRole) {
        this.idtAssociationRole = idtAssociationRole;
    }

    public Integer getAssociationId() {
        return associationId;
    }

    public void setAssociationId(Integer associationId) {
        this.associationId = associationId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getApprovalRole() {
        return approvalRole;
    }

    public void setApprovalRole(Integer approvalRole) {
        this.approvalRole = approvalRole;
    }

    public Integer getScanRole() {
        return scanRole;
    }

    public void setScanRole(Integer scanRole) {
        this.scanRole = scanRole;
    }

    public Integer getShareRole() {
        return shareRole;
    }

    public void setShareRole(Integer shareRole) {
        this.shareRole = shareRole;
    }

    public Integer getPublishTopicRole() {
        return publishTopicRole;
    }

    public void setPublishTopicRole(Integer publishTopicRole) {
        this.publishTopicRole = publishTopicRole;
    }

    public Integer getPrivateChatRole() {
        return privateChatRole;
    }

    public void setPrivateChatRole(Integer privateChatRole) {
        this.privateChatRole = privateChatRole;
    }

    public Integer getMemberListRole() {
        return memberListRole;
    }

    public void setMemberListRole(Integer memberListRole) {
        this.memberListRole = memberListRole;
    }

    public String getReceiveTopicRole() {
        return receiveTopicRole;
    }

    public void setReceiveTopicRole(String receiveTopicRole) {
        this.receiveTopicRole = receiveTopicRole;
    }

    @Override
    public String toString() {
        return "AssociationRole{" +
                "idtAssociationRole=" + idtAssociationRole +
                ", associationId=" + associationId +
                ", userId=" + userId +
                ", approvalRole=" + approvalRole +
                ", scanRole=" + scanRole +
                ", shareRole=" + shareRole +
                ", publishTopicRole=" + publishTopicRole +
                ", privateChatRole=" + privateChatRole +
                ", memberListRole=" + memberListRole +
                ", receiveTopicRole='" + receiveTopicRole + '\'' +
                '}';
    }
}
