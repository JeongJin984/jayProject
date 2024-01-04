package com.jay.orderserver.domain.entity

import jakarta.persistence.Access
import jakarta.persistence.AccessType
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.lang.IllegalArgumentException
import java.math.BigDecimal

@Embeddable
class OrderLine internal constructor(
    private var productId: String,
    private var price: BigDecimal,
    private var quantity: Int,
    @Column(name = "amount")
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