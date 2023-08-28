package com.jay.orderserver.domain.jayMarket.repo

import com.jay.orderserver.domain.jayMarket.entity.ProductOrder
import org.springframework.data.jpa.repository.JpaRepository

interface ProductOrderRepository : JpaRepository<ProductOrder, Long?> {
}