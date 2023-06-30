package com.fomichev.september.controller

import com.fomichev.september.controller.dto.request.CarRequest
import com.fomichev.september.controller.dto.request.PriceRequest
import com.fomichev.september.controller.dto.request.UpdateCarRequest
import com.fomichev.september.controller.dto.response.RentResponse
import com.fomichev.september.enum.EntityStatus
import com.fomichev.september.exception.RentException
import com.fomichev.september.mapper.RentMapper
import com.fomichev.september.model.Car
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

    /*Add a new car*/
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

    /*Update car specs*/
    @PatchMapping("catalog/update_car")
    fun updateCar(@RequestBody car: UpdateCarRequest) {
        carService.updateCar(car)
    }

    /*Get list of all the cars*/
    @GetMapping("catalog/all_cars")
    fun getAllCars(): List<Car> {
        return carService.getAllCars() ?: listOf()
    }

    /*Delete a car*/
    @DeleteMapping("catalog/delete/{carId}")
    fun deleteCar(@PathVariable carId: Long) {
        carService.deleteCar(carId)
    }

    /**
     * Start rent a car
     * */
    @PatchMapping("rent/start/{rentId}")
    fun rent(@PathVariable rentId: Long) {
        val rent =
            carRentService.getRentById(rentId) ?: throw RentException("Rent with id=$rentId doesn't exist!", null)
        if (rent.status == EntityStatus.ACTIVE || rent.finishedDate != null) {
            throw RentException("Rent with id=$rentId was already finished!", null)
        }
        carRentService.startRent(rentId)
    }

    /**
     * Get list of all requested rents
     * */
    @GetMapping("rent/requested")
    fun getRequestedRentList(): List<RentResponse> {
        val requestedRents = carRentService.getRequestedRentList()
        return rentMapper.rentToRentResponse(requestedRents)
    }

    /**
     * Get list of all active rents
     * */
    @GetMapping("rent/active")
    fun getActiveRentList(): List<RentResponse> {
        val activeRents = carRentService.getActiveRentList()
        return rentMapper.rentToRentResponse(activeRents)
    }

    /**
     * Finish rent by ID
     * */
    @PatchMapping("rent/finish/{rentId}")
    fun finishRent(@PathVariable rentId: Long) {
        carRentService.finishRent(rentId)
    }

    @PatchMapping("/price/update")
    fun updateCarPrice(
        @RequestBody request: PriceRequest
    ): ResponseEntity<*> {
        priceService.updateCarPrice(request)
        val newPrice = priceService.getPriceByCarId(request.carId)
        return ResponseEntity.ok().body("New price for carId=${request.carId} is $newPrice")
    }
}
