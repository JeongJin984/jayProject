package com.jay.orderserver.domain.repo

import com.jay.orderserver.domain.entity.OrderNo
import com.jay.orderserver.domain.entity.ProductOrder
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductOrderRepository : JpaRepository<ProductOrder, OrderNo> {
}