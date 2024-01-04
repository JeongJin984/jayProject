package com.jay.productserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.kafka.annotation.EnableKafka

@SpringBootApplication
@EnableKafka
class ProductserverApplication

fun main(args: Array<String>) {
	runApplication<ProductserverApplication>(*args)
}
