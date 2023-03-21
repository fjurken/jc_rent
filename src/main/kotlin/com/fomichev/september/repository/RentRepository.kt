package com.fomichev.september.repository

import com.fomichev.september.model.Rent
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RentRepository: JpaRepository<Rent, Long> {
}