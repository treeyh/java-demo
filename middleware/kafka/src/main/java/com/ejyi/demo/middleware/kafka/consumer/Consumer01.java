package com.ejyi.demo.middleware.kafka.consumer;

import org.apache.kafka.clients.consumer.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Consumer01 {

    private static void consumerHighApi(){
        Properties properties = new Properties();
        //
        properties.put("bootstrap.servers", "10.157.42.181:9092,10.157.42.182:9092,10.157.42.183:9092");
        //分组id
        properties.put("group.id", "group-1");
        properties.put("enable.auto.commit", "true");
        //自动确认 offset 的时间间隔
        properties.put("auto.commit.interval.ms", "1000");

        properties.put("auto.offset.reset", "earliest");
        //session超时时间
        properties.put("session.timeout.ms", "30000");
        //key序列化策略
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        //value序列化策略
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        ConsumerConfig conf = new ConsumerConfig(properties);

        Map<String, Integer> topicCountMap = new HashMap<>();
        topicCountMap.put("tree", 3);

//        ConsumerConnector connector = Consumer.createJavaConsumerConnector(config);

    }


    public static void main(String[] args){
        Properties properties = new Properties();
        //
        properties.put("bootstrap.servers", "10.157.42.181:9092,10.157.42.182:9092,10.157.42.183:9092");
        //分组id
        properties.put("group.id", "group-1");
        properties.put("enable.auto.commit", "true");
        //自动确认 offset 的时间间隔
        properties.put("auto.commit.interval.ms", "1000");

        properties.put("auto.offset.reset", "earliest");
        //session超时时间
        properties.put("session.timeout.ms", "30000");
        //key序列化策略
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        //value序列化策略
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);
        kafkaConsumer.subscribe(Arrays.asList("tree"));
        System.out.println(kafkaConsumer.partitionsFor("tree").size());
        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("offset = %d, partition = %d, key = %s, value = %s", record.offset(), record.partition(), record.key(), record.value());
                System.out.println();
            }

        }

    }
}
