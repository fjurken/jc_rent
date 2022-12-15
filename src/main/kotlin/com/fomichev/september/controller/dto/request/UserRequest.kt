package com.fomichev.september.controller.dto.request

data class UserRequest(

    val email: String,

    val password: String,

    val name: String?
)
