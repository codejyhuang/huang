package com.hrym.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 获取发表时间（如：几天前，几小时前）
 * Created by mj on 2017/10/11.
 */
public class DateFormat {

    private static final long ONE_MINUTE = 60000L;
    private static final long ONE_HOUR = 3600000L;
    private static final long ONE_DAY = 86400000L;
    private static final long ONE_WEEK = 604800000L;

    private static final String ONE_SECOND_AGO = "秒前";
    private static final String ONE_MINUTE_AGO = "分钟前";
    private static final String ONE_HOUR_AGO = "小时前";
    private static final String ONE_WEEK_AGO = "周前";
    private static final String ONE_DAY_AGO = "天前";
    private static final String ONE_MONTH_AGO = "月前";
    private static final String ONE_YEAR_AGO = "年前";


    //时间转换
    public static String format(String str) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:m:s");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long delta = new Date().getTime() - date.getTime();
//          多少秒前
//        if (delta < 1L * ONE_MINUTE) {
//            long seconds = toSeconds(delta);
//            return (seconds <= 0 ? 1 : seconds) + ONE_SECOND_AGO;
//        }
        if (delta < 60L * ONE_MINUTE) {
            long minutes = toMinutes(delta);
            return (minutes <= 0 ? 1 : minutes) + ONE_MINUTE_AGO;
        }
        if (delta < 24L * ONE_HOUR) {
            long hours = toHours(delta);
            return (hours <= 0 ? 1 : hours) + ONE_HOUR_AGO;
        }else {
            int time = DateUtil.getDateToLinuxTime(str,"yyyy-MM-dd HH:m:s");
            return DateUtil.timestampToDates(time,"yyyy-MM-dd");
        }
    }

    public static String format2(String str) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:m:s");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long delta = new Date().getTime() - date.getTime();

//        if (delta < 48L * ONE_HOUR) {
//            return "昨天";
//        }
        if (delta < 48 * ONE_HOUR) {
            int time = DateUtil.getDateToLinuxTime(str,"yyyy-MM-dd HH:m:s");
            return DateUtil.timestampToDates(time,"MM-dd");
        }
        if (delta < 7L * ONE_DAY) {
            return 2 + ONE_DAY_AGO;
        }
        if (delta < 4L * ONE_WEEK) {
            return 1 + ONE_WEEK_AGO;
        }
        if (delta < 12L * 4L * ONE_WEEK) {
            return 1 + ONE_MONTH_AGO;
        } else {
            long years = toYears(delta);
            return 1 + ONE_YEAR_AGO;
        }
    }

    private static long toSeconds(long date) {
        return date / 1000L;
    }

    private static long toMinutes(long date) {
        return toSeconds(date) / 60L;
    }

    private static long toHours(long date) {
        return toMinutes(date) / 60L;
    }

    private static long toDays(long date) {
        return toHours(date) / 24L;
    }

    private static long toWeeks(long date) {
        return toDays(date) / 7L;
    }

    private static long toMonths(long date) {
        return toDays(date) / 30L;
    }

    private static long toYears(long date) {
        return toMonths(date) / 365L;
    }

    public static void main(String[] args) {
        System.out.println(format2("2018-6-7 00:00:00"));
    }
}
