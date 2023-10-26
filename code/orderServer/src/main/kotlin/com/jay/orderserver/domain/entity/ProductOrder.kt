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
    var orderLine: List<OrderLine> = listOf(),

    @Column(name = "total_amounts")
    var totalAmounts: Int? = null,

    @Column(name = "order_state")
    var orderState: OrderState? = null,

    @ManyToOne(targetEntity = ShippingInfo::class, fetch = FetchType.LAZY)
    @JoinColumn(name = "shipping_info_id")
    var shippingInfo: ShippingInfo? = null
) {
    private fun setOrderLines(orderLines: List<OrderLine>) {
        verifyAtLeastOneOrMoreOrderLines(orderLines)
        this.orderLine = orderLines
        calculateTotalAmounts()
    }

    private fun verifyAtLeastOneOrMoreOrderLines(orderLines: List<OrderLine>?) {
        if(orderLines.isNullOrEmpty()) {
            throw IllegalArgumentException("no OrderLines")
        }
    }

    private fun calculateTotalAmounts() : Int? {
        this.totalAmounts = orderLine.sumOf { it.amount!! }
        return this.totalAmounts
    }

    fun changeShippingInfo(shippingInfo: ShippingInfo): Boolean {
        return orderState == OrderState.PAYMENT_WAITING || orderState == OrderState.PREPARING
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