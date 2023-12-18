package com.jay.orderserver.business.api

import com.jay.orderserver.business.service.OrderService
import com.jay.orderserver.common.dto.ClientOrderDTO
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/order")
class OrderAPI(
    val orderService: OrderService
) {
    @PostMapping("/confirm")
    fun orderAllOrderList(@RequestParam orderId: Long) {
        orderService.confirmOrder(1L)
    }

    @PostMapping("/register")
    fun registerOrder(@RequestBody clientOrder: ClientOrderDTO) {
        orderService.registerOrder(clientOrder)
    }


}