package com.hrym.common.util;

public class TableUtil {

    public static String getTaskPlanTableNameByItemType(Integer type) {
        String tableName = "";
        if (type.equals(0)) {
            tableName = "t_task_plan_lesson";
        } else if (type.equals(1)) {
            tableName = "t_task_plan_custom";
        } else {
            tableName = "t_bookcase";
        }
        return tableName;
    }

    public static String getTaskRecordTableNameByItemType(Integer type) {
        String tableName = "";
        if (type.equals(0)) {
            tableName = "t_task_record_lesson";
        } else {
            tableName = "t_task_record_book";
            if (type.equals(1)) {
                tableName = "t_task_record_custom";
            }
        }
        return tableName;
    }
}
