package com.fomichev.jc_rent.service.notification.email.dto

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Email(
    val emailAddress: String,
    val templateName: String,
    val htmlData: String
)
