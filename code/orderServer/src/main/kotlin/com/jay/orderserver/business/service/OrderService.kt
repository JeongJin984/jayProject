package com.jay.orderserver.business.service

import com.jay.orderserver.domain.repo.ProductOrderRepository
import org.springframework.stereotype.Service

@Service
class OrderService(
    val productOrderRepository: ProductOrderRepository
) {
    fun getOrder() {

    }
}