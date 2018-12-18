package com.hrym.rpc.app.util;


import java.io.Serializable;

/**
 * Created by mj on 2017/7/30.
 */
public class Result implements Serializable{

    // 状态码：0成功，其他为失败
    public String code = "0";

    // 成功为success，其他为失败原因
    public String message = "成功";

    //分页总数据
    public long total;

    // 数据结果集
    public Object rows;

    public Result(String code, String message, Object rows) {
        this.code = code;
        this.message = message;
        this.rows = rows;
    }

    public Result(String code, String message,long total,Object rows) {
        this.code = code;
        this.message = message;
        this.total = total;
        this.rows = rows;
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

    @Override
    public String toString() {
        return "Result{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", total=" + total +
                ", rows=" + rows +
                '}';
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getRows() {
        return rows;
    }

    public void setRows(Object data) {
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
