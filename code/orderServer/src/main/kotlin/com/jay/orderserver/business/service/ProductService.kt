package com.jay.orderserver.business.service

import com.jay.orderserver.common.dto.NewProductDTO
import com.jay.orderserver.domain.entity.Product
import com.jay.orderserver.domain.repo.ProductRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository
) {
    @Transactional
    fun addProduct(newProduct: NewProductDTO) {
        productRepository.save(
            Product(
                productId = null,
                name = newProduct.name,
                price = newProduct.price,
                description = newProduct.description
            )
        )
    }
}