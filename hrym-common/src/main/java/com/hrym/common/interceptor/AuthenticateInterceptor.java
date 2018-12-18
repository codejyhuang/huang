package com.hrym.common.interceptor;

import com.hrym.common.annotation.Allowed;
import com.hrym.common.base.BaseConstants;
import com.hrym.common.base.BaseResult;
import com.hrym.common.message.KafkaProducerSingleton;
import com.hrym.common.message.MessageBean;
import com.hrym.common.message.MessageConstant;
import com.hrym.common.message.RequestLog;
import com.hrym.common.request.MyHttpServletRequestWrapper;
import com.hrym.common.util.*;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 判断用户是否登录和校验参数
 *
 * Created by hong on 2017/6/22.
 */
public class AuthenticateInterceptor extends HandlerInterceptorAdapter {

    private final Log log = LogFactory.getLog(AuthenticateInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        // 获取json参数
        JSONObject dataObject = new JSONObject();
        if(request instanceof  MyHttpServletRequestWrapper){
            MyHttpServletRequestWrapper myWrapper= new MyHttpServletRequestWrapper(request);

            String jsonStr = GetRequestJsonUtil.getRequestJsonString(myWrapper);
                log.info(""+System.currentTimeMillis()+" requset param =================="+jsonStr);

            // post
            if (request.getMethod().toLowerCase().equals("post")) {

                if (StringUtils.isNotBlank(jsonStr)){
                    JSONObject jsonObject = JSONObject.fromObject(jsonStr);
                    JSONObject headerObject = jsonObject.getJSONObject("header");
                    dataObject = jsonObject.getJSONObject("data");

//                    String cmd = jsonObject.getString("cmd");
//                    String platform = headerObject.getString("platform");
//                    String appname = headerObject.getString("appname");
//                    String v = headerObject.getString("v");
//                    //日志消息推送
//                    RequestLog msg = new RequestLog();
//                    msg.setTopic(MessageConstant.TOPIC_HRYM_LOG);
//                    msg.setTitle("慧修行日志");
//                    msg.setRequestMethod(request.getMethod().toLowerCase());
//                    msg.setRequestUrl(request.getRequestURI());
//                    msg.setCmd(cmd);
//                    msg.setAppname(appname);
//                    msg.setPlatform(platform);
//                    msg.setAppVersion(v);
//                    msg.setContent(dataObject.toString());
//                    msg.setCreateTime(DateUtil.currentSecond());
//                    try {
//                        KafkaProducerSingleton.getInstance().sendKafkaMessage(MessageConstant.TOPIC_HRYM_LOG,msg);
//                    }catch (Exception e){
//                        e.printStackTrace();
//                    }


                    // 判断格式
                    if(headerObject.isEmpty()) {
                        setResponseValue(response,BaseConstants.GWSCODE2001,BaseConstants.GWSMSG2001);
                        return false;
                    }
                }
            }


        }
        // 注解认证
        Class<?> clazz = handler.getClass();
        RequestMapping requestMapping;
        if (clazz.isAssignableFrom(HandlerMethod.class)) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Allowed allowed = handlerMethod.getMethodAnnotation(Allowed.class);
            // Allowed注解的方法不需要验证是否登录
            if (allowed != null) {
                return true;
            }
        }

        // 判断token
        String token = dataObject.getString("token" );
        if(StringUtils.isNotEmpty(token)) {

            String obj = RedisUtil.get(token);
            if(StringUtils.isEmpty(obj)){
                setResponseValue(response,BaseConstants.GWSCODE1001,BaseConstants.GWSMSG1001);
                return false;

            }
            return true;
        }

        setResponseValue(response,BaseConstants.GWSCODE1002,BaseConstants.GWSMSG1002);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request,
                                               HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }

    /**
     * 设置response返回值
     *
     * @param response
     */
    private void setResponseValue(HttpServletResponse response, String code, String msg) {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");

        BaseResult result = new BaseResult(code,msg,null);
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(JSONObject.fromObject(result).toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }

}

