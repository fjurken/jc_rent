package com.fomichev.jc_rent.repository

import com.fomichev.jc_rent.model.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository : JpaRepository<Role, Long> {

    fun findByName(name: String): Role
}
