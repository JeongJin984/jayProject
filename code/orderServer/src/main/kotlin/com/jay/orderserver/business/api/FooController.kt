package com.jay.orderserver.business.api

import com.jay.orderserver.business.service.OrderService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class FooController(
    val orderService: OrderService
) {
//    @GetMapping("/foo")
//    fun foo() : String {
//        return orderService.foo()
//    }
}