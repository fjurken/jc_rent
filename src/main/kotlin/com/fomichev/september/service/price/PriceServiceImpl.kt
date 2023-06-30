package com.fomichev.september.service.price

import com.fomichev.september.controller.dto.request.PriceRequest
import com.fomichev.september.model.Price
import com.fomichev.september.repository.PriceRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PriceServiceImpl(
    private val priceRepository: PriceRepository
) : PriceService {

    @Transactional
    override fun updateCarPrice(request: PriceRequest) {
        val oldPrice = priceRepository.getPriceByCar(request.carId)
            ?: priceRepository.save(Price(carId = request.carId, price = request.price))
    }

    @Transactional
    override fun getPriceByCarId(carId: Long): Double? {
        return priceRepository.getPriceByCar(carId)
    }

    @Transactional
    override fun getPricesOfCarsByIds(carIds: List<Long?>): Map<Long, Double> {
        val prices: List<Map<String, Any>> = priceRepository.getListPricesByCarIds(carIds.filterNotNull())
        return prices.associate { it["carId"] as Long to it["price"] as Double }
    }
}
