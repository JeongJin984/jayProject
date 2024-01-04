package com.jay.productserver.common.dto

import com.jay.productserver.domain.entity.Product
import com.jay.productserver.domain.entity.ProductId
import com.jay.productserver.domain.entity.categoryIdOf
import com.jay.productserver.domain.entity.productIdOf

data class ProductOrderInfo(
    val productId: String,
    val name: String,
    val category: List<Long>,
    val productImage: List<String>,
    val price: Int,
    val description: String,
) {
}

