package com.jay.transactserver.business.service

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.annotation.PartitionOffset
import org.springframework.kafka.annotation.TopicPartition
import org.springframework.kafka.listener.KafkaListenerErrorHandler
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.Message
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDateTime

@Service
class TransactService {
    private val log  = LoggerFactory.getLogger("TransactService")

    @KafkaListener(
        id = "saleListener",
        topicPartitions = [
            TopicPartition(topic = "healthcheck", partitions = ["0"], partitionOffsets = [PartitionOffset(partition = "1", initialOffset = "0")])
        ],
        autoStartup = "\${listen.auto.start:true}",
        concurrency = "\${listen.concurrency:2}",
        groupId = "transact_group"
    )
    @SendTo
    fun productSold(@Payload order : String) : Message<String> {
        println(order)
        return MessageBuilder
            .withPayload("success")
            .setHeader(KafkaHeaders.KEY, "key")
            .build()
    }


}