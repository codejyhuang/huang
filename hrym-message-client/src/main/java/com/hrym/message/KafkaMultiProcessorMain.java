package com.hrym.message;

import com.hrym.common.util.PropertiesFileUtil;
import com.hrym.task.WorkRequestLog;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by hong on 2017/10/9.
 */
public class KafkaMultiProcessorMain {
    private static final Logger logger = LoggerFactory.getLogger(KafkaMultiProcessorMain.class);
    //订阅的topic
    private String alarmTopic;
    //brokers地址
    private String servers;
    //消费group
    private String group;
    //kafka消费者配置
    private Properties consumerConfig;
    private Thread[] threads;
    //保存处理任务和线程的map
    private ConcurrentHashMap<TopicPartition, RecordProcessor> recordProcessorTasks = new ConcurrentHashMap<>();
    private ConcurrentHashMap<TopicPartition, Thread> recordProcessorThreads = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        KafkaMultiProcessorMain test = new KafkaMultiProcessorMain();
        //....省略设置topic和group的代码
//        test.init();
    }

    public void init(WorkRequestLog o) {
        consumerConfig = getConsumerProperties();
        logger.debug("get kafka consumerConfig: " + consumerConfig.toString());
        //创建threadsNum个线程用于读取kafka消息, 且位于同一个group中, 这个topic有12个分区, 最多12个consumer进行读取

        PropertiesFileUtil util = PropertiesFileUtil.getInstance("kafka");
        int threadsNum = util.getInt("thread.num");
        logger.debug("create " + threadsNum + " threads to consume kafka warn msg");
        threads = new Thread[threadsNum];
        alarmTopic=util.get("msg.topic");
        for (int i = 0; i < threadsNum; i++) {
            MsgReceiver msgReceiver = new MsgReceiver(consumerConfig, alarmTopic, recordProcessorTasks, recordProcessorThreads,o);
            Thread thread = new Thread(msgReceiver);
            threads[i] = thread;
            thread.setName("alarm msg consumer " + i);
        }
        //启动这几个线程
        for (int i = 0; i < threadsNum; i++) {
            threads[i].start();
        }
        logger.debug("finish creating" + threadsNum + " threads to consume kafka warn msg");
    }


    //销毁启动的线程
    public void destroy() {
        closeRecordProcessThreads();
        closeKafkaConsumer();
    }

    private void closeRecordProcessThreads() {
        logger.debug("start to interrupt record process threads");
        for (Map.Entry<TopicPartition, Thread> entry : recordProcessorThreads.entrySet()) {
            Thread thread = entry.getValue();
            thread.interrupt();
        }
        logger.debug("finish interrupting record process threads");
    }

    private void closeKafkaConsumer() {
        logger.debug("start to interrupt kafka consumer threads");
        //使用interrupt中断线程, 在线程的执行方法中已经设置了响应中断信号
        for (int i = 0; i < threads.length; i++) {
            threads[i].interrupt();
        }
        logger.debug("finish interrupting consumer threads");
    }

    public static ClassLoader classLoader() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        if (loader == null) {
            loader = ClassLoader.getSystemClassLoader();
        }
        return loader;
    }

    /**
     * kafka生产者进行初始化
     *
     * @return KafkaProducer
     */
    public Properties getConsumerProperties() {
        Properties props = new Properties();

        InputStream inStream = null;
        try {
            inStream = classLoader().getResourceAsStream("kafka.properties");
            props.load(inStream);

        } catch (IOException e) {
            logger.error("kafkaProducer初始化失败:" + e.getMessage(), e);
        } finally {
            if (null != inStream) {
                try {
                    inStream.close();
                } catch (IOException e) {
                    logger.error("kafkaProducer初始化失败:" + e.getMessage(), e);
                }
            }
        }
        props.put("group.id","group.id111");
        return props;
    }

    public void setAlarmTopic(String alarmTopic) {
        this.alarmTopic = alarmTopic;
    }

    public void setServers(String servers) {
        this.servers = servers;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
