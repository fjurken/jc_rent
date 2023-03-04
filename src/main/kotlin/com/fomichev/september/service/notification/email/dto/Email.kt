package com.fomichev.september.service.notification.email.dto

data class Email(
    val emailAddress: String,
    val templateName: String,
    val htmlData: String
)
