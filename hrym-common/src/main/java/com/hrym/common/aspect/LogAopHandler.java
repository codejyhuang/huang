package com.hrym.common.aspect;

import com.alibaba.fastjson.JSONObject;
import com.hrym.common.logUtil.GwsLogger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by mj on 2018/3/26.
 */
public class LogAopHandler {


    /**
     * controller层面记录操作日志
     * 注意此处是aop:around的 因为需要得到请求前的参数以及请求后接口返回的结果
     * @throws Throwable
     */
    public Object doSaveLog(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature method = (MethodSignature) joinPoint.getSignature();
        String methodName = method.getName();
        Object[] objects = joinPoint.getArgs();
        String requestBody = null;
        if (objects != null && objects.length > 0) {
            for (Object object : objects) {
                if (object == null) {
                    requestBody = null;//POST接口参数为空 比如删除XXX
                } else if (object instanceof String) {
                    requestBody = (String) object;//有些接口直接把参数转换成对象了
                } else {
                    requestBody = JSONObject.toJSONString(object);
                }
            }
        }


        //只记录POST方法的日志
        boolean isNeedSaveLog = false;
        //此处不能用getAnnotationByType 是JAVA8的特性,因为注解能够重名,所以得到的是数组
        RequestMapping annotation = method.getMethod().getAnnotation(RequestMapping.class);
        GwsLogger.info("==========annotation.method()==========="+annotation.method());
        for (RequestMethod requestMethod : annotation.method()) {
            GwsLogger.info("==========requestMethod==========="+requestMethod);
            GwsLogger.info("==========RequestMethod.POST==========="+RequestMethod.POST);
            if (requestMethod==RequestMethod.POST) {
//                isNeedSaveLog = true;
            }
        }

        JSONObject requestBodyJson = null;
        try {
            requestBodyJson = JSONObject.parseObject(requestBody);
        } catch (Exception e) {
            e.printStackTrace();
            //do nothing 即POST请求没传body
        }
//        HttpServletRequest request = RequestContextUtil.getRequestByCurrentContext();
//        String userName = RequestContextUtil.getUserNameByCurrentContext();
//        if (StringUtil.isEmpty(userName)) {
//            try {
//                userName = DmsCache.get(requestBodyJson.getString("userName")).getName();
//            } catch (Exception e) {
//                userName = RequestContextUtil.getAsynUserInfoByAutoDeploy().getName();
//            }
//        }

        //得到request的参数后让方法执行它
        //注意around的情况下需要返回result 否则将不会返回值给请求者
        Object result = joinPoint.proceed(objects);
        try {
            GwsLogger.info("==========result==========="+result);
            String resultJson = JSONObject.toJSONString(result);
            GwsLogger.info(resultJson);
            requestBodyJson = JSONObject.parseObject(resultJson);

            String areaName = requestBodyJson.getString("data");
            String list = requestBodyJson.getString("list   ");
            GwsLogger.info("==========requestBodyJson==========="+requestBodyJson);
            if (isNeedSaveLog) {//如果是POST请求 则记录日志

                GwsLogger.info("==========全局日志统计===========");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

