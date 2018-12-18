package com.hrym.common.message;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Created by hong on 2017/8/31.
 */
@Table(name="NB_MESSAGE_FLOW")
public class MessageFlowBean {

    public static final int IS_READ_NO = 0;
    public static final int IS_READ_YES = 1;

    @Column(name="MSGID")
    private long msgId;

    @Column(name="SENDER")
    private long sender;

    @Column(name="RECEIVER")
    private long receiver;

    @Column(name="ISREAD")
    private int isRead;

    @Column(name="READTIME")
    private long readTime;

    public long getMsgId()
    {
        return this.msgId;
    }

    public void setMsgId(long msgId)
    {
        this.msgId = msgId;
    }

    public long getSender()
    {
        return this.sender;
    }

    public void setSender(long sender)
    {
        this.sender = sender;
    }

    public long getReceiver()
    {
        return this.receiver;
    }

    public void setReceiver(long receiver)
    {
        this.receiver = receiver;
    }

    public int getIsRead()
    {
        return this.isRead;
    }

    public void setIsRead(int isRead)
    {
        this.isRead = isRead;
    }

    public long getReadTime()
    {
        return this.readTime;
    }

    public void setReadTime(long readTime)
    {
        this.readTime = readTime;
    }

}
