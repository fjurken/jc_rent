package com.fomichev.september.service.account

import com.fomichev.september.controller.dto.request.UserRequest
import com.fomichev.september.model.ClientBack
import com.fomichev.september.model.Client
import com.fomichev.september.repository.ClientBackRepository
import com.fomichev.september.repository.ClientRepository
import com.fomichev.september.security.SecurityService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AccountServiceImpl(
    private val clientRepository: ClientRepository,
    private val clientBackRepository: ClientBackRepository,
    private val securityService: SecurityService
) : AccountService {
    @Transactional
    override fun signUp(request: UserRequest) {
        val client = clientRepository.save(
            Client(
                email = request.email,
                name = request.name!!
            )
        )
        clientBackRepository.save(
            ClientBack(
                client_id = client.id!!,
                data = securityService.encryptPassword(request.password)
            )
        )
    }

    override fun logIn(request: UserRequest): Boolean {
        val clientId = clientRepository.getClientByEmail(request.email).id
        val encryptedPass = clientBackRepository.getDataByClientId(clientId!!)
        return encryptedPass == securityService.encryptPassword(request.password)
    }
}
