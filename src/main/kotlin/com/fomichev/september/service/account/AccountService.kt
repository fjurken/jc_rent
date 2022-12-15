package com.fomichev.september.service.account

import com.fomichev.september.controller.dto.request.UserRequest

interface AccountService {

    fun signUp(request: UserRequest)

    fun logIn(request: UserRequest): Boolean

}
