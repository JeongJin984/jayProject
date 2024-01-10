package com.jay.productserver.business.service

import com.jay.productserver.domain.entity.ProductId
import com.jay.productserver.domain.repo.ProductRepository
import com.jay.proto.lib.OrderServiceGrpc
import com.jay.proto.lib.OrderServiceGrpc.OrderServiceBlockingStub
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class ProductService(
    val productRepository: ProductRepository,
    private val orderService: OrderServiceBlockingStub
) {
    fun getProductInfo(productId: ProductId) {
        orderService.getOrderCount(com.jay.proto.lib.ProductIdProto.newBuilder().setProductId(productId.getProductId()).build())

    }
}