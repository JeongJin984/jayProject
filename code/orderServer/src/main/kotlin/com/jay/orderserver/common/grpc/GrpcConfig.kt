package com.jay.orderserver.common.grpc

import com.jay.orderserver.business.service.OrderGrpcService
import io.grpc.Server
import io.grpc.ServerBuilder
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.ContextClosedEvent
import org.springframework.context.event.EventListener

@Configuration
class GrpcConfig(orderGrpcService: OrderGrpcService) {
    private val log : Logger = LoggerFactory.getLogger(GrpcConfig::class.java);
    private var server: Server = ServerBuilder
        .forPort(9093)
        .addService(orderGrpcService)
        .build()
    @EventListener(ApplicationReadyEvent::class)
    fun doSomethingAfterStartup() {
        server.start()
        log.info("Server started, listening on 9092")
        Runtime.getRuntime().addShutdownHook(
            Thread {
                println("*** shutting down gRPC server since JVM is shutting down")
                server.shutdown()
                println("*** server shut down")
            },
        )
    }

    @EventListener(ContextClosedEvent::class)
    fun onContextClosedEvent(contextClosedEvent: ContextClosedEvent) {
        server.awaitTermination()
    }
}