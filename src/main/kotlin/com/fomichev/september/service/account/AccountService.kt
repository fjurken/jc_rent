package com.fomichev.september.service.account

import com.fomichev.september.controller.dto.request.UserRequest
import com.fomichev.september.model.User

interface AccountService {

    fun signUp(request: UserRequest): User

//    fun logIn(request: UserRequest): Boolean

//    fun restorePassword(client: Client): Pair<String, String>

//    fun getClientByEmail(email: String): Client
}
