package com.fomichev.september.service.notification.email

import com.fomichev.september.model.User
import com.fomichev.september.service.notification.email.templates.EmailTemplate

interface EmailNotificationService {

    fun notify(user: User, emailTemplate: EmailTemplate, payload: Map<String, String>?)
}
