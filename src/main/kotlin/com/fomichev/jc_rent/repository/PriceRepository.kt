package com.fomichev.jc_rent.repository

import com.fomichev.jc_rent.model.Price
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
    """,
        nativeQuery = true
    )
    fun getPriceByCar(@Param("carId") carId: Long): Double?

    @Query(
        """
            select car_id, price
            from price p
            where car_id in :carIds
            order by car_id
        """,
        nativeQuery = true
    )
    fun getListPricesByCarIds(@Param("carIds") carIds: List<Long>): List<Map<String, Any>>
}
