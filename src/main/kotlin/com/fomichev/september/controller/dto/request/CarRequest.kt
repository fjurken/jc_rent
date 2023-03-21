package com.fomichev.september.controller.dto.request

import com.fomichev.september.enum.CarBrand
import com.fomichev.september.enum.CarColor

data class CarRequest(
    val brand: CarBrand,
    val model: String,
    val color: CarColor,
    val horsePower: Int,
    val licencePlate: String
)
