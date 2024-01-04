package com.jay.productserver.domain.repo

import com.jay.productserver.domain.entity.Product
import com.jay.productserver.domain.entity.ProductId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<Product, ProductId> {
}