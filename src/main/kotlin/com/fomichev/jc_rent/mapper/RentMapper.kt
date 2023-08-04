package com.fomichev.jc_rent.mapper

import com.fomichev.jc_rent.controller.dto.response.RentResponse
import com.fomichev.jc_rent.model.Rent
import com.fomichev.jc_rent.service.car.CarService
import org.springframework.stereotype.Service

@Service
class RentMapper(
    private val carService: CarService
) {

    fun rentToRentResponse(rent: List<Rent>): List<RentResponse> {
        return rent.map {
            RentResponse(
                it.id!!,
                it.createdDate!!,
                it.updatedDate!!,
                carService.getCar(it.carId)!!,
                it.startDate,
                it.endDate
            )
        }
    }
}
