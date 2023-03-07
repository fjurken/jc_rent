package com.fomichev.september.service.notification.email.templates

import com.fomichev.september.model.Client
import com.fomichev.september.service.notification.email.dto.Email
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class WelcomeMail(notificationKafkaTemplate: KafkaTemplate<String, Email>) : EmailGenerator(notificationKafkaTemplate) {

    override fun composeEmail(client: Client): Email {
        val email = Email(
            emailAddress = client.email,
            templateName = getMyCode().name,
            htmlData = "Welcome to the Journey Car Rent"
        )
        log.info("Composed ${email.templateName} successfully")
        return email
    }

    override fun getMyCode(): EmailTemplate = EmailTemplate.WELCOME
}
