package com.fomichev.jc_rent.service.notification.email.templates

import com.fomichev.jc_rent.model.User
import com.fomichev.jc_rent.service.notification.email.dto.Email
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class RentRequestMail(
    notificationKafkaTemplate: KafkaTemplate<String, Email>
) : EmailGenerator(notificationKafkaTemplate) {
    override fun composeEmail(user: User, payload: Map<String, String>?): Email {
        return Email(
            emailAddress = user.username,
            templateName = getMyCode().name,
            htmlData = "Hi, ${user.username}, we have got request for car rent.\n" +
                "Request data:\n" +
                "${payload?.get("date")},\n" +
                "${payload?.get("car")},\n" +
                "${payload?.get("total")}."
        )
    }

    override fun getMyCode(): EmailTemplate = EmailTemplate.RENT_REQUEST
}
