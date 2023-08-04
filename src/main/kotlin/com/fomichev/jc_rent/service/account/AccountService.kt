package com.fomichev.jc_rent.service.account

import com.fomichev.jc_rent.controller.dto.request.UserRequest

interface AccountService {

    fun signUp(request: UserRequest)

//    fun logIn(request: UserRequest): Boolean

    fun restorePassword(username: String)

//    fun getClientByEmail(email: String): Client
}
