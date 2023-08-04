package com.fomichev.jc_rent.repository

import com.fomichev.jc_rent.model.Car
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface CarRepository : JpaRepository<Car, Long> {

    @Query(
        """
            select c from Car c
            where c.status = com.fomichev.jc_rent.enum.EntityStatus.ACTIVE
        """
    )
    fun getAvailableCars(): List<Car>?
}
