package com.fomichev.september.service.rent

import com.fomichev.september.controller.dto.request.RentRequest
import com.fomichev.september.model.Rent
import com.fomichev.september.repository.RentRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CarRentServiceImpl(
    private val rentRepository: RentRepository
) : CarRentService {

    @Transactional
    override fun startRent(request: RentRequest) {
        val rent = Rent(
            carId = request.carId,
            startDate = request.startDate,
            endDate = request.endDate
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
