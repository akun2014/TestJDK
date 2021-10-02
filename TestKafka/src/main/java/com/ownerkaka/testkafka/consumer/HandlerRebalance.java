package com.ownerkaka.testkafka.consumer;

import com.ownerkaka.testcommon.model.User;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.common.TopicPartition;

import java.util.Collection;

/**
 * @Author akun
 * @Date 2021/9/4 23:28
 */
public class HandlerRebalance implements ConsumerRebalanceListener {

    Consumer<String, User> consumer;

    public HandlerRebalance(Consumer<String, User> consumer) {
        this.consumer = consumer;
    }

    // save the offsets in an external store using some custom code not described here
    @Override
    public void onPartitionsRevoked(Collection<TopicPartition> partitions) {

        for (TopicPartition partition : partitions) {
            int partition1 = partition.partition();
            String topic = partition.topic();
//            consumer.commitSync();

        }

    }

    // read the offsets from an external store using some custom code not described here
    @Override
    public void onPartitionsAssigned(Collection<TopicPartition> partitions) {

        for (TopicPartition partition : partitions) {

        }
    }
}
