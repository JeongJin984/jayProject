package com.jay.productserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ProductserverApplication

fun main(args: Array<String>) {
	runApplication<ProductserverApplication>(*args)
}
