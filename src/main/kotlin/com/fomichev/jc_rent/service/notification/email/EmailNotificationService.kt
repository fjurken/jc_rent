package com.fomichev.jc_rent.service.notification.email

import com.fomichev.jc_rent.model.User
import com.fomichev.jc_rent.service.notification.email.templates.EmailTemplate

interface EmailNotificationService {

    fun notify(user: User, emailTemplate: EmailTemplate, payload: Map<String, String>?)
}
