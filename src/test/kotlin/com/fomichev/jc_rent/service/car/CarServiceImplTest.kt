package com.fomichev.jc_rent.service.car

import com.fomichev.jc_rent.configuration.JwtUtils
import com.fomichev.jc_rent.repository.CarRepository
import com.fomichev.jc_rent.service.notification.email.EmailNotificationService
import com.fomichev.jc_rent.service.price.PriceService
import com.fomichev.jc_rent.service.rent.CarRentService
import com.fomichev.jc_rent.service.user.UserService
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class CarServiceImplTest {

    private val carRepository: CarRepository = mockk(relaxed = true)
    private val carRentService: CarRentService = mockk()
    private val emailNotificationService: EmailNotificationService = mockk()
    private val userService: UserService = mockk()
    private val priceService: PriceService = mockk()
    private val jwtUtils: JwtUtils = mockk()

    private val carServiceImpl =
        CarServiceImpl(
            carRepository,
            carRentService,
            emailNotificationService,
            userService,
            priceService,
            jwtUtils
        )

    @Test
    fun addNewCar() {
    }

    @Test
    fun updateCar() {
    }

    @Test
    fun getCar() {
    }

    @Test
    @DisplayName("get all cars")
    fun getAllCars() {
        carServiceImpl.getAllCars()
        verify(exactly = 1) { carRepository.findAll() }
    }

    @Test
    fun deleteCar() {
    }

    @Test
    fun getListOfAvailableCars() {
    }

    @Test
    fun requestRentCar() {
    }

    @Test
    fun finishRentCar() {
    }
}
