package com.fomichev.september.service.price

interface PriceService {

    fun updateCarPrice(carId: Long, newPrice: Double)

    fun getPriceByCarId(carId: Long): Double?
}
