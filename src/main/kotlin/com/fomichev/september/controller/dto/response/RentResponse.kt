package com.fomichev.september.controller.dto.response

import com.fomichev.september.model.Car
import java.time.Instant
import java.util.*

data class RentResponse(
    val id: Long,
    val createdDate: Date,
    val updatedDate: Date,
    val car: Car,
    val startDate: Instant,
    val endDate: Instant,
)