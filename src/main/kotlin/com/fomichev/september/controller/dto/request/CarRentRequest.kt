package com.fomichev.september.controller.dto.request

import java.time.Instant

data class CarRentRequest(
    val carId: Long,
    val startDate: Instant,
    val endDate: Instant
)