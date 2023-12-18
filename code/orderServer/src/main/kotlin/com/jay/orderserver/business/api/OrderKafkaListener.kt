package com.jay.orderserver.business.api

import com.jay.orderserver.business.service.OrderService
import jakarta.transaction.Transactional
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.annotation.PartitionOffset
import org.springframework.kafka.annotation.TopicPartition
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Service

@Service
class OrderKafkaListener(
    val orderService: OrderService
) {
    @KafkaListener(
        id = "orderCancelListener",
        topicPartitions = [
            TopicPartition(topic = "order-cancel", partitions = ["0"], partitionOffsets = [PartitionOffset(partition = "1", initialOffset = "0")])
        ],
        autoStartup = "\${listen.auto.start:true}",
        concurrency = "\${listen.concurrency:2}",
        groupId = "transact_group"
    )
    fun orderCancel(@Payload orderId : String) {
        orderService.orderCancel(orderId)
    }
}