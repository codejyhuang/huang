package com.hrym.quartzUtils;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * 定时器管理器
 * Created by mj on 2018/5/3.
 */
public class QuartzManager {

    public static void addJob(Scheduler sched, String jobName, @SuppressWarnings("rawtypes") Class cls, String time) {

        try {
            //定义一个Trigger触发器
            Trigger trigger = newTrigger()
                    .withIdentity("triggerName", "triggerGroupName") //定义name/group
                    .startNow()                                     //一旦加入scheduler，立即生效
                    .withSchedule(simpleSchedule()                  //使用SimpleTrigger
                            .withIntervalInSeconds(10)                   //每隔一秒执行一次
                            .repeatForever())                           //一直执行，奔腾到老不停歇
                    .build();

            //定义一个JobDetail
            JobDetail job = newJob(cls) //定义Job类为HelloQuartz类，这是真正的执行逻辑所在
                    .withIdentity("jobName", "jobGroupName") //定义 任务名/任务组
                    .usingJobData("name", "quartz") //定义属性
                    .build();

            //加入这个调度
            sched.scheduleJob(job, trigger);
            // 启动
            if (!sched.isShutdown()) {
                sched.start();
            }
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }
}
