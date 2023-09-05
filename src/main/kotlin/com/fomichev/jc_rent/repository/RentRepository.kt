package com.fomichev.jc_rent.repository

import com.fomichev.jc_rent.model.Rent
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.Instant

@Repository
interface RentRepository : JpaRepository<Rent, Long> {

    @Query(
        """
        select r from Rent r
        where r.carId = :carId
        and ((endDate >= :startDate and startDate <= :endDate) or (endDate <= :startDate and startDate >= :endDate))
        """
    )
    fun getIntersections(
        @Param("carId") carId: Long,
        @Param("startDate") startDate: Instant,
        @Param("endDate") endDate: Instant
    ): List<Rent>

    @Query(
        """
        select r from Rent r
        where r.finishedDate = null
        and r.status = com.fomichev.jc_rent.enum.EntityStatus.NOT_ACTIVE
        """
    )
    fun getRequestedRentList(): List<Rent>

    @Query(
        """
        select r from Rent r
        where r.finishedDate = null
        and r.status = com.fomichev.jc_rent.enum.EntityStatus.ACTIVE
    """
    )
    fun getActiveRentList(): List<Rent>

    @Modifying
    @Query(
        """
            update Rent r
            set r.finished_date = current_timestamp() 
            and r.status = com.fomichev.jc_rent.enum.EntityStatus.NOT_ACTIVE
            where r.id = :rentId
        """,
        nativeQuery = true
    )
    fun finishRent(@Param("rentId") rentId: Long)
}
