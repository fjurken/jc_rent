package com.fomichev.september.service.account.admin

import com.fomichev.september.controller.dto.request.UserRequest
import com.fomichev.september.service.account.AccountService
import org.springframework.stereotype.Service

@Service
class AdminAccountServiceImpl(
    private val accountService: AccountService
) : AdminAccountService {

    override fun logIn(request: UserRequest): Boolean {
        return accountService.logIn(request)
    }
}
