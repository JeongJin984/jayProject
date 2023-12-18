package com.jay.orderserver.business.api

import com.jay.orderserver.business.service.ProductService
import com.jay.orderserver.common.dto.NewProductDTO
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/product/v1")
class ProductAPI(
    private val productService: ProductService
) {
    @PostMapping("/register")
    fun registerProduct(@RequestBody newProduct: NewProductDTO) {
        productService.addProduct(newProduct)
    }
}