package com.fomichev.september.service.notification.email.templates

// @Component
// class WelcomeMail(notificationKafkaTemplate: KafkaTemplate<String, Email>) : EmailGenerator(notificationKafkaTemplate) {
//
//    override fun composeEmail(client: Client, payload: Map<String, String>?): Email {
//        val email = Email(
//            emailAddress = client.email,
//            templateName = getMyCode().name,
//            htmlData = "Welcome to the Journey Car Rent"
//        )
//        log.info("Composed ${email.templateName} successfully")
//        return email
//    }
//
//    override fun getMyCode(): EmailTemplate = EmailTemplate.WELCOME
// }
