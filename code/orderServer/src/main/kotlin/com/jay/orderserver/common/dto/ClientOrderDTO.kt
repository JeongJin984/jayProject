package com.jay.orderserver.common.dto

import com.jay.orderserver.domain.entity.ProductOrder

data class ClientOrderDTO(
    var productId: Long,
    var quantity: Int,
    var userOrderId: Long
) {
}