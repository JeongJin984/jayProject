package com.jay.orderserver.common.dto

import com.jay.orderserver.domain.entity.ProductOrder
import jakarta.persistence.Column
import java.math.BigDecimal

data class NewProductDTO(
    var name: String,
    var price: BigDecimal,
    var description: String
) {
}