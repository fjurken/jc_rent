package com.fomichev.september.repository

import com.fomichev.september.model.Rent
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface RentRepository : JpaRepository<Rent, Long> {

    @Query(
        """
        select r from Rent r
        where r.finished = false
    """
    )
    fun getActiveRentList(): List<Rent>

    @Modifying
    @Query(
        """
            update Rent r
            set r.finished = true
            where r.id = :rentId
        """
    )
    fun finishRent(@Param("rentId") rentId: Long)
}
