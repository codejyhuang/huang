package com.hrym;

import com.hrym.quartzUtils.QuartzManager;
import com.hrym.task.QuartzJobExample;
import org.quartz.Scheduler;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * Created by mj on 2018/5/3.
 */
@EnableScheduling
public class QuartzApplication {

    private static org.slf4j.Logger _log = LoggerFactory.getLogger(QuartzApplication.class);

    public static void main(String[] args) {

        _log.info(">>>>> Quartz定时器 正在启动 <<<<<");
        ApplicationContext context =  new ClassPathXmlApplicationContext("classpath:spring/*.xml");

        //创建scheduler
        Scheduler scheduler = (Scheduler)context.getBean("schedulerFactoryBean");
        QuartzJobExample quartzJobExample = (QuartzJobExample)context.getBean("quartzJobExample");

        String jobName = "动态任务调度";

        QuartzManager.addJob(scheduler,jobName, quartzJobExample.getClass(), null);

        _log.info(">>>>> Quartz定时器 启动完成 <<<<<");
    }
}
