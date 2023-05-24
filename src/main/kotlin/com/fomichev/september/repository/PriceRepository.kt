package com.fomichev.september.repository

import com.fomichev.september.model.Price
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface PriceRepository : JpaRepository<Price, Long> {

    @Query(
        """
        select p.price
        from Price p
        where car_id = :carId
    """
    )
    fun getPriceByCar(@Param("carId") carId: Long): Double?

    @Modifying
    @Query(
        """
        update Price p
        set p.price = :newPrice
        where p.carId = :carId
    """
    )
    fun updateCarPrice(@Param("carId") carId: Long, @Param("newPrice") newPrice: Double)
}
