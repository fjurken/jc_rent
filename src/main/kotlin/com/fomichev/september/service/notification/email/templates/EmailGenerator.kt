package com.fomichev.september.service.notification.email.templates

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fomichev.september.model.User
import com.fomichev.september.service.AbstractService
import com.fomichev.september.service.notification.email.dto.Email
import org.springframework.kafka.core.KafkaTemplate

abstract class EmailGenerator(
    private val notificationKafkaTemplate: KafkaTemplate<String, Email>
) : AbstractService() {

    abstract fun composeEmail(user: User, payload: Map<String, String>?): Email

    abstract fun getMyCode(): EmailTemplate

    fun send(email: Email) {
        notificationKafkaTemplate.send("jc_rent", email)
        /*try {
            log.info("Email $email successfully sent to kafka!")
        } catch (ex: Throwable) {
            log.error("Something went wrong during sending email $email", ex)
        }*/
    }
}

class JsonSerializerWithJTM<T> : org.springframework.kafka.support.serializer.JsonSerializer<T>() {
    init {
        objectMapper
            .registerModule(JavaTimeModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
    }
}
