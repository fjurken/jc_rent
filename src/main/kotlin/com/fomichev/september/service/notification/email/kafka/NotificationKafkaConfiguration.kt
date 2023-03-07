package com.fomichev.september.service.notification.email.kafka

import com.fomichev.september.service.notification.email.dto.Email
import com.fomichev.september.service.notification.email.templates.JsonSerializerWithJTM
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
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, JsonSerializerWithJTM::class.java)
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializerWithJTM::class.java)

        return DefaultKafkaProducerFactory(config)
    }

    @Bean
    fun notificationKafkaTemplate(): KafkaTemplate<String, Email> {
        return KafkaTemplate(notificationKafkaProducerFactory())
    }

}