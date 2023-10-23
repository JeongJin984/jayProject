package com.jay.orderserver.business.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.jay.orderserver.domain.entity.ProductOrder
import com.jay.orderserver.domain.repo.ProductOrderRepository
import jakarta.transaction.Transactional
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.clients.producer.ProducerRecord
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.annotation.PartitionOffset
import org.springframework.kafka.annotation.TopicPartition
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Service
import java.lang.Exception
import java.sql.Timestamp
import java.time.LocalDateTime
import java.util.*
import java.util.concurrent.TimeUnit

@Service
class OrderService(
    val productOrderRepository : ProductOrderRepository,
    val replyTemplate: ReplyingKafkaTemplate<String,String,String>
    val mapper : ObjectMapper
) {

    private val log = LoggerFactory.getLogger(OrderService::class.java)
    @Transactional
    fun confirmOrder(orderId : Long) {
        val productOrderWrap : Optional<ProductOrder> = productOrderRepository.findById(orderId)

        if(productOrderWrap.isEmpty) {
            log.error("No Such Order / orderId : {}", orderId)
            throw IllegalArgumentException("No Such Order / orderId: $orderId")
        }

        val productOrder = productOrderWrap.get()
        productOrder.confirm()

        try {
            val future = replyTemplate.sendAndReceive(ProducerRecord("saleTopic", null, orderId.toString(), mapper.writeValueAsString(productOrder)))
            val response : ConsumerRecord<String, String> = future.get()
        } catch (e : Exception) {
            log.error(e.message)
        }
    }
}