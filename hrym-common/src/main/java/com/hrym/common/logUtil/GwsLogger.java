package com.hrym.common.logUtil;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 *  全局日志打印
 * Created by mj on 2017/11/9.
 */
public class GwsLogger {

    private static Map<String,Logger> loggerMap = new HashMap<String,Logger>();

    public static void main(String[] args) throws ClassNotFoundException {
        GwsLogger.error("自定义LOG","sss444444");
        GwsLogger.info("1",233,GwsLoggerTypeEnum.AVERAGE_DAY.getDesc(),"hjkjjfjfjfff");
    }

    public static void debug(Object message){
        String className = getClassName();
        Logger log = getLogger(className);
        if(log.isDebugEnabled()){
            log.debug(System.currentTimeMillis()+" requset param ================== "+message);
        }
    }

    public static void debug(String tag, Object message){
        String className = getClassName();
        Logger log = getLogger(className);
        if(log.isDebugEnabled()){
            log.debug(System.currentTimeMillis()+new StringBuffer().append("【").append(tag).append("】================== ").append(message).toString());
        }
    }

    public static void info(Object message){
        String className = getClassName();
        Logger log = getLogger(className);
        if(log.isInfoEnabled()){
            log.info(System.currentTimeMillis()+" requset param ================== "+message);
        }
    }

    public static void info(String tag, Object message){
        String className = getClassName();
        Logger log = getLogger(className);
        if(log.isInfoEnabled()){
            log.info(System.currentTimeMillis()+new StringBuffer().append("【").append(tag).append("】================== ").append(message).toString());
        }
    }

    public static void info(String tag, Integer uuid,String deviceId,Object message){
        String className = getClassName();
        Logger log = getLogger(className);
        if(log.isInfoEnabled()){
            log.info(System.currentTimeMillis()+new StringBuffer().append("【").append(tag).append("】用户id:"+uuid+"--设备号:"+deviceId+" ================== ").append(message).toString());
        }
    }


    public static void warn(Object message){
        String className = getClassName();
        Logger log = getLogger(className);
        log.warn(System.currentTimeMillis()+" requset param ================== "+message);
    }

    public static void warn(String tag, Object message){
        String className = getClassName();
        Logger log = getLogger(className);
        log.warn(System.currentTimeMillis()+new StringBuffer().append("【").append(tag).append("】================== ").append(message).toString());
    }

    public static void error(Object message){
        String className = getClassName();
        Logger log = getLogger(className);
        log.error(System.currentTimeMillis()+" requset param ================== "+message);
    }

    public static void error(String tag, Object message){
        String className = getClassName();
        Logger log = getLogger(className);
        log.error(System.currentTimeMillis()+new StringBuffer().append("【").append(tag).append("】================== ").append(message).toString());
    }

    /**
     * 获取最开始的调用者所在类
     * @return
     */
    private static String getClassName(){
        Throwable th = new Throwable();
        StackTraceElement[] stes = th.getStackTrace();
        StackTraceElement ste = stes[2];
        return ste.getClassName();
    }

    /**
     * 根据类名获得logger对象
     * @param className
     * @return
     */
    private static Logger getLogger(String className){
        Logger log = null;
        if(loggerMap.containsKey(className)){
            log = loggerMap.get(className);
        }else{
            try {
                log = Logger.getLogger(Class.forName(className));
                loggerMap.put(className, log);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return log;
    }

    /**
     * 获取调用此函数的代码的位置
     * @return 包名.类名.方法名(行数)
     */
    public static String getCodeLocation(){
        try{
            /*** 获取输出信息的代码的位置 ***/
            String location = "";
            StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
            location = "["+stacks[2].getMethodName()
                    + "(" + stacks[2].getLineNumber() + ")"+"]-";
            return location;
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return "";
        }
    }

}
