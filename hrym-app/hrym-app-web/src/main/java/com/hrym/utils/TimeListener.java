package com.hrym.utils;

import com.hrym.common.listener.ApplicationContextListener;
import com.hrym.rpc.auth.api.TaskService;
import com.hrym.rpc.auth.dao.mapper.TaskPlanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by mj on 2017/10/31.
 */
public class TimeListener implements ServletContextListener {

    private static Logger _log = LoggerFactory.getLogger(TimeListener.class);

    @Autowired
    private TaskPlanMapper taskPlanMapper;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        _log.info(">>>>> hrym-time-正在启动 <<<<<");
        ApplicationContext context =  new ClassPathXmlApplicationContext("classpath:spring/*.xml");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0); // 控制时
        calendar.set(Calendar.MINUTE, 0);    // 控制分
        calendar.set(Calendar.SECOND, 0);    // 控制秒

        Date time = calendar.getTime();     // 得出执行任务的时间,此处为今天的12：00：00
        //开启定时任务
        Timer timer = new Timer();
        //开始功课提醒，每60秒轮询一次
        timer.scheduleAtFixedRate((TaskTiming)context.getBean("taskTiming"), time, 1000 * 60 * 60 * 24);

        System.out.println(">>>>>>>>>>>>>>>>>>>>定时结束");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

        System.out.println(">>>>>>>>>>>>>>>>>>>>结束");
    }

}
