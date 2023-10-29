package com.jay.orderserver.domain.dto

import com.jay.orderserver.domain.entity.Product
import java.math.BigDecimal

class ProductInfoDTO private constructor (
    val name: String,
    val price: BigDecimal,
    val description: String
) {
    fun buildProductEntity(): Product {
        return Product(null, name, price, description)
    }
    data class Builder(
        var name: String? = null,
        var price: String? = null,
        var description: String? = null
    ) {
        fun name(name: String) = apply { this.name = name }
        fun price(price: String) = apply { this.price = price }
        fun description(description: String) = apply { this.description = description }

        fun build() = ProductInfoDTO(name!!, BigDecimal(price), description!!)
    }
}