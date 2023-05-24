package com.fomichev.september.service.price

import com.fomichev.september.repository.PriceRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PriceServiceImpl(
    private val priceRepository: PriceRepository
) : PriceService {

    @Transactional
    override fun updateCarPrice(carId: Long, newPrice: Double) {
        val oldPrice = priceRepository.getPriceByCar(carId)

            ?: priceRepository.updateCarPrice(carId, newPrice)
    }

    @Transactional
    override fun getPriceByCarId(carId: Long): Double? {
        return priceRepository.getPriceByCar(carId)
    }
}
