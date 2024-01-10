package com.jay.orderserver.business.service

import com.jay.orderserver.domain.entity.orderNoOf
import com.jay.orderserver.domain.repo.ProductOrderRepository
import com.jay.proto.lib.OrderServiceGrpc
import com.jay.proto.lib.ProductIdProto
import com.jay.proto.lib.ProductOrderInfoProto
import io.grpc.stub.StreamObserver
import org.springframework.stereotype.Service

@Service
class OrderGrpcService(
    private val orderRepository: ProductOrderRepository
) : OrderServiceGrpc.OrderServiceImplBase() {
    override fun getOrderCount(request: ProductIdProto?, responseObserver: StreamObserver<ProductOrderInfoProto>?) {
        val quantity = orderRepository.getTotalProductOrderQuantity(request!!.productId)

        val orderQuantity = ProductOrderInfoProto.newBuilder()
            .setOrderCount(quantity)
            .build()

        responseObserver?.run {
            onNext(orderQuantity)
            onCompleted()
        }
    }
}