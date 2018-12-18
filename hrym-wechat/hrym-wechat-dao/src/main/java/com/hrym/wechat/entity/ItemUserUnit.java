package com.hrym.wechat.entity;

import com.hrym.wechat.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户功课量词关系 实体
 */
@Setter
@Getter
public class ItemUserUnit extends BaseEntity {

    private Integer itemId;

    private Integer uuid;

    private Integer itemContentId;

    private String unit;

    private String intro;

    private Integer type;
    private Integer updateTime;

}
