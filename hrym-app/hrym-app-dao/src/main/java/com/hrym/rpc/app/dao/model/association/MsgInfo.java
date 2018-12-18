package com.hrym.rpc.app.dao.model.association;

import java.io.Serializable;

/**
 * 社群消息
 * Created by mj on 2017/9/10.
 */
public class MsgInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer msgId;                  //消息id
    private Integer msgTypeValue;          //消息类型值(如社群id，功课id_用户id；用户id)'
    private Integer topicId;                //主题
    private Integer fromId;                 //发起方
    private Integer toId;                   //接受方id
    private String content;                 //内容
    private Integer contentType;            //0:回复\n1:点赞\n2:申请入群\n3:审核
    private Integer parentIdt;              //父消息id，没有为0
    private Integer status;                  //0：拒绝；1：同意；2：审核中；3:退群
    private Integer isread;                 //0：未读；1:已读
    private Integer createTime;             //创建时间
    private Integer msgType;                //1:系统；2：社群；3：功课;4:用户认证

    public Integer getIsread() {
        return isread;
    }

    public void setIsread(Integer isread) {
        this.isread = isread;
    }

    public Integer getMsgId() {
        return msgId;
    }

    public void setMsgId(Integer msgId) {
        this.msgId = msgId;
    }

    public Integer getMsgTypeValue() {
        return msgTypeValue;
    }

    public void setMsgTypeValue(Integer msgTypeValue) {
        this.msgTypeValue = msgTypeValue;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Integer getFromId() {
        return fromId;
    }

    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    public Integer getToId() {
        return toId;
    }

    public void setToId(Integer toId) {
        this.toId = toId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getContentType() {
        return contentType;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    public Integer getParentIdt() {
        return parentIdt;
    }

    public void setParentIdt(Integer parentIdt) {
        this.parentIdt = parentIdt;
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

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    @Override
    public String toString() {
        return "MsgInfo{" +
                "msgId=" + msgId +
                ", msgTypeValue=" + msgTypeValue +
                ", topicId=" + topicId +
                ", fromId=" + fromId +
                ", toId=" + toId +
                ", content='" + content + '\'' +
                ", contentType=" + contentType +
                ", parentIdt=" + parentIdt +
                ", status=" + status +
                ", isread=" + isread +
                ", createTime=" + createTime +
                ", msgType=" + msgType +
                '}';
    }
}
