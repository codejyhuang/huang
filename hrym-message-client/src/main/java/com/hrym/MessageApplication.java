package com.hrym;

import com.hrym.message.KafkaMultiProcessorMain;
import com.hrym.task.WorkRemainTask;
import com.hrym.task.WorkRequestLog;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Timer;

/**
 * Created by hong on 2017/10/19.
 */
public class MessageApplication {

    private static org.slf4j.Logger _log = LoggerFactory.getLogger(MessageApplication.class);

    public static void main(String[] args) {
        _log.info(">>>>> hrym-message-client 正在启动 <<<<<");
        ApplicationContext context =  new ClassPathXmlApplicationContext("classpath:spring/*.xml");

        WorkRequestLog workRequestLog = (WorkRequestLog)context.getBean("workRequestLog");

        KafkaMultiProcessorMain kafkaMultiProcessor = new KafkaMultiProcessorMain();
        kafkaMultiProcessor.init(workRequestLog);


        //开启定时任务
        Timer timer = new Timer();
        //开始功课提醒，每60秒轮询一次
        timer.scheduleAtFixedRate((WorkRemainTask)context.getBean("workRemainTask"), 1000, 60000);

        _log.info(">>>>> hrym-message-client 启动完成 <<<<<");
    }
}
