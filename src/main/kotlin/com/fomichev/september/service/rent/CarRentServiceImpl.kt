package com.fomichev.september.service.rent

import com.fomichev.september.model.Rent
import com.fomichev.september.repository.RentRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

@Service
class CarRentServiceImpl(
    private val rentRepository: RentRepository
) : CarRentService {

    @Transactional
    override fun startRent(carId: Long, startDate: Instant, endDate: Instant) {
        val rent = Rent(
            carId = carId,
            startDate = startDate,
            endDate = endDate
        )
        rentRepository.save(rent)
    }

    @Transactional
    override fun finishRent(rentId: Long) {
        rentRepository.finishRent(rentId)
    }

    @Transactional
    override fun getActiveRentList(): List<Rent> {
        return rentRepository.getActiveRentList()
    }
}
