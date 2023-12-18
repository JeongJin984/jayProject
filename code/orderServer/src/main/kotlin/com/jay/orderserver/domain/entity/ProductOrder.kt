package com.jay.orderserver.domain.entity

import com.jay.orderserver.common.dto.ClientOrderDTO
import jakarta.persistence.*
import java.lang.IllegalArgumentException
import java.math.BigDecimal

@Entity(name = "OrderEntity")
@Table(name = "order_list")
class ProductOrder (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private var orderLineId: Long? = null,

    @ManyToOne(targetEntity = Product::class)
    @JoinColumn(name = "product_id")
    private var product: Product? = null,

    @ManyToOne
    @JoinColumn(name = "product_order_id")
    private var userOrder: UserOrder? = null,

    @Column(name = "price")
    private var price: BigDecimal = BigDecimal(-1),

    @Column(name = "quantity")
    private var quantity: Int = -1,

    @Column(name = "amount")
    private var amount: BigDecimal = BigDecimal(-1)
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

fun createNewProductOrder(product: Product, userOrder: UserOrder) : ProductOrder {
    return ProductOrder(
        null,
        product,
        userOrder,
        product.price
    )
}