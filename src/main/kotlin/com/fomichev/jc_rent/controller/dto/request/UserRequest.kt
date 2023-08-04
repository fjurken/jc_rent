package com.fomichev.jc_rent.controller.dto.request

import com.fasterxml.jackson.annotation.JsonProperty

data class UserRequest(

    @JsonProperty("email")
    val email: String,

    @JsonProperty("password")
    val password: String,

    @JsonProperty("firstName")
    val firstName: String,

    @JsonProperty("lastName")
    val lastName: String
)
