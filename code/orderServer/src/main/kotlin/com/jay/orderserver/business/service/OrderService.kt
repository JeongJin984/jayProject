package com.jay.orderserver.business.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.jay.orderserver.business.dto.OrderDTO
import com.jay.orderserver.domain.dto.ProductInfoDTO
import com.jay.orderserver.domain.entity.OrderList
import com.jay.orderserver.domain.entity.OrderState
import com.jay.orderserver.domain.entity.Product
import com.jay.orderserver.domain.entity.ProductOrder
import com.jay.orderserver.domain.repo.OrderListRepository
import com.jay.orderserver.domain.repo.ProductOrderRepository
import com.jay.orderserver.domain.repo.ProductRepository
import jakarta.transaction.TransactionSynchronizationRegistry
import jakarta.transaction.Transactional
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.clients.producer.ProducerRecord
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.annotation.PartitionOffset
import org.springframework.kafka.annotation.TopicPartition
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Service
import java.lang.Exception
import java.math.BigDecimal
import java.sql.Timestamp
import java.time.LocalDateTime
import java.util.*
import java.util.concurrent.TimeUnit

@Service
class OrderService(
    private val productRepository: ProductRepository,
    private val orderListRepository: OrderListRepository,
    private val productOrderRepository : ProductOrderRepository,
    private val kafkaTemplate: KafkaTemplate<String, String>,
    private val mapper : ObjectMapper
) {

    private val log = LoggerFactory.getLogger(OrderService::class.java)
    @Transactional
    @Throws(NoSuchElementException::class)
    fun confirmOrder(productOrderId : Long) {
        val productOrder : ProductOrder = productOrderRepository.findById(productOrderId).get()

        productOrder.confirm()

        try {
            kafkaTemplate.send(ProducerRecord("order", null, productOrderId.toString(), mapper.writeValueAsString(
                OrderDTO(productOrder.orderId.toString(), productOrder.ownerId.toString(), productOrder.totalAmounts)
            )))
        } catch (e : Exception) {
            log.error(e.message)
        }
    }

    @KafkaListener(
        id = "orderCancelListener",
        topicPartitions = [
            TopicPartition(topic = "order-cancel", partitions = ["0", "1"])
        ],
        autoStartup = "\${listen.auto.start:true}",
        concurrency = "\${listen.concurrency:2}",
        groupId = "transact_group"
    )
    @Transactional
    fun orderCancel(@Payload order : String) {
        val orderDTO = mapper.readValue(order, OrderDTO::class.java)
        val orderEntity = productOrderRepository.findById(orderDTO.orderId.toLong()).get()
        orderEntity.cancel()
    }

    @Transactional
    fun registerOrder(productId: Long, quantity: Int, productOrderId: Long) {
        val product = productRepository.findById(productId).get()
        val productOrder  = productOrderRepository.findById(productOrderId)

        if(productOrder.isEmpty) {
            val order = ProductOrder(null, 1, listOf(), product.price.multiply(BigDecimal(quantity)), OrderState.PREPARING, null)

            val orderList = OrderList(
                null,
                product,
                order,
                product.price,
                quantity,
                product.price.multiply(BigDecimal(quantity))
            )

            productOrderRepository.save(order)
            orderListRepository.save(orderList)
        } else {
            orderListRepository.save(OrderList(
                null,
                product,
                productOrder.get(),
                product.price,
                quantity,
                product.price.multiply(BigDecimal(quantity))
            ))
        }


    }

    @Transactional
    fun registerProduct(productInfo: ProductInfoDTO) {
        productRepository.save(
            ProductInfoDTO.Builder()
                .name(productInfo.name)
                .price(productInfo.price.toString())
                .description(productInfo.description)
                .build().buildProductEntity()
        )
    }
}