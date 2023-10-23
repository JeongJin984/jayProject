package com.jay.transactserver.business.service

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.annotation.PartitionOffset
import org.springframework.kafka.annotation.TopicPartition
import org.springframework.stereotype.Service

@Service
class TransactService {
    @KafkaListener(
        id = "saleListener",
        topicPartitions = [
            TopicPartition(topic = "saleTopic", partitions = ["0"], partitionOffsets = [PartitionOffset(partition = "1", initialOffset = "0")])
        ],
        autoStartup = "\${listen.auto.start:true}",
        concurrency = "\${listen.concurrency:3}"
    )
    fun productSold(record : ConsumerRecord<Any, Any>) {

    }
}