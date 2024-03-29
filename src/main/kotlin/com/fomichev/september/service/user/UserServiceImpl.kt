package com.fomichev.september.service.user

import com.fomichev.september.enum.EntityStatus
import com.fomichev.september.model.Role
import com.fomichev.september.model.User
import com.fomichev.september.repository.RoleRepository
import com.fomichev.september.repository.UserRepository
import mu.KotlinLogging
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository,
    private val passwordEncoder: BCryptPasswordEncoder
) : UserService {

    val log = KotlinLogging.logger { }

    @Transactional
    override fun register(user: User): User {
        val role = roleRepository.findByName("ROLE_USER")
        val userRoles = mutableListOf<Role>()
        userRoles.add(role)

        user.password = passwordEncoder.encode(user.password)
        user.roles = userRoles
        user.status = EntityStatus.ACTIVE

        val registeredUser = userRepository.save(user)

        log.info("User $registeredUser was successfully registered")

        return registeredUser
    }

    @Transactional
    override fun getAll(): List<User> {
        val result = userRepository.findAll()
        log.info("Users found: ${result.size}")
        return result
    }

    @Transactional
    override fun findByUsername(username: String): User? {
        val result = userRepository.findByUsername(username)
        log.info("User found by username: $result")
        return result
    }

    @Transactional
    override fun findById(id: Long): User? {
        val result = userRepository.findById(id).orElse(null)
        if (result == null) {
            log.warn("User not found by id $id")
        }
        log.info("User $result found by id $id")
        return result
    }

    @Transactional
    override fun delete(id: Long) {
        userRepository.deleteById(id)
        log.info("User successfully deleted by id $id")
    }
}
