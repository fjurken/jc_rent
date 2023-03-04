package com.fomichev.september.service.client

import com.fomichev.september.model.Client

interface ClientService {

    fun getById(id: Long): Client
}
