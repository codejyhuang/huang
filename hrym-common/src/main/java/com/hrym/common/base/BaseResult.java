package com.hrym.common.base;

import java.io.Serializable;

/**
 * 统一返回结果类
 * Created by qhzhang on 2017/2/18.
 */
public class BaseResult implements Serializable{

    // 状态码：0成功，其他为失败
    public String code = "0";

    // 成功为success，其他为失败原因
    public String message = "成功";

    // 数据结果集
    public Object data;

    public BaseResult(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseResult{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
