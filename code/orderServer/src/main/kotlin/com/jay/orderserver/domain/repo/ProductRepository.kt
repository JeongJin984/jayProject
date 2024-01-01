package com.jay.orderserver.domain.repo

import com.jay.orderserver.domain.product.Product
import com.jay.orderserver.domain.product.ProductId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<Product, ProductId> {
}