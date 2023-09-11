package com.jay.orderserver.business.api

import com.jay.orderserver.business.service.OrderService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderAPI(
    val orderService: OrderService
) {
    @PostMapping("/order/all")
    fun orderAllOrderList(@RequestParam orderListId: Long) {

    }
}