package com.fomichev.september.service.price

import com.fomichev.september.controller.dto.request.PriceRequest

interface PriceService {

    fun updateCarPrice(request: PriceRequest)

    fun getPriceByCarId(carId: Long): Double?

    fun getPricesOfCarsByIds(carIds: List<Long?>): Map<Long, Double>
}
