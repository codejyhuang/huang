package com.hrym.common.base;

import java.io.Serializable;

/**
 * 入参基础类
 * Created by hong on 2017/6/22.
 */
public class BaseRequestParam  implements Serializable {

    // 请求url
    private String cmd;

    // 请求头信息
    public HeaderParam header;

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getAppname() {
        return header.getAppname();
    }

    public String getPlatform() {
        return header.getPlatform();
    }

    public String getV() {
        return header.getV();
    }

    @Override
    public String toString() {
        return "BaseRequestParam{" +
                "cmd='" + cmd + '\'' +
                ", headerParam=" + header +
                '}';
    }
}

/**
 * 参数头信息
 */
class HeaderParam  implements Serializable{
    // app标示
    private String appname;

    // 客户端平台信息
    private String platform;

    // 版本号
    private String v;

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    @Override
    public String toString() {
        return "HeaderParam{" +
                "appname='" + appname + '\'' +
                ", platform='" + platform + '\'' +
                ", v='" + v + '\'' +
                '}';
    }
}
