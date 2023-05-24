package com.fomichev.september.controller.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.Instant
import java.util.*

data class RentRequest(
    @JsonProperty("carId")
    val carId: Long,

    @JsonProperty("startDate")
    val startDate: Instant,

    @JsonProperty("endDate")
    val endDate: Instant
)