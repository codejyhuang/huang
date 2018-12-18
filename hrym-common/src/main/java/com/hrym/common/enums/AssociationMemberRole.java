package com.hrym.common.enums;

/**
 * Created by mj on 2017/9/8.
 */
public enum  AssociationMemberRole {

    //用户类型：0：群管理员；1：特约嘉宾；2：一般人员
    MANAGER(0,"群管理员"),
    GUEST(1,"特约嘉宾"),
    CONSUMER(2,"一般人员"),
    MAINGROU(3,"群主"),
    ;
    private Integer val;
    private String desc;

    private AssociationMemberRole (Integer val,String desc) {
        this.val = val;
        this.desc = desc;
    }

    public Integer getVal() {
        return val;
    }
    public String getDesc() {
        return desc;
    }

    public static AssociationMemberRole getEnumByNumber(Integer val){
        if (val == null)
            return null;
        for (AssociationMemberRole tSORNOTEnum : AssociationMemberRole.values()) {
            if (tSORNOTEnum.getVal().equals(val))
                return tSORNOTEnum;
        }
        return null;
    }

    public static AssociationMemberRole getEnumByDesc(String desc){
        if (desc == null)
            return null;
        for (AssociationMemberRole tSORNOTEnum : AssociationMemberRole.values()) {
            if (tSORNOTEnum.getDesc().equals(desc))
                return tSORNOTEnum;
        }
        return null;
    }
}
