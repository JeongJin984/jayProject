package com.jay.orderserver.infra.kafka

import org.apache.kafka.clients.admin.NewTopic
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerConfig.*
import org.apache.kafka.common.serialization.IntegerSerializer
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.KafkaListenerContainerFactory
import org.springframework.kafka.config.TopicBuilder
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate

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
    fun producerFactory(): ProducerFactory<String, String> {
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
    fun kafkaTemplate(): KafkaTemplate<String, String> {
        return KafkaTemplate(producerFactory())
    }

    @Bean
    fun repliesContainer(
        kafkaListenerContainerFactory : KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>>
    ) : ConcurrentMessageListenerContainer<String, String> {
        val repliesContainer = kafkaListenerContainerFactory.createContainer("replies")
        repliesContainer.containerProperties.setGroupId("repliesGroup")
        repliesContainer.isAutoStartup = true
        return repliesContainer
    }

    @Bean
    fun replyingTemplate(
        kafkaListenerContainerFactory : KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>>
    ) : ReplyingKafkaTemplate<String, String, String> {
        return ReplyingKafkaTemplate(producerFactory(), repliesContainer(kafkaListenerContainerFactory))
    }
}