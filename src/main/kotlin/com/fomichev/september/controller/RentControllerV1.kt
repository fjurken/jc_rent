package com.fomichev.september.controller

import com.fomichev.september.controller.dto.request.CarRentRequest
import com.fomichev.september.controller.dto.response.CarCatalog
import com.fomichev.september.mapper.CarMapper
import com.fomichev.september.service.car.CarService
import com.fomichev.september.service.price.PriceService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import sendinblue.Configuration
import sendinblue.auth.ApiKeyAuth
import sibApi.AccountApi


@RestController
@RequestMapping("/api/v1/catalog/")
class RentControllerV1(
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

    @PostMapping("brevo")
    fun sendEmail() {
        val defaultClient = Configuration.getDefaultApiClient()
        val apiKey = defaultClient.getAuthentication("api-key") as ApiKeyAuth
        apiKey.apiKey = "apiKey"

        val accApi = AccountApi()
        val result = accApi.account
        println(result)
    }
}
