package com.fomichev.jc_rent.service.account

import com.fomichev.jc_rent.controller.dto.request.UserRequest
import com.fomichev.jc_rent.exception.EmailWasAlreadyRegisteredException
import com.fomichev.jc_rent.model.User
import com.fomichev.jc_rent.repository.RoleRepository
import com.fomichev.jc_rent.repository.UserRepository
import com.fomichev.jc_rent.security.SecurityService
import com.fomichev.jc_rent.service.notification.email.EmailNotificationService
import com.fomichev.jc_rent.service.notification.email.templates.EmailTemplate
import com.fomichev.jc_rent.service.user.UserService
import mu.KotlinLogging
import org.apache.commons.lang3.RandomStringUtils
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AccountServiceImpl(
    private val securityService: SecurityService,
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository,
    private val userService: UserService,
    private val emailNotificationService: EmailNotificationService
) : AccountService {

    val log = KotlinLogging.logger {}

//    @Transactional
//    override fun getClientByEmail(email: String): Client {
//        return clientRepository.getClientByEmail(email) ?: throw UnknownEmailException(
//            "Client with email $email hasn't been registered!" +
//                "\nPlease, create new account.",
//            null
//        )
//    }

    /**
     * Sign up
     */
    @Transactional
    override fun signUp(request: UserRequest) {
        if (userRepository.findByUsername(request.email) == null) {
            val newUser = userService.register(
                User(
                    username = request.email,
                    password = request.password,
                    firstName = request.firstName,
                    lastName = request.lastName
                )
            )
//        if (userRepository.findByUsername(request.email) == null) {
//            val newUser = userRepository.save(
//                User(
//                    username = request.email,
//                    password = request.password,
//                    firstName = request.firstName,
//                    lastName = request.lastName
//                )
//            )
//            roleRepository.save(Role(Roles.USER.name, listOf(newUser)))
//        log.info("New client ${newUser.username} with id=${newUser.id} successfully signed up!")
//        return newUser
        } else throw EmailWasAlreadyRegisteredException(
            "Client with email ${request.email} was already registered" +
                "\nPlease, log in or click \"Forgot my password\"",
            null
        )
    }

//    /**
//     * Log in
//     */
//    override fun logIn(request: UserRequest): Boolean {
//        val clientId = clientRepository.getClientByEmail(request.email)?.id
//            ?: throw UnknownEmailException(
//                "Client with email ${request.email} hasn't been registered!" +
//                    "\nPlease, create new account.",
//                null
//            )
//        val encryptedPass = clientBackRepository.getDataByClientId(clientId)
//        return encryptedPass == securityService.encryptPassword(request.password!!)
//    }

    /**
     * Restore password
     */
    override fun restorePassword(username: String) {
        val user = userService.findByUsername(username)
            ?: throw UsernameNotFoundException("User with username $username not found")

        val newPass = RandomStringUtils.randomAscii(12)
        user.password = BCryptPasswordEncoder().encode(newPass)
        userService.save(user)
        emailNotificationService.notify(user, EmailTemplate.RESTORE_PASSWORD, mapOf(Pair("pass", newPass)))
    }
}
