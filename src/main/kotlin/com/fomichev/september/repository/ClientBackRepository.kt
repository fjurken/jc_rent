package com.fomichev.september.repository

import com.fomichev.september.model.ClientBack
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ClientBackRepository : JpaRepository<ClientBack, Long> {

    @Query("select c from ClientBack c where c.client_id = :clientId")
    fun getByClientId(@Param("clientId") id: Long): ClientBack?

    @Query("select c.data from ClientBack c where c.client_id = :id")
    fun getDataByClientId(@Param("id") id: Long): String?
}
