package com.fomichev.september.service.rent

import com.fomichev.september.model.Rent
import java.time.Instant

interface CarRentService {

    fun startRent(carId: Long, startDate: Instant, endDate: Instant)

    fun finishRent(rentId: Long)

    fun getActiveRentList(): List<Rent>
}
