package com.fomichev.september.repository

import com.fomichev.september.model.Laptop
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LaptopRepository : JpaRepository<Laptop, Long>
