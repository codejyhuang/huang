package com;

import com.hrym.common.message.KafkaProducerSingleton;
import com.hrym.common.message.MessageConstant;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Arrays;
import java.util.Properties;

/**
 * Created by hong on 2017/9/6.
 */
public class Test {
    public static void main(String[] args) {



//        KafkaProducerSingleton.getInstance().sendKafkaMessage("hrym-message-01","111111111testqwqwqwqwq");
    }
}

class Mess{


    private String name;
    private int age;

    @Override
    public String toString() {
        return "Mess{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
