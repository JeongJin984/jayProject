package com.jay.orderserver.business.service

import com.jay.orderserver.domain.repo.ProductOrderRepository
import io.grpc.ManagedChannelBuilder
import io.grpc.stub.StreamObserver
import org.chb.examples.lib.HelloReply
import org.chb.examples.lib.HelloRequest
import org.chb.examples.lib.SimpleGrpc
import org.springframework.stereotype.Service

@Service
class OrderService(
    val productOrderRepository: ProductOrderRepository
) {
    fun foo() {
        val channel = ManagedChannelBuilder.forAddress("localhost", 15001)
            .usePlaintext()
            .build();

        val stub = SimpleGrpc.new(channel)
        stub.sayHello(HelloRequest.newBuilder().setName("").build())
    }

}