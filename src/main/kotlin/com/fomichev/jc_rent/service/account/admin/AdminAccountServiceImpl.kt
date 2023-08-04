package com.fomichev.jc_rent.service.account.admin

import com.fomichev.jc_rent.controller.dto.request.UserRequest
import org.springframework.stereotype.Service

@Service
class AdminAccountServiceImpl(
//    private val accountService: AccountService
) : AdminAccountService {

    override fun logIn(request: UserRequest): Boolean {
//        return accountService.logIn(request)
        return true
    }
}
