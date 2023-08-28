package com.jay.orderserver.domain.repo

import com.jay.orderserver.domain.entity.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Long> {
}