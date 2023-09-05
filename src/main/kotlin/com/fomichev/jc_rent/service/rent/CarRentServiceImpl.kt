package com.fomichev.jc_rent.service.rent

import com.fomichev.jc_rent.configuration.JwtUtils
import com.fomichev.jc_rent.controller.dto.request.CarRentRequest
import com.fomichev.jc_rent.enum.EntityStatus
import com.fomichev.jc_rent.exception.CarAlreadyOccupied
import com.fomichev.jc_rent.model.Rent
import com.fomichev.jc_rent.repository.RentRepository
import com.fomichev.jc_rent.service.AbstractService
import com.fomichev.jc_rent.service.utils.prettyDate
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant
import java.time.temporal.ChronoUnit
import com.fomichev.jc_rent.service.rent.BusyPeriod as BusyPeriod

@Service
class CarRentServiceImpl(
    private val rentRepository: RentRepository,
    private val jwtUtils: JwtUtils
) : CarRentService, AbstractService() {

    @Transactional
    override fun getRentById(rentId: Long): Rent? {
        return rentRepository.findByIdOrNull(rentId)
    }

    /**
     * Create new request for car rent
     * @param request - request from client
     * */
    @Transactional
    override fun requestRent(request: CarRentRequest) {
        log.info("New car rent request $request")
        /*check do we have intersections with other clients or not*/
        checkDatesIntersections(request.carId, request.startDate, request.endDate)
        /*create and save new rent request*/
        val newRequest = rentRepository.save(Rent(request.carId, jwtUtils.username, request.startDate, request.endDate))
        log.info("Created new car rent request $newRequest")
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

    /**
     * Check do we have intersections with other clients or not
     * @param carId - car ID
     * @param startDate - start date
     * @param endDate - end date
     * */
    private fun checkDatesIntersections(carId: Long, startDate: Instant, endDate: Instant) {
        log.info("Checking rent request intersections by dates [$startDate - $endDate] and car ID=$carId")
        var date: Instant
        var bufferDate: Instant? = null
        val intersectionDates = mutableListOf<Instant>()

        /*Intersected periods from database by dates and car ID*/
        val intersections = rentRepository.getIntersections(carId, startDate, endDate)

        /*found something*/
        if (intersections.isNotEmpty()) {
            date = startDate
            while (date <= endDate) {
                intersections.forEach { otherPeriod ->
                    if ((date.isAfter(otherPeriod.startDate) || date == otherPeriod.startDate) &&
                        (date.isBefore(otherPeriod.endDate) || date == otherPeriod.endDate)
                    ) {
                        intersectionDates.add(date)
                    }
                }
                date = date.plus(1, ChronoUnit.DAYS)
            }

            val periods = mutableListOf<BusyPeriod<Instant, Instant?>>()
            var periodStart: Instant? = null
            var currentPeriod: BusyPeriod<Instant, Instant?>

//            intersectionDates.forEach {
//                /*Start of period*/
//                if (periodStart == null) {
//                    periodStart = it
//                    periods.add(Pair(periodStart!!, null))
//                    currentPeriod = Pair(periodStart!!, null)
//                }
//
//                if (bufferDate == null) {
//                    bufferDate = it
//                } else {
//                    if (bufferDate!!.plus(1, ChronoUnit.DAYS) != it) {
//                        currentPeriod.end = it
//                    }
//                }
//            }

//            periods.removeIf { it.second == null }

            var stringDates = ""
            intersectionDates.forEach { day -> stringDates += day.prettyDate() + ", " }
                .also { stringDates = stringDates.removeSuffix(", ") }
            throw CarAlreadyOccupied(
                "The car is already occupied that days: $stringDates", null
            )
        }
    }
}

class BusyPeriod<out A, out B>(
    val start: A,
    val end: B?
)
