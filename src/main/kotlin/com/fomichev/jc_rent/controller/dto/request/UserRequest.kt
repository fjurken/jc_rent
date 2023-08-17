package com.fomichev.jc_rent.controller.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotEmpty

data class UserRequest(

    @JsonProperty("email")
    val email: String,

    @JsonProperty("password")
    val password: String,

    @JsonProperty("firstName")
    @field:NotEmpty(message = "First name should be entered!")
    val firstName: String,

    @JsonProperty("lastName")
    @NotEmpty
    val lastName: String
)
