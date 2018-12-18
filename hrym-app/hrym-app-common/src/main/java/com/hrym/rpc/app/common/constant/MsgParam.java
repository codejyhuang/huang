package com.hrym.rpc.app.common.constant;

import com.hrym.common.base.BaseRequestParam;

import java.io.Serializable;

/**
 * Created by hong on 2018/2/26.
 */
public class MsgParam  extends BaseRequestParam implements Serializable {
    // 参数data
    private MsgData data ;

    public MsgData getData() {
        return data;
    }

    public void setData(MsgData data) {
        this.data = data;
    }

    public String getPhonesNo() {
        return data.getPhone();
    }

    public long getSt() {return data.getSt();}

    public long getEt() { return  data.getEt();}

    public String getMsg() { return data.getMsg();}

    @Override
    public String toString() {
        return "MsgParam{" +
                "data=" + data +
                '}';
    }
}

class MsgData implements Serializable{
    private String phone;
    private long st;
    private long et;
    private String msg;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getSt() {
        return st;
    }

    public void setSt(long st) {
        this.st = st;
    }

    public long getEt() {
        return et;
    }

    public void setEt(long et) {
        this.et = et;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "MsgData{" +
                "phone='" + phone + '\'' +
                ", st=" + st +
                ", et=" + et +
                ", msg='" + msg + '\'' +
                '}';
    }
}
