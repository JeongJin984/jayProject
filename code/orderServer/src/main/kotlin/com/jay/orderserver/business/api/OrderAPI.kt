package com.jay.orderserver.business.api

import com.jay.orderserver.business.service.OrderService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderAPI(
    val orderService: OrderService
) {
    @GetMapping("/order/all")
    fun orderAllOrderList(@RequestParam orderId: Long) {
        orderService.confirmOrder(1L)
    }
}