package com.fomichev.jc_rent.service.rent

import com.fomichev.jc_rent.controller.dto.request.CarRentRequest
import com.fomichev.jc_rent.model.Rent

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
