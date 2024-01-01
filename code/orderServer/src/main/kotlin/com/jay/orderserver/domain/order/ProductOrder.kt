package com.jay.orderserver.domain.order

import jakarta.persistence.*
import java.io.Serializable
import java.lang.IllegalArgumentException
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "product_order")
@Access(AccessType.FIELD)
class ProductOrder (
    @EmbeddedId
    private var orderLineId: OrderNo,

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "order_line", joinColumns = [JoinColumn(name = "order_number")])
    @OrderColumn(name = "line_idx")
    private var orderLines: List<OrderLine> = listOf(),

    @Column(name = "total_amounts")
    private var totalAmount: BigDecimal,

    @Embedded
    private var shippingInfo: ShippingInfo? = null,

    @Column(name = "order_state")
    @Enumerated(EnumType.STRING)
    private var orderState: OrderState = OrderState.PREPARING,

    @Column(name = "order_date")
    private var orderDate: LocalDateTime
) {

    private fun verifyAtLeastOneOrMoreOrderLines() {
        if(orderLines.isEmpty()) {
            throw IllegalArgumentException("no OrderLines")
        }
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

    private fun calculateTotalAmounts() {
        this.totalAmount = orderLines.sumOf { it.getAmount() }
    }
}


enum class OrderState {
    PAYMENT_WAITING, PREPARING, SHIPPED, DELIVERING, DELIVERY_COMPLETED, DELIVERY_FAILED, CANCELED;
}

@Embeddable
class OrderNo internal constructor (
    @Column(name = "order_number")
    private var number : String
) : Serializable {
    public fun getNumber() : String {
        return number
    }
}

fun orderNoOf(orderNo : String) : OrderNo {
    return OrderNo(orderNo)
}