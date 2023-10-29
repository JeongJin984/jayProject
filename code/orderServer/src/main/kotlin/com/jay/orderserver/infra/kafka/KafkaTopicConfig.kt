package com.jay.orderserver.infra.kafka

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder

@Configuration
class KafkaTopicConfig {
    @Bean
    fun orderTopic() : NewTopic {
        return TopicBuilder.name("order")
            .partitions(2)
            .replicas(2)
            .build()
    }

    @Bean
    fun orderCancelTopic() : NewTopic {
        return TopicBuilder.name("order-cancel")
            .partitions(2)
            .replicas(2)
            .build()
    }

}