package com.fomichev.september.controller

import com.fomichev.september.controller.dto.request.CarRequest
import com.fomichev.september.controller.dto.request.RentRequest
import com.fomichev.september.controller.dto.request.UpdateCarRequest
import com.fomichev.september.controller.dto.response.RentResponse
import com.fomichev.september.mapper.RentMapper
import com.fomichev.september.model.Car
import com.fomichev.september.model.Rent
import com.fomichev.september.service.AbstractService
import com.fomichev.september.service.car.CarService
import com.fomichev.september.service.price.PriceService
import com.fomichev.september.service.rent.CarRentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Administrator
 */
@RestController
@RequestMapping("/api/v1/admin/")
class AdminController(
    private val carService: CarService,
    private val carRentService: CarRentService,
    private val priceService: PriceService,
    private val rentMapper: RentMapper
) : AbstractService() {

    @PostMapping("/catalog/add_car")
    fun addCar(@RequestBody car: CarRequest) {
        carService.addNewCar(
            Car(
                brand = car.brand,
                model = car.model,
                carType = car.carType,
                color = car.color,
                engineType = car.engineType,
                engineCapacity = car.engineCapacity,
                enginePower = car.enginePower,
                transmission = car.transmission,
                licencePlate = car.licencePlate
            )
        )
    }

    @PatchMapping("catalog/update_car")
    fun updateCar(@RequestBody car: UpdateCarRequest) {
        carService.updateCar(car)
    }

    @GetMapping("catalog/all_cars")
    fun getAllCars(): List<Car> {
        return carService.getAllCars()?: listOf()
    }

    @DeleteMapping("catalog/delete/{carId}")
    fun deleteCar(@PathVariable carId: Long) {
        carService.deleteCar(carId)
    }

    @PostMapping("rent")
    fun rent(@RequestBody request: RentRequest) {
        carRentService.startRent(request)
    }

    @GetMapping("rent/active")
    fun getActiveRentList(): List<RentResponse> {
        val activeRents =  carRentService.getActiveRentList()
        return rentMapper.rentToRentResponse(activeRents)
    }

    @PatchMapping("rent/finish/{rentId}")
    fun finishRent(@PathVariable rentId: Long) {
        carRentService.finishRent(rentId)
    }

    @PatchMapping("/price/update/{carId}")
    fun updateCarPrice(
        @PathVariable carId: Long,
        @RequestBody price: Double
    ): ResponseEntity<*> {
        priceService.updateCarPrice(carId, price)
        val newPrice = priceService.getPriceByCarId(carId)
        return ResponseEntity.ok().body("New price for carId=$carId is $newPrice")
    }
}
