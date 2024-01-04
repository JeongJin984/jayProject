package com.jay.productserver.common.dto

data class MainProductInfo(
    val productId: String,
    val name: String,
    val category: List<String>,
    val productImage: List<String>,
    val price: Int,
    val description: String,

) {
}