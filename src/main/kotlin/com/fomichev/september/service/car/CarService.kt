package com.fomichev.september.service.car

import com.fomichev.september.controller.dto.request.CarRentRequest
import com.fomichev.september.controller.dto.request.UpdateCarRequest
import com.fomichev.september.model.Car

interface CarService {

    fun addNewCar(car: Car)

    fun updateCar(request: UpdateCarRequest)

    fun getCar(carId: Long): Car?

    fun getAllCars(): List<Car>?

    fun deleteCar(carId: Long)

    fun getListOfAvailableCars(): List<Car>?

    fun requestRentCar(request: CarRentRequest)

    fun finishRentCar(carId: Long)
}
