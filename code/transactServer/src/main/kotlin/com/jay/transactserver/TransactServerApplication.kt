package com.jay.transactserver

import ch.qos.logback.core.rolling.RollingFileAppender
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.kafka.annotation.EnableKafka

@SpringBootApplication
@EnableKafka
class TransactServerApplication

fun main(args: Array<String>) {
	runApplication<TransactServerApplication>(*args)
}
