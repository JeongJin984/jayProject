package com.jay.productserver.business.service

import com.jay.productserver.common.dto.ProductOrderInfo
import com.jay.productserver.domain.entity.Product
import com.jay.productserver.domain.repo.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(
    val productRepository: ProductRepository
) {
    fun getProduct() : List<ProductOrderInfo> {
        return productRepository.findAll().map { it.createProductOrderInfo() }
    }

    fun foo() : String {
        return "asdf"
    }
}