package com.hrym.wechat.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by hrym13 on 2018/6/20.  系统功课  / 经书  实体
 */
@Setter
@Getter
public class ItemLesson implements Serializable {

    private Integer itemId;             // 功课ID
    private Integer itemContentId;      // 功课内容ID
    private Integer type;

    private String versionName;         // 版本名称

    private Integer isAdd;
    

}