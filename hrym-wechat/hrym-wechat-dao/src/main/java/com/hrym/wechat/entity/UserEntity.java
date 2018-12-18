package com.hrym.wechat.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by hong on 2018/11/15.
 */
@Setter
@Getter
public class UserEntity implements Serializable {

    private Integer uuid;
    private String social_uid;
    private String nickname;
    private Integer source;
    private String avatar;
    private Integer created_time;
    private Integer updated_time;
    private Integer wx_uuid;
    private Integer open_id;


}
