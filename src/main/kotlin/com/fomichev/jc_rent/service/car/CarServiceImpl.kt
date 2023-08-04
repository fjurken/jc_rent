package com.fomichev.jc_rent.service.car

import com.fomichev.jc_rent.controller.dto.request.CarRentRequest
import com.fomichev.jc_rent.controller.dto.request.UpdateCarRequest
import com.fomichev.jc_rent.enum.EntityStatus
import com.fomichev.jc_rent.exception.NoSuchCarException
import com.fomichev.jc_rent.exception.RentException
import com.fomichev.jc_rent.model.Car
import com.fomichev.jc_rent.repository.CarRepository
import com.fomichev.jc_rent.service.notification.email.EmailNotificationService
import com.fomichev.jc_rent.service.notification.email.templates.EmailTemplate
import com.fomichev.jc_rent.service.price.PriceService
import com.fomichev.jc_rent.service.rent.CarRentService
import com.fomichev.jc_rent.service.user.UserService
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Duration
import javax.persistence.EntityNotFoundException

@Service
class CarServiceImpl(
    private val carRepository: CarRepository,
    private val carRentService: CarRentService,
    private val emailNotificationService: EmailNotificationService,
    private val userService: UserService,
    private val priceService: PriceService
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
        return carRepository.findAll().filter { it.status == EntityStatus.ACTIVE }
    }

    @Transactional
    override fun deleteCar(carId: Long) {
        val car = carRepository.getReferenceById(carId)
        car.status = EntityStatus.DELETED
        carRepository.save(car)
    }

    @Transactional
    override fun getListOfAvailableCars(): List<Car>? {
        return carRepository.getAvailableCars()
    }

    @Transactional
    override fun requestRentCar(request: CarRentRequest) {
        println("Request for rent car=${getCar(request.carId)}, from: ${request.startDate} to: ${request.endDate}")

        carRentService.requestRent(request)
        val user = userService.findByUsername(SecurityContextHolder.getContext().authentication.name)
        val car = getCar(request.carId)
        val duration = Duration.between(request.startDate, request.endDate).toDays()
        val price = duration * (priceService.getPriceByCarId(car!!.id!!)
            ?: throw RentException("Chosen car doesn't allowed to be rented, please try again later", null))

        /*Notification*/
        val payload = mutableMapOf<String, String>()
        payload["date"] = "dates from ${request.startDate} to ${request.endDate}"
        payload["car"] = "car is $car"
        payload["total"] = "total: days $duration, price $price"
        emailNotificationService.notify(user!!, EmailTemplate.RENT_REQUEST, payload)
    }

    @Transactional
    override fun finishRentCar(carId: Long) {
        TODO("Not yet implemented")
    }
}
