package com.fomichev.september.service.user

import com.fomichev.september.model.User
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
