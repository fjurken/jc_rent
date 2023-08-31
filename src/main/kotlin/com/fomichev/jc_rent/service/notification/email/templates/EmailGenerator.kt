package com.fomichev.jc_rent.service.notification.email.templates

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fomichev.jc_rent.model.User
import com.fomichev.jc_rent.service.AbstractService
import com.fomichev.jc_rent.service.notification.email.dto.Email
import org.springframework.mail.MailException
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper

abstract class EmailGenerator(
    private val javaMailSender: JavaMailSender,
) : AbstractService() {

    abstract fun composeEmail(user: User, payload: Map<String, Any>): Email

    abstract fun getMyCode(): EmailTemplate

    fun send(email: Email) {
        val message = javaMailSender.createMimeMessage()
        val messageHelper = MimeMessageHelper(message, true)
        messageHelper.addTo(email.receiver)
        messageHelper.setFrom(email.sender)
        messageHelper.setSubject(email.subject)
        messageHelper.setText(email.htmlData, true)
        try {
            javaMailSender.send(message)
            log.info("Email ${this.getMyCode()} was successfully sent to ${email.receiver}!")
        } catch (ex: MailException) {
            log.error("Something went wrong during sending email!", ex)
        }
    }
}

class JsonSerializerWithJTM<T> : org.springframework.kafka.support.serializer.JsonSerializer<T>() {
    init {
        objectMapper
            .registerModule(JavaTimeModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
    }
}
