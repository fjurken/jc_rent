package com.fomichev.september.service.rent

import com.fomichev.september.controller.dto.request.CarRentRequest
import com.fomichev.september.model.Rent

interface CarRentService {

    fun getRentById(rentId: Long): Rent?

    fun requestRent(request: CarRentRequest)

    /**
     * Start rent requested by client*/
    fun startRent(rentId: Long)

    fun finishRent(rentId: Long)

    fun getRequestedRentList(): List<Rent>

    fun getActiveRentList(): List<Rent>
}
