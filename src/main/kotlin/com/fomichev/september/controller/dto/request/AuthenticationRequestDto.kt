package com.fomichev.september.controller.dto.request

import com.fasterxml.jackson.annotation.JsonProperty

data class AuthenticationRequestDto(
    @JsonProperty("username")
    val username: String,
    @JsonProperty("password")
    val password: String
)
