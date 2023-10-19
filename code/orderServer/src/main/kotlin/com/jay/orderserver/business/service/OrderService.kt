package com.jay.orderserver.business.service

import com.jay.orderserver.domain.entity.ProductOrder
import com.jay.orderserver.domain.repo.ProductOrderRepository
import jakarta.transaction.Transactional
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.lang.Exception
import java.util.*

@Service
class OrderService(
    val productOrderRepository : ProductOrderRepository
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

        }catch (e : Exception) {
            log.error(e);
        }

    }
}