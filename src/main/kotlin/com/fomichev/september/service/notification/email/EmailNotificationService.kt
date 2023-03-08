package com.fomichev.september.service.notification.email

import com.fomichev.september.model.Client
import com.fomichev.september.service.notification.email.templates.EmailTemplate

interface EmailNotificationService {

    fun notify(client: Client, emailTemplate: EmailTemplate, payload: Map<String, String>?)
}
