package com.fomichev.september.mapper

import com.fomichev.september.controller.dto.response.RentResponse
import com.fomichev.september.model.Rent
import com.fomichev.september.service.car.CarService
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
