package com.fomichev.jc_rent.service.account

import com.fomichev.jc_rent.controller.dto.request.UserRequest
import com.fomichev.jc_rent.model.User

interface AccountService {

    fun signUp(request: UserRequest): User

//    fun logIn(request: UserRequest): Boolean

    fun restorePassword(username: String)

//    fun getClientByEmail(email: String): Client
}
