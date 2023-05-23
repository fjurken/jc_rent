package com.fomichev.september.controller.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import com.fomichev.september.enum.*

data class CarRequest(
    @JsonProperty("brand")
    val brand: CarBrand,
    @JsonProperty("model")
    val model: String,
    @JsonProperty("carType")
    var carType: CarType,
    @JsonProperty("color")
    val color: CarColor,
    @JsonProperty("engineType")
    val engineType: EngineType,
    @JsonProperty("engineCapacity")
    val engineCapacity: String,
    @JsonProperty("enginePower")
    val enginePower: Int,
    @JsonProperty("transmission")
    var transmission: Transmission,
    @JsonProperty("licencePlate")
    val licencePlate: String
)
