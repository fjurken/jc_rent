package com.fomichev.jc_rent.service.notification.email

import com.fomichev.jc_rent.model.User
import com.fomichev.jc_rent.service.notification.email.templates.EmailGenerator
import com.fomichev.jc_rent.service.notification.email.templates.EmailTemplate
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class EmailNotificationServiceImpl() : EmailNotificationService, KLogging() {

    @Autowired
    constructor(emailGenerators: List<EmailGenerator>) : this() {
        mapGenerators = emailGenerators.associateBy { it.getMyCode() }
    }

    private var mapGenerators = mapOf<EmailTemplate, EmailGenerator>()

    @Transactional
    override fun notify(user: User, emailTemplate: EmailTemplate, payload: Map<String, Any>) {
        val emailGenerator = mapGenerators[emailTemplate]
            ?: throw UnsupportedOperationException("$emailTemplate doesn't supported yet")
        logger.info("Composing email for ${user.username}")
        val email = emailGenerator.composeEmail(user, payload)
        emailGenerator.send(email)
    }
}
