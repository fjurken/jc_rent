package com.fomichev.jc_rent.service.price

import com.fomichev.jc_rent.controller.dto.request.PriceRequest

interface PriceService {

    fun updateCarPrice(request: PriceRequest)

    fun getPriceByCarId(carId: Long): Double?

    fun getPricesOfCarsByIds(carIds: List<Long?>): Map<Long, Double>
}
