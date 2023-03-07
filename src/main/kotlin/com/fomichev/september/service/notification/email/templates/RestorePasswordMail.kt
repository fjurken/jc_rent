package com.fomichev.september.service.notification.email.templates

import com.fomichev.september.model.Client
import com.fomichev.september.service.notification.email.dto.Email
import net.bytebuddy.utility.RandomString
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class RestorePasswordMail(notificationKafkaTemplate: KafkaTemplate<String, Email>) : EmailGenerator(
    notificationKafkaTemplate
) {

    override fun composeEmail(client: Client): Email {
        val tempPassword = RandomString.make()
        return Email(
            emailAddress = client.email,
            templateName = getMyCode().name,
            htmlData = tempPassword
        )
    }

    override fun getMyCode(): EmailTemplate = EmailTemplate.RESTORE_PASSWORD
}
