package com.fomichev.september.controller.dto.response

import com.fomichev.september.model.Car
import java.time.Instant

data class RentResponse(
    val id: Long,
    val createdDate: Instant,
    val updatedDate: Instant,
    val car: Car,
    val startDate: Instant,
    val endDate: Instant,
)
