package com.fomichev.september.repository

import com.fomichev.september.model.Price
import org.springframework.data.jpa.repository.JpaRepository
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

    @Query(
        """
            select p.carId as carId, p.price as price
            from Price p
            where car_id in :carIds
        """
    )
    fun getListPricesByCarIds(@Param("carIds") carIds: List<Long>): List<Map<String, Any>>

}
