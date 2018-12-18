package com.hrym.rpc.app.dao.model.task;

import java.io.Serializable;

/**渠道二维码实体类
 * Created by hrym13 on 2017/10/30.
 */
public class Qrcode implements Serializable {

    private Integer channelId;          //渠道ID
    private String url;                 //原始URL
    private String channelName;         // 渠道名称
    private String channelUrl;          //渠道URL
    private Integer accessTimes;        //访问的次数
    private Integer createTime;          //渠道创建时间
    private  String createTimes;
    private Integer select;         //判断访问次数加事件

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelUrl() {
        return channelUrl;
    }

    public void setChannelUrl(String channelUrl) {
        this.channelUrl = channelUrl;
    }

    public Integer getAccessTimes() {
        return accessTimes;
    }

    public void setAccessTimes(Integer accessTimes) {
        this.accessTimes = accessTimes;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public String getCreateTimes() {
        return createTimes;
    }

    public void setCreateTimes(String createTimes) {
        this.createTimes = createTimes;
    }

    public Integer getSelect() {
        return select;
    }

    public void setSelect(Integer select) {
        this.select = select;
    }

    @Override
    public String toString() {
        return "Qrcode{" +
                "channelId=" + channelId +
                ", url='" + url + '\'' +
                ", channelName='" + channelName + '\'' +
                ", channelUrl='" + channelUrl + '\'' +
                ", accessTimes=" + accessTimes +
                ", createTime=" + createTime +
                ", createTimes='" + createTimes + '\'' +
                ", select=" + select +
                '}';
    }
}
