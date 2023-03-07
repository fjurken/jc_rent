package com.fomichev.september.service.notification.email.templates

import com.fomichev.september.model.Client
import com.fomichev.september.service.notification.email.dto.Email
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import java.io.BufferedReader

@Component
class WelcomeMail(notificationKafkaTemplate: KafkaTemplate<String, Email>) : EmailGenerator(notificationKafkaTemplate) {

    override fun composeEmail(client: Client): Email {
        val reader = BufferedReader(ClassLoader.getSystemResourceAsStream("templates/welcome_mail.html").reader())
        val content = StringBuilder()
        reader.use { reader ->
            var line = reader.readLine()
            while (line != null) {
                content.append(line)
                line = reader.readLine()
            }
        }
        val email = Email(
            emailAddress = client.email,
            templateName = getMyCode().name,
            htmlData = content.toString()
        )
        log.info("Composed ${email.templateName} successfully")
        return email
    }

    override fun getMyCode(): EmailTemplate = EmailTemplate.WELCOME
}
