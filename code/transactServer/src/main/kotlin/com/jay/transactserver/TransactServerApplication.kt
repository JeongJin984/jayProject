package com.jay.transactserver

import ch.qos.logback.core.rolling.RollingFileAppender
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TransactServerApplication

fun main(args: Array<String>) {
	RollingFileAppender
	runApplication<TransactServerApplication>(*args)
}
