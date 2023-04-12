package com.fomichev.september.controller

import com.fomichev.september.controller.dto.request.CarRentRequest
import com.fomichev.september.controller.dto.response.CarCatalog
import com.fomichev.september.mapper.CarMapper
import com.fomichev.september.service.car.CarService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/catalog/")
class RentControllerV1(
    private val carService: CarService,
    private val carMapper: CarMapper
) {

    @GetMapping("cars")
    fun getCarsCatalog(): List<CarCatalog> {
        val carList = carService.getListOfAvailableCars()
        return carMapper.toAvailableListCarResponse(carList)
    }

    @PostMapping("rent")
    fun rent(@RequestBody request: CarRentRequest) {
        carService.startRentCar(request.carId, request.startDate, request.endDate)
    }
}
