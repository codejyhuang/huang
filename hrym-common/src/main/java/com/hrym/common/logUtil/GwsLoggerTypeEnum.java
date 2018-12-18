package com.hrym.common.logUtil;

/**
 * 全局日志类型常量定义
 * Created by mj on 2017/11/9.
 */
public enum GwsLoggerTypeEnum {

    TASK_CLICK(1,"功课点击（人）"),  //功课点击（人）
    TASK_CREATE(2,"功课创建（人）"), //功课点击（人）
    DO_TASK(3,"做功课（人）"),      //做功课（人）
    TIME(4,"使用时长（分）"),         //使用时长（分）
    RATE_SHARE(5,"进度分享（个）"),   //进度分享（个）
    TASK_END(6,"功课完结（个）"),     //功课完结（个）
    END_SHARE(7,"完结分享（个）"),    //完结分享（个）
    TOP_TASK(8,"最热门功课"),     //最热门功课
    AVERAGE_NUM(9,"平均制定次数（次）"),  //平均制定次数（次）
    AVERAGE_DAY(10,"平均制定期限（天）"),  //平均制定期限（天）
    TASK(11,"功课"),
    ASSOCIATION(12,"创建社群数（个）"),
    ASSOCIATION_SHARE(13,"社群分享"),
    ASSOCIATION_APPLY(14,"社群申请"),
    ASSOCIATION_APPROVE(15,"社群申请通过数（个）"),
    SUGGEST(16,"查找点击（次）"),
    REALNAME(17,"实名认证（人）"),
    PHONE(18,"手机认证"),
    FEADBACK(19,"意见反馈（人）"),
    SEX_RATIO(20,"性别比例（男：女）"),
    MUSIC_CLICK(21,"佛乐点击（次）"),
    FOJING_CLICK(22,"佛经点击（次）"),
    ARTICLE_SHARE(23,"文章分享（个）")



            ;
    private Integer type;
    private String desc;

    private GwsLoggerTypeEnum (Integer type,String desc) {
        this.type = type;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }
    public String getDesc() {
        return desc;
    }

    public static GwsLoggerTypeEnum getEnumByNumber(Integer type){
        if (type == null)
            return null;
        for (GwsLoggerTypeEnum tSORNOTEnum : GwsLoggerTypeEnum.values()) {
            if (tSORNOTEnum.getType().equals(type))
                return tSORNOTEnum;
        }
        return null;
    }

    public static GwsLoggerTypeEnum getEnumByDesc(String desc){
        if (desc == null)
            return null;
        for (GwsLoggerTypeEnum tSORNOTEnum : GwsLoggerTypeEnum.values()) {
            if (tSORNOTEnum.getDesc().equals(desc))
                return tSORNOTEnum;
        }
        return null;
    }
}
