package com.fomichev.september.service.car

import com.fomichev.september.model.Car
import com.fomichev.september.repository.CarRepository
import com.fomichev.september.service.rent.CarRentService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

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
    override fun getCar(carId: Long): Car? {
        return carRepository.findById(carId).get()
    }

    @Transactional
    override fun deleteCar(carId: Long) {
        carRepository.deleteById(carId)
    }

    @Transactional
    override fun getListOfAvailableCars(): List<Car> {
        return carRepository.findAll()
    }

    @Transactional
    override fun startRentCar(carId: Long, startDateTime: Instant, endDateTime: Instant) {
        println("Request for rent car=${getCar(carId)}, from: $startDateTime to: $endDateTime")
        carRentService.startRent(carId, startDateTime, endDateTime)
    }

    @Transactional
    override fun finishRentCar(carId: Long) {
        TODO("Not yet implemented")
    }
}
