package com.fomichev.jc_rent.service.notification.email.templates

import com.fomichev.jc_rent.configuration.EmailSenderConfiguration
import com.fomichev.jc_rent.model.User
import com.fomichev.jc_rent.service.notification.email.dto.Email
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Component

@Component
class RestorePasswordMail(
    private val javaMailSender: JavaMailSender,
    private val emailSenderConfiguration: EmailSenderConfiguration
) : EmailGenerator(javaMailSender) {

    override fun composeEmail(user: User, payload: Map<String, Any>): Email {
        return Email(
            receiver = user.username,
            sender = emailSenderConfiguration.sender,
            subject = getMyCode().name,
            htmlData = "Hi ${user.firstName}, here is your new password ${payload?.get("pass")}. \n" +
                "Best regards, Journey Car Rent"
        )
    }

    override fun getMyCode(): EmailTemplate = EmailTemplate.RESTORE_PASSWORD
}
