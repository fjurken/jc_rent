package com.fomichev.september.service.notification.email.templates

import com.fomichev.september.model.Client
import com.fomichev.september.service.notification.email.dto.Email

interface EmailGenerator {

    fun composeEmail(client: Client): Email

    fun getMyCode(): EmailTemplate
}
