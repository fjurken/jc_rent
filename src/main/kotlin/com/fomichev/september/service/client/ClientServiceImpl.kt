package com.fomichev.september.service.client

import com.fomichev.september.model.Client
import com.fomichev.september.repository.ClientRepository

class ClientServiceImpl(
    private val clientRepository: ClientRepository
) : ClientService {
    override fun getById(id: Long): Client {
        return clientRepository.findById(id).get()
    }
}
