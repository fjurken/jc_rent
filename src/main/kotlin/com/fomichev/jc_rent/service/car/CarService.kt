package com.fomichev.jc_rent.service.car

import com.fomichev.jc_rent.controller.dto.request.CarRentRequest
import com.fomichev.jc_rent.controller.dto.request.UpdateCarRequest
import com.fomichev.jc_rent.model.Car

interface CarService {

    fun addNewCar(car: Car): Car

    fun updateCar(request: UpdateCarRequest)

    fun getCar(carId: Long): Car?

    fun getAllCars(): List<Car>?

    fun deleteCar(carId: Long)

    fun getListOfAvailableCars(): List<Car>?

    fun requestRentCar(request: CarRentRequest)

    fun finishRentCar(carId: Long)
}
