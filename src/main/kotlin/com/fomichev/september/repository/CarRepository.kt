package com.fomichev.september.repository

import com.fomichev.september.model.Car
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CarRepository : JpaRepository<Car, Long>
