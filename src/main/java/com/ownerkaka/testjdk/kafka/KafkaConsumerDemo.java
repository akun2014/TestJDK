package com.ownerkaka.testjdk.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

@Slf4j
public class KafkaConsumerDemo {


    public static void main(String[] args) {
        Properties config = getConfig();
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(config);

        consumer.subscribe(Collections.singleton(config.getProperty("topic")));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMinutes(1));


            for (ConsumerRecord<String, String> record : records) {
                log.info("records:{}", record);
            }
        }
    }

    private static Properties getConfig() {
        Properties properties = JavaKafkaConfigurer.getKafkaProperties();

        return properties;
    }
}
