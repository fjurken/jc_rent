package com.fomichev.september.service.account

import com.fomichev.september.controller.dto.request.UserRequest
import com.fomichev.september.exception.EmailWasAlreadyRegisteredException
import com.fomichev.september.model.User
import com.fomichev.september.repository.RoleRepository
import com.fomichev.september.repository.UserRepository
import com.fomichev.september.security.SecurityService
import com.fomichev.september.service.user.UserService
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AccountServiceImpl(
    private val securityService: SecurityService,
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository,
    private val userService: UserService
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

//    /**
//     * Restore password
//     */
//    override fun restorePassword(client: Client): Pair<String, String> {
//        val clientData = clientBackRepository.getByClientId(client.id!!)
//            ?: throw ClientBackDataIsEmptyException(
//                "ClientBack data for client $client is empty!", null
//            )
//        val pass = RandomString.make()
//        val encryptedPass = securityService.encryptPassword(pass)
//        clientData.data = encryptedPass
//        clientBackRepository.save(clientData)
//        return Pair(pass, encryptedPass)
//    }
}
