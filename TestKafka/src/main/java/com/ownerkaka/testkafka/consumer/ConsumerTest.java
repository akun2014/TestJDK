package com.ownerkaka.testkafka.consumer;

import com.google.common.collect.Lists;
import com.ownerkaka.testcommon.model.User;
import com.ownerkaka.testkafka.KafkaConfig;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @Author akun
 * @Date 2021/9/4 23:19
 */
@Slf4j
public class ConsumerTest {

    @SneakyThrows
    public static void main(String[] args) {
        Properties properties = KafkaConfig.getKafkaProperties();
        KafkaConsumer<String, User> consumer = new KafkaConsumer<>(properties);

        consumer.subscribe(Collections.singleton(properties.getProperty("topic")), new HandlerRebalance(consumer));

        //从指定偏移量处理记录
        for (TopicPartition topicPartition : consumer.assignment()) {
            consumer.seek(topicPartition, 10);
        }

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                log.info("staring exit...");
                consumer.wakeup();
            }
        });

        try {
            while (true) {
                ConsumerRecords<String, User> records = consumer.poll(Duration.ofMillis(500));

                for (ConsumerRecord<String, User> record : records) {
                    log.info("records:{}", record);
                }
                //异步提交
                consumer.commitAsync();
            }
        } catch (Exception e) {
            log.error("", e);
        } finally {
            try {
                //同步提交
                consumer.commitSync();
            } finally {
                consumer.close();
            }
        }


    }
}
