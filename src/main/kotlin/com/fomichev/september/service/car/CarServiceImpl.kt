package com.fomichev.september.service.car

import com.fomichev.september.controller.dto.request.CarRentRequest
import com.fomichev.september.controller.dto.request.UpdateCarRequest
import com.fomichev.september.exception.NoSuchCarException
import com.fomichev.september.model.Car
import com.fomichev.september.repository.CarRepository
import com.fomichev.september.service.rent.CarRentService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CarServiceImpl(
    private val carRepository: CarRepository,
    private val carRentService: CarRentService
) : CarService {

    @Transactional
    override fun addNewCar(car: Car) {
        carRepository.save(car)
    }

    @Transactional
    override fun updateCar(request: UpdateCarRequest) {
        val car = getCar(request.id) ?: throw NoSuchCarException("Car with Id=${request.id} doesn't exist!", null)
        if (request.color != null) car.color = request.color
        if (request.licencePlate != null) car.licencePlate = request.licencePlate
        if (request.status != null) car.status = request.status
        carRepository.save(car)
    }

    @Transactional
    override fun getCar(carId: Long): Car? {
        return carRepository.findById(carId).get()
    }

    @Transactional
    override fun getAllCars(): List<Car>? {
        return carRepository.findAll()
    }

    @Transactional
    override fun deleteCar(carId: Long) {
        carRepository.deleteById(carId)
    }

    @Transactional
    override fun getListOfAvailableCars(): List<Car>? {
        return carRepository.getAvailableCars()
    }

    @Transactional
    override fun requestRentCar(request: CarRentRequest) {
        println("Request for rent car=${getCar(request.carId)}, from: ${request.startDate} to: ${request.endDate}")
        carRentService.requestRent(request)
    }

    @Transactional
    override fun finishRentCar(carId: Long) {
        TODO("Not yet implemented")
    }
}
