package com.fomichev.september.service.notification.email

import com.fomichev.september.model.Client
import com.fomichev.september.service.notification.email.dto.Email
import com.fomichev.september.service.notification.email.templates.EmailTemplate

interface EmailNotificationService {

    fun notify(client: Client, emailTemplate: EmailTemplate)
}
