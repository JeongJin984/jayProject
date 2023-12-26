package com.jay.orderserver.common.dto

data class ClientOrderDTO(
    var productId: Long,
    var quantity: Int,
    var userOrderId: Long
) {
}