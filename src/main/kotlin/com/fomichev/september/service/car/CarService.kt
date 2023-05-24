package com.fomichev.september.service.car

import com.fomichev.september.model.Car
import java.time.Instant

interface CarService {

    fun addNewCar(car: Car)

    fun getCar(carId: Long): Car?

    fun getAllCars(): List<Car>?

    fun deleteCar(carId: Long)

    fun getListOfAvailableCars(): List<Car>

    fun startRentCar(carId: Long, startDateTime: Instant, endDateTime: Instant)

    fun finishRentCar(carId: Long)
}
