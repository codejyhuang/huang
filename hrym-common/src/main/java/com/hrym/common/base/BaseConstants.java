package com.hrym.common.base;

/**
 * 全局常量
 * 1XXX 权限相关
 * 2XXX 参数相关
 * 3XXX 系统级别异常
 * 4XXX 业务异常
 *
 * Created by mj on 2017/2/18.
 */
public class BaseConstants {

    //http请求返回信息


    public static final String GWSCODE0000 = "0";
    public static final String GWSMSG0000 = "操作成功";

    public static final String GWSCODE1001 = "1001";
    public static final String GWSMSG1001 = "用户尚未登录";

    public static final String GWSCODE1002 = "1002";
    public static final String GWSMSG1002 = "无效token";

    public static final String GWSCODE1003 = "1003";
    public static final String GWSMSG1003 = "用户已注册";

    public static final String GWSCODE2001 = "2001";
    public static final String GWSMSG2001 = "无效的请求数据";

    public static final String GWSCODE2002 = "2002";
    public static final String GWSMSG2002 = "缺少业务参数";

    public static final String GWSCODE2003 = "2003";
    public static final String GWSMSG2003 = "URL请求异常";

    public static final String GWSCODE3001 = "3001";
    public static final String GWSMSG3001 = "系统级异常";        //系统故障，执行异常

    public static final String GWSCODE4001 = "4001";
    public static final String GWSMSG4001 = "验证码发送失败";

    public static final String GWSCODE4002 = "4002";
    public static final String GWSMSG4002 = "请输入正确的验证码";

    public static final String GWSCODE4003 = "4003";
    public static final String GWSMSG4003 = "无响应内容";

    public static final String GWSCODE4004 = "4004";
    public static final String GWSMSG4004 = "TrackerServer getConnection return null";

    public static final String GWSCODE4005 = "4005";
    public static final String GWSMSG4005 = "getStoreStorage return null";

    public static final String GWSCODE4006 = "4006";
    public static final String GWSMSG4006 = "file upload success";

    public static final String GWSCODE4007 = "4007";
    public static final String GWSMSG4007 = "请输入正确的cmd";

    public static final String GWSCODE5001 = "5001";
    public static final String GWSMSG5001 = "您已添加过此功课";

    //分页查询起始页
    public static final Integer PAGE_NUM = 1;
    //分页查询每页记录数
    public static final Integer PAGE_SIZE = 10;
    public static final Integer PAGE_SIZE2 = 5;
    public static final Integer PAGE_SIZE3 = 20;

}
