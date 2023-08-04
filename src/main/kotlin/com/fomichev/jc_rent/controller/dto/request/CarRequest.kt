package com.fomichev.jc_rent.controller.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import com.fomichev.jc_rent.enum.CarBrand
import com.fomichev.jc_rent.enum.CarColor
import com.fomichev.jc_rent.enum.CarType
import com.fomichev.jc_rent.enum.EngineType
import com.fomichev.jc_rent.enum.Transmission

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
