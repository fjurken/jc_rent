package com.fomichev.september.service.account

import com.fomichev.september.controller.dto.request.UserRequest
import com.fomichev.september.exception.EmailWasAlreadyRegisteredException
import com.fomichev.september.exception.UnknownEmailException
import com.fomichev.september.model.Client
import com.fomichev.september.model.ClientBack
import com.fomichev.september.repository.ClientBackRepository
import com.fomichev.september.repository.ClientRepository
import com.fomichev.september.security.SecurityService
import com.fomichev.september.service.notification.email.EmailNotificationService
import com.fomichev.september.service.notification.email.templates.EmailTemplate
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AccountServiceImpl(
    private val clientRepository: ClientRepository,
    private val clientBackRepository: ClientBackRepository,
    private val securityService: SecurityService,
    private val emailNotificationService: EmailNotificationService
) : AccountService {

    val log = KotlinLogging.logger {}

    /**
     * Sign up
     */
    @Transactional
    override fun signUp(request: UserRequest): Client {
        if (clientRepository.getClientByEmail(request.email) == null) {
            val client = clientRepository.save(
                Client(
                    email = request.email,
                    name = request.name!!
                )
            )
            log.info("New client ${client.name} with id=${client.id} successfully signed up!")
            clientBackRepository.save(
                ClientBack(
                    client_id = client.id!!,
                    data = securityService.encryptPassword(request.password!!)
                )
            )
            return client
        } else throw EmailWasAlreadyRegisteredException(
            "Client with email ${request.email} was already registered" +
                    "\nPlease, log in or click \"Forgot my password\"", null
        )
    }

    /**
     * Log in
     */
    override fun logIn(request: UserRequest): Boolean {
        val clientId = clientRepository.getClientByEmail(request.email)?.id
            ?: throw UnknownEmailException(
                "Client with email ${request.email} hasn't been registered!" +
                        "\nPlease, create new account.",
                null
            )
        val encryptedPass = clientBackRepository.getDataByClientId(clientId)
        return encryptedPass == securityService.encryptPassword(request.password!!)
    }

    /**
     * Restore password
     */
    override fun restorePassword(request: UserRequest) {
        val client = clientRepository.getClientByEmail(request.email) ?: throw UnknownEmailException(
            "Client with email ${request.email} hasn't been registered!" +
                    "\nPlease, create new account.",
            null
        )
        emailNotificationService.notify(client, EmailTemplate.RESTORE_PASSWORD)
    }
}
