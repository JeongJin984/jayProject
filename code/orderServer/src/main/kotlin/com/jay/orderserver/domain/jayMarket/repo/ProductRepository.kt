package com.jay.orderserver.domain.jayMarket.repo

import com.jay.orderserver.domain.jayMarket.entity.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Long> {
}