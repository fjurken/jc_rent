package com.fomichev.september.controller

import com.fomichev.september.controller.dto.request.UserRequest
import com.fomichev.september.service.account.AccountService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/")
class AccountController(
    private val accountService: AccountService
) {

    @GetMapping("/sign_up")
    fun getSignUpPage(): String {
        return "sign_up"
    }

    @PostMapping("/sign_up")
    fun signIn(
        @RequestBody request: UserRequest
    ) {
        accountService.signUp(request)
    }

    @GetMapping("/log_in")
    fun getLogInPage(): String {
        return "log_in"
    }

    @PostMapping("/log_in")
    fun logIn(
        @RequestBody request: UserRequest
    ): ResponseEntity<HttpStatus> {
        return if (accountService.logIn(request))
            ResponseEntity(HttpStatus.OK)
        else ResponseEntity(HttpStatus.UNAUTHORIZED)
    }

    @GetMapping("/account")
    fun getAccountPage(): String {
        return "account"
    }
}
