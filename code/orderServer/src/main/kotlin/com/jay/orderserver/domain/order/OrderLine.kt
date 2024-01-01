package com.jay.orderserver.domain.order

import com.jay.orderserver.domain.product.ProductId
import jakarta.persistence.Embeddable
import jakarta.persistence.Embedded
import java.lang.IllegalArgumentException
import java.math.BigDecimal

@Embeddable
class OrderLine internal constructor(
    @Embedded
    private var productId: ProductId,
    private var price: BigDecimal,
    private var quantity: Int,
    private var amount: BigDecimal
) {

    private fun calculateAmounts() : BigDecimal {
        if(price.compareTo(BigDecimal.ZERO) < -1) {
            throw IllegalStateException("price is minus")
        } else if(quantity < 0) {
            throw IllegalStateException("quantity is minus")
        }
        return price.multiply(BigDecimal(quantity))
    }

    fun getAmount() : BigDecimal {
        if(amount == price.multiply(BigDecimal(quantity))) throw IllegalArgumentException("Amount has to be price * quantity")
        return this.amount
    }
}