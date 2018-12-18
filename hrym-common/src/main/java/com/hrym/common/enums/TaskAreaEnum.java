package com.hrym.common.enums;

/**
 * Created by mj on 2017/6/26.
 */
public enum TaskAreaEnum {

    COMMON_TASK_AREA(1,"常用功课专区"),
    BSA_TASK_AREA(2,"慧修行功课专区"),
    XIUXING_AREA(3,"修行专区"),
    HOT_TASK_AREA(4,"热门功课"),
    RECOMMEND_TASK_AREA(5,"推荐功课"),
    HUI_REPAIR_TASK_AREA(6,"慧修行能帮你做什么")
    ;
    private Integer type;
    private String desc;

    private TaskAreaEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }
    public String getDesc() {
        return desc;
    }

    public static TaskAreaEnum getEnumByNumber(Integer type){
        if (type == null)
            return null;
        for (TaskAreaEnum tSORNOTEnum : TaskAreaEnum.values()) {
            if (tSORNOTEnum.getType().equals(type))
                return tSORNOTEnum;
        }
        return null;
    }

    public static TaskAreaEnum getEnumByDesc(String desc){
        if (desc == null)
            return null;
        for (TaskAreaEnum tSORNOTEnum : TaskAreaEnum.values()) {
            if (tSORNOTEnum.getDesc().equals(desc))
                return tSORNOTEnum;
        }
        return null;
    }
}
