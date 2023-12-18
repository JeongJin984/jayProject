package com.jay.orderserver.domain.repo

import com.jay.orderserver.domain.entity.ProductOrder
import org.springframework.data.jpa.repository.JpaRepository

interface ProductOrderRepository : JpaRepository<ProductOrder, Long> {
}