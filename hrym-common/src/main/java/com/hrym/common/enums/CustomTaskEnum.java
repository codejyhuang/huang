package com.hrym.common.enums;

/**
 * Created by mj on 2017/6/26.
 */
public enum CustomTaskEnum {

    COMMON_TASK(1,"普通功课"),
    CUSTOM_TASK(2,"自由定制功课")
    ;
    private Integer type;
    private String desc;

    private CustomTaskEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }
    public String getDesc() {
        return desc;
    }

    public static CustomTaskEnum getEnumByNumber(Integer type){
        if (type == null)
            return null;
        for (CustomTaskEnum tSORNOTEnum : CustomTaskEnum.values()) {
            if (tSORNOTEnum.getType().equals(type))
                return tSORNOTEnum;
        }
        return null;
    }

    public static CustomTaskEnum getEnumByDesc(String desc){
        if (desc == null)
            return null;
        for (CustomTaskEnum tSORNOTEnum : CustomTaskEnum.values()) {
            if (tSORNOTEnum.getDesc().equals(desc))
                return tSORNOTEnum;
        }
        return null;
    }
}
