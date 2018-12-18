package com.hrym.common.enums;

/**
 * Created by mj on 2017/6/26.
 */
public enum IsOrNotEnum {

    YES(1,"是"),
    NO(2,"否"),
    ;
    private Integer type;
    private String desc;

    private IsOrNotEnum (Integer type,String desc) {
        this.type = type;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }
    public String getDesc() {
        return desc;
    }

    public static IsOrNotEnum getEnumByNumber(Integer type){
        if (type == null)
            return null;
        for (IsOrNotEnum tSORNOTEnum : IsOrNotEnum.values()) {
            if (tSORNOTEnum.getType().equals(type))
                return tSORNOTEnum;
        }
        return null;
    }

    public static IsOrNotEnum getEnumByDesc(String desc){
        if (desc == null)
            return null;
        for (IsOrNotEnum tSORNOTEnum : IsOrNotEnum.values()) {
            if (tSORNOTEnum.getDesc().equals(desc))
                return tSORNOTEnum;
        }
        return null;
    }
}
