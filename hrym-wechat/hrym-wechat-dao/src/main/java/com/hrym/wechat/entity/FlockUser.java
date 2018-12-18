package com.hrym.wechat.entity;

import com.hrym.wechat.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FlockUser extends BaseEntity {

    public static final Integer ROLE_ADMIN=0;
    public static final Integer ROLE_MEMBER=1;

    private Integer flockId; //共修群id

    private Integer uuid; //用户id

    private Integer type = ROLE_MEMBER; // 0:群管理员  1:群成员

    private Integer order;

    private String avatar; //用户头像url

    private String nickName;

    private Integer src = 0;
    private Integer createTime;
}
