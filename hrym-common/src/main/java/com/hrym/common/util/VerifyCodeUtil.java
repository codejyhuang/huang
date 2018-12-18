package com.hrym.common.util;

import java.util.Calendar;
import java.util.Random;

/**
 * 验证码随机数生成工具
 *
 * Created by hong on 2017/6/23.
 */
public class VerifyCodeUtil {

    //随机生成验证码
    public static String getRandNum(int min, int max) {
        int randNum = min + (int)(Math.random() * ((max - min) + 1));
        return String.valueOf(randNum);
    }


    //随机生成token
    public static String getToken(String key){
        return String.valueOf(MD5Util.MD5(key));
    }


}
