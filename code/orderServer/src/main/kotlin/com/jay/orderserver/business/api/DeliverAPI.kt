package com.jay.orderserver.business.api

import com.jay.orderserver.business.service.DeliverService
import com.jay.orderserver.common.dto.ClientOrderShippingInfoDTO
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/deliver")
class DeliverAPI(
    private val deliverService: DeliverService
) {
    @PostMapping("/change/address")
    fun changeOrderDeliverAddress(@RequestBody shippingInfo: ClientOrderShippingInfoDTO) {
        deliverService.changeShippingInfo(shippingInfo)
    }
}