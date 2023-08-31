package com.fomichev.jc_rent.service.notification.email.kafka

import com.fomichev.jc_rent.service.notification.email.dto.Email
import com.fomichev.jc_rent.service.notification.email.templates.JsonSerializerWithJTM
import org.apache.kafka.clients.producer.ProducerConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

@Configuration
class NotificationKafkaConfiguration {

    @Bean
    fun notificationKafkaProducerFactory(): ProducerFactory<String, Email> {
        val config = mutableMapOf<String, Any>()
        config[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = "localhost:9092"
        config[ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION] = "3"
        config[ProducerConfig.RETRIES_CONFIG] = "3"
        config[ProducerConfig.RETRY_BACKOFF_MS_CONFIG] = "500"
        config[ProducerConfig.MAX_BLOCK_MS_CONFIG] = "5000"
        config[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = JsonSerializerWithJTM::class.java
        config[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = JsonSerializerWithJTM::class.java

        return DefaultKafkaProducerFactory(config)
    }

    @Bean
    fun notificationKafkaTemplate(): KafkaTemplate<String, Email> {
        return KafkaTemplate(notificationKafkaProducerFactory())
    }
}
