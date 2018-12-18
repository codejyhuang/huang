package com.hrym.wechat.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class ItemVO implements Serializable {

    private Integer itemId;

    private Integer itemContentId;

    private Integer uuid;
    //功课类型
    private Integer type;

}
