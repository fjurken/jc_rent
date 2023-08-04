package com.fomichev.jc_rent.service.rent

import com.fomichev.jc_rent.controller.dto.request.CarRentRequest
import com.fomichev.jc_rent.enum.EntityStatus
import com.fomichev.jc_rent.model.Rent
import com.fomichev.jc_rent.repository.RentRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CarRentServiceImpl(
    private val rentRepository: RentRepository
) : CarRentService {

    @Transactional
    override fun getRentById(rentId: Long): Rent? {
        return rentRepository.findByIdOrNull(rentId)
    }

    @Transactional
    override fun requestRent(request: CarRentRequest) {
        val rent = Rent(
            carId = request.carId,
            startDate = request.startDate,
            endDate = request.endDate,

        )
        rentRepository.save(rent)
    }

    @Transactional
    override fun startRent(rentId: Long) {
        val rent = rentRepository.findById(rentId).get()
        rent.status = EntityStatus.ACTIVE
        rentRepository.save(rent)
    }

    @Transactional
    override fun finishRent(rentId: Long) {
        rentRepository.finishRent(rentId)
    }

    @Transactional
    override fun getRequestedRentList(): List<Rent> {
        return rentRepository.getRequestedRentList()
    }

    @Transactional
    override fun getActiveRentList(): List<Rent> {
        return rentRepository.getActiveRentList()
    }
}
