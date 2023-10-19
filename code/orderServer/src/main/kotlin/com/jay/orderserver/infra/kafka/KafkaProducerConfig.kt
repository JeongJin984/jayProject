package com.jay.orderserver.infra.kafka

import org.apache.kafka.clients.admin.NewTopic
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerConfig.*
import org.apache.kafka.common.serialization.IntegerSerializer
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

@Configuration
class KafkaProducerConfig {
    @Bean
    fun healCheckTopic() : NewTopic {
        return TopicBuilder.name("healthcheck")
            .partitions(2)
            .replicas(2)
            .build()
    }

    @Bean
    fun producerFactory(): ProducerFactory<Int?, String?> {
        return DefaultKafkaProducerFactory(producerConfigs())
    }

    @Bean
    fun producerConfigs(): Map<String, Any> {
        return mapOf(
            BOOTSTRAP_SERVERS_CONFIG to "localhost:9092",
            KEY_SERIALIZER_CLASS_CONFIG to IntegerSerializer::class.java,
            VALUE_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java
        )
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<Int?, String?> {
        return KafkaTemplate(producerFactory())
    }
}