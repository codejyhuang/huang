package com.hrym.message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hrym.common.message.MessageBean;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Properties;

/**
 * Created by mj on 2018/4/11.
 */
public class KafkaUtils {

    private final static Logger LOGGER = LoggerFactory.getLogger(KafkaUtils.class);

    private static Consumer<String, String> consumer;

    private KafkaUtils() {

    }

    /**
     * 消费者
     */
    static {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.1.210:9092");//服务器ip:端口号，集群用逗号分隔
        props.put("group.id", "hrym-log");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put( "key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("hrym-log"));
    }

    /**
     * 从kafka上接收对象消息，将json字符串转化为对象，便于获取消息的时候可以使用get方法获取。
     */
    public static void getMsgFromKafka(){
        while(true){
            ConsumerRecords<String, String> records = KafkaUtils.getKafkaConsumer().poll(100);
            if (records.count() > 0) {
                for (ConsumerRecord<String, String> record : records) {
                    JSONObject jsonAlarmMsg = JSON.parseObject(record.value());
                    MessageBean alarmMsg = JSONObject.toJavaObject(jsonAlarmMsg, MessageBean.class);
                    LOGGER.info("从kafka接收到的消息是：" + alarmMsg.toString());
                }
            }
        }
    }


    public static Consumer<String, String> getKafkaConsumer() {
        return consumer;
    }


    public static void closeKafkaConsumer() {
        consumer.close();
    }


    public static void main(String[] args) {
        getMsgFromKafka();
    }
}
