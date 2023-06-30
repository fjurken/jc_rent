package com.fomichev.september.controller.dto.request

import com.fasterxml.jackson.annotation.JsonProperty

data class PriceRequest(
    @JsonProperty("carId")
    val carId: Long,

    @JsonProperty("price")
    val price: Double
)
