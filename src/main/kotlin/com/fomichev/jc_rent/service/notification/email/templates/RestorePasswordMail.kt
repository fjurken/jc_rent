package com.fomichev.jc_rent.service.notification.email.templates

import com.fomichev.jc_rent.model.User
import com.fomichev.jc_rent.service.notification.email.dto.Email
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class RestorePasswordMail(
    notificationKafkaTemplate: KafkaTemplate<String, Email>
) : EmailGenerator(
    notificationKafkaTemplate
) {

    override fun composeEmail(user: User, payload: Map<String, String>?): Email {
        return Email(
            emailAddress = user.username,
            templateName = getMyCode().name,
            htmlData = "Hi ${user.firstName}, here is your new password ${payload?.get("pass")}. \n" +
                "Best regards, Journey Car Rent"
        )
    }

    override fun getMyCode(): EmailTemplate = EmailTemplate.RESTORE_PASSWORD
}
