package com.jay.orderserver.domain.repo

import com.jay.orderserver.domain.entity.OrderNo
import com.jay.orderserver.domain.entity.ProductOrder
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ProductOrderRepository : JpaRepository<ProductOrder, OrderNo>, ProductOrderRepositoryCustom {
    @Query("select sum(ol.quantity) from order_line ol where ol.productId = ?1", nativeQuery = true)
    fun getTotalProductOrderQuantity(productId: String) : Int
}