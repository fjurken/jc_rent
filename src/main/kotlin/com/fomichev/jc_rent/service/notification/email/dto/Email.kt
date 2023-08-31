package com.fomichev.jc_rent.service.notification.email.dto

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Email(
    val receiver: String,
    val sender: String,
    val subject: String,
    val htmlData: String
)
