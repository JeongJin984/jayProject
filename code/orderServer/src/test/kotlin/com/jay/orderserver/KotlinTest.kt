package com.jay.orderserver

import com.jay.orderserver.domain.entity.ProductOrder
import com.jay.orderserver.domain.entity.ShippingInfo
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class KotlinTest {

    @Test
    @DisplayName("초기 생성 시 배송지 정보 변경 가능")
    fun testShippingInfoChangeAtInitialed() {
        val productOrder = ProductOrder()
        assertTrue(productOrder.changeShippingInfo(ShippingInfo()))
    }

    @Test
    @DisplayName("주문 취소 시 배송지 정보 변경 불가능")
    fun testShippingInfoChangeAtCanceled() {
        val productOrder = ProductOrder()
        productOrder.cancel()
        assertFalse(productOrder.changeShippingInfo(ShippingInfo()))
    }
}