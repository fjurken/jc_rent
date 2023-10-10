package com.fomichev.jc_rent.service.user

import com.fomichev.jc_rent.enum.EntityStatus
import com.fomichev.jc_rent.enum.Roles
import com.fomichev.jc_rent.model.Role
import com.fomichev.jc_rent.model.User
import com.fomichev.jc_rent.repository.RoleRepository
import com.fomichev.jc_rent.repository.UserRepository
import mu.KLogging
import mu.KotlinLogging
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository,
    private val passwordEncoder: BCryptPasswordEncoder
) : UserService, KLogging() {

    @Transactional
    override fun register(user: User) {
        val role = roleRepository.findByName(Roles.USER.text)
        val userRoles = mutableListOf<Role>()
        userRoles.add(role)

        user.password = passwordEncoder.encode(user.password)
        user.roles = userRoles
        user.status = EntityStatus.ACTIVE

        val registeredUser = userRepository.save(user)

        logger.info("User $registeredUser was successfully registered")
//
//        return registeredUser
    }

    @Transactional
    override fun getAll(): List<User> {
        val result = userRepository.findAll()
        logger.info("Users found: ${result.size}")
        return result
    }

    @Transactional
    override fun findByUsername(username: String): User? {
        val result = userRepository.findByUsername(username)
        logger.info("User found by username: $result")
        return result
    }

    @Transactional
    override fun findById(id: Long): User? {
        val result = userRepository.findById(id).orElse(null)
        if (result == null) {
            logger.warn("User not found by id $id")
        }
        logger.info("User $result found by id $id")
        return result
    }

    @Transactional
    override fun delete(id: Long) {
        userRepository.deleteById(id)
        logger.info("User successfully deleted by id $id")
    }

    @Transactional
    override fun save(user: User) {
        userRepository.save(user)
    }
}
