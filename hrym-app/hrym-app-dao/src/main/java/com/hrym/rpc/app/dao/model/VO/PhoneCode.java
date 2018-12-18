package com.hrym.rpc.app.dao.model.VO;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 *
 * 发送验证吗手机号码实体类
 * Created by mj on 2017/12/5.
 */
@Table(name = "t_phone")
public class PhoneCode implements Serializable {

    @Id
    private Integer id;     //id
    private String name;    //姓名
    private String phone;   //手机号
    private String code;    //验证码

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "PhoneCode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
