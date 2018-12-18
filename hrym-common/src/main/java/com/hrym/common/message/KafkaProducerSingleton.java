package com.hrym.common.message;

import net.sf.json.JSONObject;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * Created by hong on 2017/9/5.
 */
public class KafkaProducerSingleton {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(KafkaProducerSingleton.class);

    private KafkaProducerSingleton(){}

    public static KafkaProducerSingleton getInstance() {
        return Single.INSTANCE.getInstance();
    }

    /**
     * 通过枚举实现单例
     */
    private static enum Single{

        INSTANCE;

        KafkaProducerSingleton instance;
        private Single() {
            instance =  new KafkaProducerSingleton();
        }

        public KafkaProducerSingleton getInstance() {
            return instance;
        }
    }

    private String topic;

    private int retry = 3;

    private static KafkaProducer<String, String> kafkaProducer;


    /**
     * kafka生产者进行初始化
     *
     * @return KafkaProducer
     */
    public void init() {
        if (null == kafkaProducer) {
            Properties props = new Properties();

            InputStream inStream = null;
            try {
                inStream = classLoader().getResourceAsStream("kafka.properties");
                props.load(inStream);
                kafkaProducer = new KafkaProducer<String, String>(props);
            } catch (IOException e) {
                LOGGER.error("kafkaProducer初始化失败:" + e.getMessage(), e);
            } finally {
                if (null != inStream) {
                    try {
                        inStream.close();
                    } catch (IOException e) {
                        LOGGER.error("kafkaProducer初始化失败:" + e.getMessage(), e);
                    }
                }
            }
        }
    }

    /**
     * 通过kafkaProducer发送消息
     *
     * @param topic
     * @param messageBean
     */
    public void sendKafkaMessage(final String topic, final MessageBean messageBean){
        String msg = JSONObject.fromObject(messageBean).toString();
        this.sendKafkaMessage(topic,msg);
    }

    public void sendKafkaMessage(final String topic, final RequestLog messageBean){
        String msg = JSONObject.fromObject(messageBean).toString();
        this.sendKafkaMessage(topic,msg);
    }

    /**
     * 通过kafkaProducer发送消息
     *
     * @param topic
     *            消息接收主题
     * @param message
     *            消息
     * @param message
     *            具体消息值
     */
    public void sendKafkaMessage(final String topic, final String message) {

        if(kafkaProducer==null) {

            // 初始化kafka
            this.init();
        }
        /**
         * 1、如果指定了某个分区,会只讲消息发到这个分区上 2、如果同时指定了某个分区和key,则也会将消息发送到指定分区上,key不起作用
         * 3、如果没有指定分区和key,那么将会随机发送到topic的分区中 4、如果指定了key,那么将会以hash<key>的方式发送到分区中
         */
        ProducerRecord<String, String> record = new ProducerRecord<String, String>(
                topic, message);
        // send方法是异步的,添加消息到缓存区等待发送,并立即返回，这使生产者通过批量发送消息来提高效率
        // kafka生产者是线程安全的,可以单实例发送消息
        kafkaProducer.send(record, new Callback() {
            public void onCompletion(RecordMetadata recordMetadata,
                                     Exception exception) {
                if (null != exception) {
                    LOGGER.error("kafka发送消息失败:" + exception.getMessage(),
                            exception);
                    retryKakfaMessage(topic,message);
                }
            }
        });

        kafkaProducer.flush();
    }

    /**
     * 当kafka消息发送失败后,重试
     *
     * @param retryMessage
     */
    private void retryKakfaMessage(final String topic, final String retryMessage) {
        ProducerRecord<String, String> record = new ProducerRecord<String, String>(
                topic, this.retry, "", retryMessage);
        for (int i = 1; i <= retry; i++) {
            try {
                kafkaProducer.send(record);
                break;
            } catch (Exception e) {
                LOGGER.error("kafka发送消息失败:" + e.getMessage(), e);
                //todu 本地化保存
                //retryKakfaMessage(retryMessage);
            }
        }
    }

    /**
     * kafka实例销毁
     */
    public void close() {
        if (null != kafkaProducer) {
            kafkaProducer.close();
        }
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getRetry() {
        return retry;
    }

    public void setRetry(int retry) {
        this.retry = retry;
    }

    public static ClassLoader classLoader() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        if (loader == null) {
            loader = ClassLoader.getSystemClassLoader();
        }
        return loader;
    }

    public static void main(String[] args){

        MessageBean bean = new MessageBean();
        //bean赋值
        bean.setTitle("sss");
        KafkaProducerSingleton.getInstance().sendKafkaMessage(MessageConstant.TOPIC_HRYM_MESSAGE,bean);
    }

}
