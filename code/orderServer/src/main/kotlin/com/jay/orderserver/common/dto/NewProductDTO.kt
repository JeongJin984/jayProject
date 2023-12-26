package com.jay.orderserver.common.dto

import java.math.BigDecimal

data class NewProductDTO(
    var name: String,
    var price: BigDecimal,
    var description: String
) {
}