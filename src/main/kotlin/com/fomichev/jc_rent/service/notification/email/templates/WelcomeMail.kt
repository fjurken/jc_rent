package com.fomichev.jc_rent.service.notification.email.templates

import com.fomichev.jc_rent.model.User
import com.fomichev.jc_rent.service.notification.email.dto.Email
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class WelcomeMail(notificationKafkaTemplate: KafkaTemplate<String, Email>) : EmailGenerator(notificationKafkaTemplate) {

    override fun composeEmail(user: User, payload: Map<String, String>?): Email {
        val email = Email(
            emailAddress = user.username,
            templateName = getMyCode().name,
            htmlData = "Welcome to the Journey Car Rent"
        )
        log.info("Composed ${email.templateName} successfully")
        return email
    }

    override fun getMyCode(): EmailTemplate = EmailTemplate.WELCOME
}
