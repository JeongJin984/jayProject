package com.jay.orderserver.domain.entity

import jakarta.persistence.*
import java.lang.IllegalArgumentException

@Entity
@Table(name = "product_order")
class ProductOrder (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    var orderId: Long? = null,

    @OneToMany(mappedBy = "productOrder", fetch = FetchType.LAZY)
    var orderLists: List<OrderList> = listOf(),

    @Column(name = "total_amounts")
    var totalAmounts: Int? = null,

    @Column(name = "order_state")
    var orderState: OrderState? = null,

    @ManyToOne(targetEntity = ShippingInfo::class, fetch = FetchType.LAZY)
    @JoinColumn(name = "shipping_info_id")
    var shippingInfo: ShippingInfo? = null
) {
    private fun setOrderLines(orderLists: List<OrderList>) {
        verifyAtLeastOneOrMoreOrderLines(orderLists)
        this.orderLists = orderLists
        calculateTotalAmounts()
    }

    private fun verifyAtLeastOneOrMoreOrderLines(orderLists: List<OrderList>?) {
        if(orderLists.isNullOrEmpty()) {
            throw IllegalArgumentException("no OrderLines")
        }
    }

    private fun calculateTotalAmounts() : Int? {
        this.totalAmounts = orderLists.sumOf { it.amount!! }
        return this.totalAmounts
    }

    fun changeShippingInfo(shippingInfo: ShippingInfo): Boolean {
        return orderState == OrderState.PAYMENT_WAITING || orderState == OrderState.PREPARING
    }

    fun cancel() {
        verifyNotYetShipped()
        this.orderState = OrderState.CANCELED
    }

    private fun verifyNotYetShipped() {
        if(orderState != OrderState.PAYMENT_WAITING && orderState != OrderState.PREPARING)
            throw IllegalArgumentException("already shipped")
    }
}

enum class OrderState {
    PAYMENT_WAITING, PREPARING, SHIPPED, DELIVERING, DELIVERY_COMPLETED, CANCELED;
}