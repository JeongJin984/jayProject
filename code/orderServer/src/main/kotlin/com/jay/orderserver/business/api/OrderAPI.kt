package com.jay.orderserver.business.api

import com.jay.orderserver.business.dto.ProductDTO
import com.jay.orderserver.business.service.OrderService
import com.jay.orderserver.domain.dto.ProductInfoDTO
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderAPI(
    val orderService: OrderService
) {
    @GetMapping("/order/confirm")
    fun orderAllOrderList(@RequestParam orderId: Long) {
        orderService.confirmOrder(orderId)
    }

    @PostMapping("/order/register")
    fun registerOrder(
        @RequestParam productId: Long,
        @RequestParam quantity: Int,
        @RequestParam orderId: Long
    ) {
        orderService.registerOrder(productId, quantity, orderId)
    }

    @PostMapping("/product/register")
    fun registerProduct(
        @RequestBody productDTO: ProductDTO
    ) {
        orderService.registerProduct(
            ProductInfoDTO.Builder()
                .name(productDTO.name)
                .price(productDTO.price)
                .description(productDTO.description)
                .build()
        )
    }
}