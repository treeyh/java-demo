package com.ejyi.demo.middleware.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.Future;

public class Producer01 {


    private static Producer<String, String> getKafkaProducer(){


        Properties properties = new Properties();
        properties.put("bootstrap.servers", "10.157.42.181:9092,10.157.42.182:9092,10.157.42.183:9092");
        properties.put("acks", "all");
        properties.put("retries", 0);
        properties.put("batch.size", 16384);
        properties.put("linger.ms", 1);
        properties.put("buffer.memory", 33554432);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = null;
        try {
            producer = new KafkaProducer<String, String>(properties);
            return producer;
        } catch (Exception e) {
            e.printStackTrace();

        } finally {

        }
        return null;
    }


    public static void main(String[] args){
        Producer<String, String> producer = getKafkaProducer();
        try {
            for (int i = 0; i < 100; i++) {
                String msg = "Message " + i;
                Future<RecordMetadata> future = producer.send(new ProducerRecord<String, String>("tree", "tree_"+i, msg));
                RecordMetadata recordMetadata = future.get();
                System.out.println("Sent:" + msg+"; offset:"+recordMetadata.offset()+"; partition:"+recordMetadata.partition());
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            producer.close();
        }

    }
}
