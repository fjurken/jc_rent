package com.fomichev.september.service.notification.email.templates

// @Component
// class RestorePasswordMail(
//    notificationKafkaTemplate: KafkaTemplate<String, Email>
// ) : EmailGenerator(
//    notificationKafkaTemplate
// ) {

//    override fun composeEmail(client: Client, payload: Map<String, String>?): Email {
//        val tempPassword = payload?.get("pass")
//        return Email(
//            emailAddress = client.email,
//            templateName = getMyCode().name,
//            htmlData = "Hi ${client.name}, here is your new password $tempPassword. \n" +
//                "Best regards, Journey Car Rent"
//        )
//    }
//
//    override fun getMyCode(): EmailTemplate = EmailTemplate.RESTORE_PASSWORD
// }
