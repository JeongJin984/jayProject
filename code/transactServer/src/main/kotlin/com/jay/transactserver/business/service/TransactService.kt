package com.jay.transactserver.business.service

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
import org.springframework.kafka.listener.KafkaListenerErrorHandler
import org.springframework.messaging.Message
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDateTime

@Service
class TransactService (
    val transactRepository: TransactRepository
){
    private val log  = LoggerFactory.getLogger("TransactService")

    @KafkaListener(
        id = "saleListener",
        topicPartitions = [
            TopicPartition(topic = "saleTopic", partitions = ["0"], partitionOffsets = [PartitionOffset(partition = "1", initialOffset = "0")])
        ],
        autoStartup = "\${listen.auto.start:true}",
        concurrency = "\${listen.concurrency:3}",
        groupId = "transact_group",
        errorHandler = "validationErrorHandler"
    )
    fun productSold(@Payload order : String) : Message<String> {
        return if(order.contains("s")) {
            transactRepository.save(Transact("a", LocalDateTime.now(), LocalDateTime.now(), TransactStatus.SALE, "test", "11111", BigDecimal(1.4), BigDecimal(1.4), BigDecimal(1.4), TransType.BUY))
            MessageBuilder.withPayload("success").build()
        } else {
            MessageBuilder.withPayload("error").build()
        }
    }

    @Bean
    fun validationErrorHandler():KafkaListenerErrorHandler {
        return KafkaListenerErrorHandler { message, exception ->
            log.error("validation error handler message=${message.payload} exception groupId=${exception.groupId} exception cause=${exception.rootCause}")
            message
        }
    }
}