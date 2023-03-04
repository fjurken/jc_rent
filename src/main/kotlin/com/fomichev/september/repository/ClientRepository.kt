package com.fomichev.september.repository

import com.fomichev.september.model.Client
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ClientRepository : JpaRepository<Client, Long> {

    @Query("SELECT c FROM Client c WHERE c.email = :email")
    fun getClientByEmail(@Param("email") email: String): Client?
}
