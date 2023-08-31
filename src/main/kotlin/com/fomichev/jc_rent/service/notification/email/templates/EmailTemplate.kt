package com.fomichev.jc_rent.service.notification.email.templates

enum class EmailTemplate(
    val text: String
) {
    WELCOME("Welcome to Journey Car Rent"),
    PASSWORD_CHANGED("Password has been changed"),
    EMAIL_CHANGED(""),
    RESTORE_PASSWORD("Password has been restored"),
    RENT_REQUEST("Car rent request")
}
