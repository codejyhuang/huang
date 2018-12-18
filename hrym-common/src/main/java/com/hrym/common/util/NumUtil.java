package com.hrym.common.util;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;

/**
 * 数字运算工具类
 * Created by mj on 2017/7/12.
 */
public class NumUtil {

    /**
     * 获取百分比
     *
     * @param num
     * @param total
     */
    public static String getPercent(double num, double total) {

        if ((num == 0)) {
            return "0%";
        }
        if (num >= total || total == 0) {
            return "100%";
        }
        DecimalFormat df = new DecimalFormat("0%");
        //可以设置精确几位小数
        df.setMaximumFractionDigits(0);
        //模式 例如四舍五入
        df.setRoundingMode(RoundingMode.HALF_UP);

        double accuracy_num = num * 1.0 / total * 1.0;
        String str = df.format(accuracy_num);
        if (str.equals("0%")) {
            str = "1%";
        }
        if (str.equals("100%")) {
            str = "99%";
        }
        return str;
    }

    /**
     * 获取字符double的格式化数据
     *
     * @param value
     * @return
     */
    public static String formatFloatNumber(double value) {
        System.out.println(value);
        if (value != 0.00) {
            java.text.DecimalFormat df = new java.text.DecimalFormat("########");
            return df.format(value);
        } else {
            return "0";
        }

    }

    //计算今日建议数量
    public static int getTargetNum(double totalNum, Integer totalTime, double doneNum, int startTime) {

        //已过的天数
        int afterDay = 0;
        try {
            afterDay = DateUtil.daysBetween(startTime, DateUtil.currentSecond());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //今日建议
        double targetNum = 0;
        double newTotalNum = 0.6 * totalNum;//总完成数的60%
        double newTotalTime = 0.5 * totalTime;//总周期的50%

        if (afterDay >= 0 && totalTime <= 10) {
            targetNum = (totalNum - doneNum) / (totalTime - afterDay);
            return (int) Math.ceil(targetNum);//向上取整
        }
        if (afterDay >= 0 && afterDay < newTotalTime && doneNum < newTotalNum) {
            targetNum = (newTotalNum - doneNum) / (newTotalTime - afterDay);
            return (int) Math.ceil(targetNum);//向上取整
        }
        if (afterDay >= 0) {
            targetNum = (totalNum - doneNum) / (totalTime - afterDay);
            return (int) Math.ceil(targetNum);//向上取整
        }

        return (int) targetNum;
    }

    public static String getDayNumStr(Double num) {
        if (num != null && !num.equals(new Double(0.0))) {
            String c = Math.round(num) + "";
            if (c.length() > 4 && c.length() < 9) {
                return (c.substring(0, c.length() - 4) + "万" + c.substring(c.length() - 4, c.length()));
            }
            if (c.length() < 5) {
                return (c);
            }
        } else {
            return ("0");
        }
        return "";
    }

    public static String getTotalNum(Double num, String str) {
        if (num != null && !num.equals(new Double(0.0))) {
            String b = Math.round(num) + "";
            if (b.length() > 4 && b.length() < 9) {
                String substring = b.substring(b.length() - 4, b.length());
                return (b.substring(0, b.length() - 4) + "万" + b.substring(b.length() - 4, b.length()));
            }
            if (b.length() >= 9) {
                return (b.substring(0, b.length() - 8) + "亿" + b.substring(b.length() - 8, b.length() - 4) + "万" + b.substring(b.length() - 4, b.length()));
            }
            if (b.length() < 5) {
                return (b);
            }
        } else {
            return (str);
        }
        return "";
    }


    public static String getTotalNumStr(Double num, String str) {
        if (num != null && !num.equals(new Double(0.0))) {
            String b = Math.round(num) + "";
            if (b.length() > 4 && b.length() < 9) {
                String g = b.substring(b.length() - 4, b.length());
                for (int i = 1; i <= 4; i++) {
                    if (g.substring(0, 1).equals("0")) {
                        g = g.substring(1, g.length());
                    } else {
                        break;
                    }
                }
                String s = b.substring(0, b.length() - 4) + "万" + g;
                return s;
            }
            if (b.length() >= 9) {
                String g = b.substring(b.length() - 4, b.length());
                String w = b.substring(b.length() - 8, b.length() - 4);
                for (int i = 1; i <= 4; i++) {
                    if (g.substring(0, 1).equals("0")) {
                        g = g.substring(1, g.length());
                    } else {
                        break;
                    }
                }
                for (int i = 1; i <= 4; i++) {
                    if (w.substring(0, 1).equals("0")) {
                        w = w.substring(1, w.length());
                    } else {
                        break;
                    }
                }
                if (w.equals("")) {
                    String wb = b.substring(0, b.length() - 8) + "亿" + g;
                    if (g.equals("")) {
                        return b.substring(0, b.length() - 8) + g + "亿";
                    }
                    return wb;
                }
                String s = b.substring(0, b.length() - 8) + "亿" + w + "万" + g;
                return s;
            }
            if (b.length() < 5) {
                return b;
            }
        } else {
            return (str);
        }
        return "";
    }
}
