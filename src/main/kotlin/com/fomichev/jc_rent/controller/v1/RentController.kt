package com.fomichev.jc_rent.controller.v1

import com.fomichev.jc_rent.controller.dto.request.CarRentRequest
import com.fomichev.jc_rent.controller.dto.response.CarCatalog
import com.fomichev.jc_rent.mapper.CarMapper
import com.fomichev.jc_rent.service.car.CarService
import com.fomichev.jc_rent.service.price.PriceService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/catalog/")
@CrossOrigin(origins = ["http://localhost:8080"])
class RentController(
    private val carService: CarService,
    private val priceService: PriceService,
    private val carMapper: CarMapper
) {

    @GetMapping("cars")
    fun getCarsCatalog(): List<CarCatalog> {
        val carList = carService.getListOfAvailableCars()
        val price = priceService.getPricesOfCarsByIds(carList?.map { it.id }!!.toList())
        return carMapper.toAvailableListCarResponse(carList, price)
    }

    /*Request for car rent from client*/
    @PostMapping("rent")
    fun rent(@RequestBody request: CarRentRequest) {
        carService.requestRentCar(request)
    }
}
