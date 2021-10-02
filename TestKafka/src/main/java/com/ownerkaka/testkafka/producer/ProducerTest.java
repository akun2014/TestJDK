package com.ownerkaka.testkafka.producer;

import com.ownerkaka.testkafka.KafkaConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

@Slf4j
public class ProducerTest {

    public static void main(String[] args) {
        Properties config = getConfig();
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(config);
        ProducerRecord<String, String> record = new ProducerRecord<String, String>(config.getProperty("topic"), "test");
        record.topic();

        producer.send(record, (metadata, exception) -> {
            log.info("topic={}", metadata.topic());
        });

    }

    private static Properties getConfig() {
        Properties properties = KafkaConfig.getKafkaProperties();

        return properties;
    }
}
