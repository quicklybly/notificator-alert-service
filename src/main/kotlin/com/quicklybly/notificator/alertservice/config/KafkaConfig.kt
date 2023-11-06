package com.quicklybly.notificator.alertservice.config

import com.quicklybly.notificator.alertservice.dto.Alert
import com.quicklybly.notificator.alertservice.dto.deserialize.AlertDeserializer
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory


@Configuration
class KafkaConfig {

    @Value("\${spring.kafka.bootstrap-servers}")
    private lateinit var bootstrapAddress: String

    @Value("\${spring.kafka.consumer.auto-offset-reset}")
    private lateinit var offset: String

    @Bean
    fun consumerFactory(): ConsumerFactory<String, Alert> {
        val config = mapOf(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapAddress,
            ConsumerConfig.AUTO_OFFSET_RESET_CONFIG to offset,
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to AlertDeserializer::class.java,
        )
        return DefaultKafkaConsumerFactory(config)
    }

    @Bean
    fun kafkaListenerContainerFactory(
        consumerFactory: ConsumerFactory<String, Alert>
    ): ConcurrentKafkaListenerContainerFactory<String, Alert> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, Alert>()
        factory.consumerFactory = consumerFactory
        return factory
    }
}