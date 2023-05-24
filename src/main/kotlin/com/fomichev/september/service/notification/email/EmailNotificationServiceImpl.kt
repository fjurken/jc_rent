package com.fomichev.september.service.notification.email

import com.fomichev.september.service.AbstractService
import org.springframework.stereotype.Service

@Service
class EmailNotificationServiceImpl() : EmailNotificationService, AbstractService() {

//    @Autowired
//    constructor(emailGenerators: List<EmailGenerator>) : this() {
//        mapGenerators = emailGenerators.associateBy { it.getMyCode() }
//    }

//    private var mapGenerators = mapOf<EmailTemplate, EmailGenerator>()
//
//    @Transactional
//    override fun notify(client: Client, emailTemplate: EmailTemplate, payload: Map<String, String>?) {
//        val emailGenerator = mapGenerators[emailTemplate]
//            ?: throw UnsupportedOperationException("$emailTemplate doesn't supported yet")
//        log.info("Composing email for ${client.email}")
//        val email = emailGenerator.composeEmail(client, payload)
//        emailGenerator.send(email)
//    }
}
