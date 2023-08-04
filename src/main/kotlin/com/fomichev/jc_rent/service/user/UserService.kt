package com.fomichev.jc_rent.service.user

import com.fomichev.jc_rent.model.User
import org.springframework.stereotype.Service

@Service
interface UserService {

    fun register(user: User)

    fun getAll(): List<User>

    fun save(user: User)

    fun findByUsername(username: String): User?

    fun findById(id: Long): User?

    fun delete(id: Long)
}
