package com.jay.transactserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TransactServerApplication

fun main(args: Array<String>) {
	runApplication<TransactServerApplication>(*args)
}
