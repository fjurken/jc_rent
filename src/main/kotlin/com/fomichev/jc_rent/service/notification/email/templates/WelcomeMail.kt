package com.fomichev.jc_rent.service.notification.email.templates

import com.fomichev.jc_rent.configuration.EmailSenderConfiguration
import com.fomichev.jc_rent.model.User
import com.fomichev.jc_rent.service.notification.email.dto.Email
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Component

@Component
class WelcomeMail(
    private val javaMailSender: JavaMailSender,
    private val emailSenderConfiguration: EmailSenderConfiguration
) : EmailGenerator(javaMailSender) {

    override fun composeEmail(user: User, payload: Map<String, Any>): Email {
        val email = Email(
            receiver = user.username,
            sender = emailSenderConfiguration.sender,
            subject = getMyCode().text,
            htmlData = "Welcome to the Journey Car Rent"
        )
        logger.info("Composed ${email.subject} successfully")
        return email
    }

    override fun getMyCode(): EmailTemplate = EmailTemplate.WELCOME
}
