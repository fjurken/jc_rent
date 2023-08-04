package com.fomichev.jc_rent.controller.dto.response

import com.fomichev.jc_rent.model.Car
import java.time.Instant

data class RentResponse(
    val id: Long,
    val createdDate: Instant,
    val updatedDate: Instant,
    val car: Car,
    val startDate: Instant,
    val endDate: Instant,
)
