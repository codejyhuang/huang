package com.hrym.common.logUtil;

import java.io.Serializable;

/**
 * 全局
 * Created by mj on 2017/11/15.
 */
public class LogBean implements Serializable {

    private String logType ="analyse";
    private Integer uuid;       //用户id
    private Integer group;
    private Integer groupValue;  //功课id；社群id
    private String groupValueDesc;//功课名称，社群名称
    private Integer type;       //功课：功课类型id
    private String typeDesc;    //类型名称
    private String content;     //内容
    private String content2;    //内容2
    private long num;
    private long num2;

    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }

    public long getNum2() {
        return num2;
    }

    public void setNum2(long num2) {
        this.num2 = num2;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    public Integer getGroupValue() {
        return groupValue;
    }

    public void setGroupValue(Integer groupValue) {
        this.groupValue = groupValue;
    }

    public String getGroupValueDesc() {
        return groupValueDesc;
    }

    public void setGroupValueDesc(String groupValueDesc) {
        this.groupValueDesc = groupValueDesc;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent2() {
        return content2;
    }

    public void setContent2(String content2) {
        this.content2 = content2;
    }

    @Override
    public String toString() {
        return "LogBean{" +
                "\"logType\":\"" + logType + "\"" +
                ", \"uuid\":" + uuid +
                ", \"group\":" + group +
                ", \"groupValue\":" + groupValue +
                ", \"groupValueDesc\":\"" + groupValueDesc + "\"" +
                ", \"type\":" + type +
                ", \"typeDesc\":\"" + typeDesc + "\"" +
                ", \"content\":\"" + content + "\"" +
                ", \"content2\":\"" + content2 + "\"" +
                ", \"num\":"+ num +
                ", \"num2\":"+ num2 +
                '}';
    }
}
