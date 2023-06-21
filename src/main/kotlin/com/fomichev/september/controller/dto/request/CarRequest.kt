package com.fomichev.september.controller.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import com.fomichev.september.enum.CarBrand
import com.fomichev.september.enum.CarColor
import com.fomichev.september.enum.CarType
import com.fomichev.september.enum.EngineType
import com.fomichev.september.enum.Transmission

data class CarRequest(
    @JsonProperty("brand")
    val brand: CarBrand,
    @JsonProperty("model")
    val model: String,
    @JsonProperty("carType")
    val carType: CarType,
    @JsonProperty("color")
    val color: CarColor,
    @JsonProperty("engineType")
    val engineType: EngineType,
    @JsonProperty("engineCapacity")
    val engineCapacity: String,
    @JsonProperty("enginePower")
    val enginePower: Int,
    @JsonProperty("transmission")
    val transmission: Transmission,
    @JsonProperty("licencePlate")
    val licencePlate: String
)
