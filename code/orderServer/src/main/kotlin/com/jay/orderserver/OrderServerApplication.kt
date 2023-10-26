package com.jay.orderserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.kafka.annotation.EnableKafka

@SpringBootApplication
@EnableKafka
class OrderServerApplication

fun main(args: Array<String>) {
	runApplication<OrderServerApplication>(*args)
}
