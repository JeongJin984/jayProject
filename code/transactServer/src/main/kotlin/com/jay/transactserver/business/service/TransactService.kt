package com.jay.transactserver.business.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.jay.transactserver.business.dto.OrderDTO
import com.jay.transactserver.data.entity.TransType
import com.jay.transactserver.data.entity.Transact
import com.jay.transactserver.data.entity.TransactStatus
import com.jay.transactserver.data.repo.TransactRepository
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.annotation.PartitionOffset
import org.springframework.kafka.annotation.TopicPartition
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.listener.KafkaListenerErrorHandler
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.Message
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDateTime

@Service
class TransactService(
    val kafkaTemplate: KafkaTemplate<String, String>,
    val transactRepository: TransactRepository,
    val mapper: ObjectMapper
) {
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
    fun productSold(@Payload order : String) {
        val orderDTO = mapper.readValue(order, OrderDTO::class.java)
        if(orderDTO.totalAmount.toDouble() < 0) {
            kafkaTemplate.send("order-cancel", order)
        } else {
            transactRepository.save(Transact(null, LocalDateTime.now(), LocalDateTime.now(), TransactStatus.SALE, "test", "1111", BigDecimal(1),BigDecimal(2), BigDecimal(3), TransType.BUY))
        }
    }
}