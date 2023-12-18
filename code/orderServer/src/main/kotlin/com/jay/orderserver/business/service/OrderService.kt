package com.jay.orderserver.business.service

import ch.qos.logback.core.net.server.Client
import com.fasterxml.jackson.databind.ObjectMapper
import com.jay.orderserver.common.dto.ClientOrderDTO
import com.jay.orderserver.domain.dto.ProductInfoDTO
import com.jay.orderserver.domain.entity.ProductOrder
import com.jay.orderserver.domain.entity.UserOrder
import com.jay.orderserver.domain.entity.createNewProductOrder
import com.jay.orderserver.domain.repo.ProductOrderRepository
import com.jay.orderserver.domain.repo.UserOrderRepository
import com.jay.orderserver.domain.repo.ProductRepository
import jakarta.transaction.Transactional
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.clients.producer.ProducerRecord
import org.slf4j.LoggerFactory
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate
import org.springframework.stereotype.Service
import java.lang.Exception
import java.util.*

@Service
class OrderService(
    private val productRepository: ProductRepository,
    private val productOrderRepository: ProductOrderRepository,
    private val userOrderRepository : UserOrderRepository,
    private val replyTemplate: ReplyingKafkaTemplate<String,String,String>,
    private val mapper : ObjectMapper
) {

    private val log = LoggerFactory.getLogger(OrderService::class.java)
    @Transactional
    @Throws(NoSuchElementException::class)
    fun confirmOrder(productOrderId : Long) {
        val userOrder : UserOrder = userOrderRepository.findById(productOrderId).get()

        userOrder.confirm()

        try {
            val future = replyTemplate.sendAndReceive(ProducerRecord("order", null, productOrderId.toString(), "asdf"))
            val response : ConsumerRecord<String, String> = future.get()
            println(response.value())
        } catch (e : Exception) {
            log.error(e.message)
        }
    }


    @Transactional
    fun orderCancel(orderId : String) {
        val order = userOrderRepository.findById(orderId.toLong()).get()
        order.cancel()
    }

    @Transactional
    fun registerOrder(clientOrder: ClientOrderDTO) {
        val product = productRepository.findById(clientOrder.productId)
        val productOrder  = userOrderRepository.findById(clientOrder.userOrderId)

        productOrderRepository.save(createNewProductOrder(product.get(), productOrder.get()))
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