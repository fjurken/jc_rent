package com.fomichev.jc_rent.service.account.admin

import com.fomichev.jc_rent.controller.dto.request.UserRequest

interface AdminAccountService {

    fun logIn(request: UserRequest): Boolean
}
