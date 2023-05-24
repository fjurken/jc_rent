package com.fomichev.september.service.account.admin

import com.fomichev.september.controller.dto.request.UserRequest

interface AdminAccountService {

    fun logIn(request: UserRequest): Boolean
}
