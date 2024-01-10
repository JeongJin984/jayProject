package com.jay.orderserver.business.service

import com.jay.orderserver.domain.repo.ProductOrderRepository
import io.grpc.ManagedChannelBuilder
import io.grpc.stub.StreamObserver
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class OrderService {
//    fun foo():String {
//        val channel = ManagedChannelBuilder.forAddress("localhost", 9092)
//            .usePlaintext()
//            .build();
//
//        val stub = SimpleGrpc.newBlockingStub(channel)
//        val sayHello : HelloReply = stub.sayHello(HelloRequest.newBuilder().setName("").build())
//
//        return sayHello.message
//    }

}