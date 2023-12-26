package com.jay.orderserver.domain.entity

import jakarta.persistence.*
import java.lang.IllegalArgumentException
import java.math.BigDecimal

@Entity
@Table(name = "order_confirm")
class UserOrder (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private var orderId: Long? = null,

    @Column(name = "owner_id")
    private var ownerId: Long? = null,

    @OneToMany(mappedBy = "productOrder", fetch = FetchType.LAZY)
    private var productOrder: List<ProductOrder> = listOf(),

    @Column(name = "total_amounts")
    private var totalAmounts: BigDecimal = BigDecimal(-1),

    @Column(name = "order_state")
    @Enumerated(EnumType.STRING)
    private var orderState: OrderState = OrderState.PREPARING,

    @Embedded
    private var shippingInfo: ShippingInfo? = null
) {
    private fun setOrderLines(productOrders: List<ProductOrder>) {
        verifyAtLeastOneOrMoreOrderLines(productOrders)
        this.productOrder = productOrders
        calculateTotalAmounts()
    }

    private fun verifyAtLeastOneOrMoreOrderLines(productOrders: List<ProductOrder>?) {
        if(productOrders.isNullOrEmpty()) {
            throw IllegalArgumentException("no OrderLines")
        }
    }

    private fun calculateTotalAmounts() : BigDecimal {
        this.totalAmounts = productOrder.sumOf { it.getAmount() }
        return this.totalAmounts
    }

    fun changeShippingInfo(shippingInfo: ShippingInfo): Boolean {
        val canChange = orderState == OrderState.PREPARING
        if(!canChange) return false
        this.shippingInfo = shippingInfo
        return true
    }

    fun cancel() {
        verifyNotYetShipped()
        this.orderState = OrderState.CANCELED
    }

    fun confirm() {
        this.orderState = OrderState.PAYMENT_WAITING
    }

    fun paymentSucceed() {
        this.orderState = OrderState.PREPARING
    }

    fun deliverStarted() {
        this.orderState = OrderState.DELIVERING
    }

    fun deliverSucceed() {
        this.orderState = OrderState.DELIVERY_COMPLETED
    }

    fun deliverFailed() {
        this.orderState = OrderState.DELIVERY_FAILED
    }

    private fun verifyNotYetShipped() {
        if(orderState != OrderState.PAYMENT_WAITING && orderState != OrderState.PREPARING)
            throw IllegalArgumentException("already shipped")
    }
}

enum class OrderState {
    PAYMENT_WAITING, PREPARING, SHIPPED, DELIVERING, DELIVERY_COMPLETED, DELIVERY_FAILED, CANCELED;
}