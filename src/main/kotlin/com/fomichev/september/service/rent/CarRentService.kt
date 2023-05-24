package com.fomichev.september.service.rent

import com.fomichev.september.controller.dto.request.RentRequest
import com.fomichev.september.model.Rent

interface CarRentService {

    fun startRent(request: RentRequest)

    fun finishRent(rentId: Long)

    fun getActiveRentList(): List<Rent>
}
