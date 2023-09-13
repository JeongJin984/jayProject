package com.jay.orderserver.domain.entity

import jakarta.persistence.*

@Entity
@Table(name = "order_line")
class OrderLine (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var orderLineId: Long? = null,

    @ManyToOne(targetEntity = Product::class)
    @JoinColumn(name = "product_id")
    var product: Product? = null,

    @ManyToOne
    @JoinColumn(name = "product_order_id")
    var productOrder: ProductOrder? = null,

    @Column(name = "price")
    var price: Long? = null,

    @Column(name = "quantity")
    var quantity: Int? = null,

    @Column(name = "amount")
    var amount: Int? = null
) {
    private fun calculateAmounts() : Long {
        return price!! * quantity!!
    }

}