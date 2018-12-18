package com.hrym.rpc.app.dao.model.VO;

import java.io.Serializable;

/**
 * Created by hong on 2018/2/24.
 */
public class SplashScreemVO implements Serializable {


    private Integer id;

    //闪屏pic地址
    private String pic;

    //pic跳转地址
    private String url;

    //延迟时间
    private Integer time;

    private String xpic;
    //开始时间-秒
    private Integer st;
    private String sts;
    //结束时间-秒
    private Integer et;
    private String ets;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getXpic() {
        return xpic;
    }

    public void setXpic(String xpic) {
        this.xpic = xpic;
    }

    public Integer getSt() {
        return st;
    }

    public void setSt(Integer st) {
        this.st = st;
    }

    public String getSts() {
        return sts;
    }

    public void setSts(String sts) {
        this.sts = sts;
    }

    public Integer getEt() {
        return et;
    }

    public void setEt(Integer et) {
        this.et = et;
    }

    public String getEts() {
        return ets;
    }

    public void setEts(String ets) {
        this.ets = ets;
    }

    @Override
    public String toString() {
        return "SplashScreemVO{" +
                "id=" + id +
                ", pic='" + pic + '\'' +
                ", url='" + url + '\'' +
                ", time=" + time +
                ", xpic='" + xpic + '\'' +
                ", st=" + st +
                ", sts='" + sts + '\'' +
                ", et=" + et +
                ", ets='" + ets + '\'' +
                '}';
    }
}
