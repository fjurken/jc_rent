package com.fomichev.september.service.notification.email

import com.fomichev.september.model.Client
import com.fomichev.september.service.AbstractService
import com.fomichev.september.service.notification.email.dto.Email
import com.fomichev.september.service.notification.email.templates.EmailGenerator
import com.fomichev.september.service.notification.email.templates.EmailTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.UnsupportedOperationException

@Service
class EmailNotificationServiceImpl() : EmailNotificationService, AbstractService() {

    @Autowired
    constructor(emailGenerators: List<EmailGenerator>) : this() {
        mapGenerators = emailGenerators.associateBy { it.getMyCode() }
    }

    private var mapGenerators = mapOf<EmailTemplate, EmailGenerator>()

    @Transactional
    override fun send(email: Email) {
        log.info("Email ${email.templateName} to ${email.emailAddress} was successfully sent!")
    }

    @Transactional
    override fun notify(client: Client, emailTemplate: EmailTemplate) {
        val emailGenerator = mapGenerators[emailTemplate]
            ?: throw UnsupportedOperationException("$emailTemplate doesn't supported yet")
        log.info("Composing email for ${client.email}")
        val email = emailGenerator.composeEmail(client)
        send(email)
    }
}
