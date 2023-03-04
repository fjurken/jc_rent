package com.fomichev.september.service.account

import com.fomichev.september.controller.dto.request.UserRequest
import com.fomichev.september.model.Client

interface AccountService {

    fun signUp(request: UserRequest): Client

    fun logIn(request: UserRequest): Boolean

    fun restorePassword(request: UserRequest)
}
