package com.jay.transactserver.business.dto

import java.math.BigDecimal

class OrderDTO(
    var orderId: String,
    var ownerId: String,
    var totalAmount: BigDecimal
) {
}