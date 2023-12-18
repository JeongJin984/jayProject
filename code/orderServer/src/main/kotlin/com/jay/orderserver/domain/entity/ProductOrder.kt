package com.jay.orderserver.domain.entity

import jakarta.persistence.*
import java.lang.IllegalArgumentException
import java.math.BigDecimal

@Entity
@Table(name = "product_order")
class ProductOrder (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    var orderId: Long? = null,

    @Column(name = "owner_id")
    var ownerId: Long? = null,

    @OneToMany(mappedBy = "productOrder", fetch = FetchType.LAZY)
    var orderList: List<OrderList> = listOf(),

    @Column(name = "total_amounts")
    var totalAmounts: BigDecimal = BigDecimal(-1),

    @Column(name = "order_state")
    var orderState: OrderState = OrderState.PREPARING,

    @ManyToOne(targetEntity = ShippingInfo::class, fetch = FetchType.LAZY)
    @JoinColumn(name = "shipping_info_id")
    var shippingInfo: ShippingInfo? = null
) {
    private fun setOrderLines(orderLists: List<OrderList>) {
        verifyAtLeastOneOrMoreOrderLines(orderLists)
        this.orderList = orderLists
        calculateTotalAmounts()
    }

    private fun verifyAtLeastOneOrMoreOrderLines(orderLists: List<OrderList>?) {
        if(orderLists.isNullOrEmpty()) {
            throw IllegalArgumentException("no OrderLines")
        }
    }

    private fun calculateTotalAmounts() : BigDecimal {
        this.totalAmounts = orderList.sumOf { it.getAmount() }
        return this.totalAmounts
    }

    fun changeShippingInfo(shippingInfo: ShippingInfo): Boolean {
        val canChange = orderState == OrderState.PREPARING
        if(!canChange) return canChange
        this.shippingInfo = shippingInfo
        return canChange
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