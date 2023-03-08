package com.fomichev.september.controller

import com.fomichev.september.controller.dto.request.UserRequest
import com.fomichev.september.exception.EmailWasAlreadyRegisteredException
import com.fomichev.september.exception.UnknownEmailException
import com.fomichev.september.service.account.AccountService
import com.fomichev.september.service.notification.email.EmailNotificationService
import com.fomichev.september.service.notification.email.templates.EmailTemplate
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class AccountController(
    private val accountService: AccountService,
    private val emailNotificationService: EmailNotificationService,
) {

    @GetMapping("/sign_up")
    fun getSignUpPage(): String {
        return "sign_up"
    }

    @PostMapping("/sign_up")
    fun signIn(@RequestBody request: UserRequest): ResponseEntity<*> {
        if (request.password.equals("")) return ResponseEntity.badRequest().body("Field \"password\" can't be empty!")
        try {
            // Save new client data
            val client = accountService.signUp(request)
            // Notify new our client with Welcome template email
            emailNotificationService.notify(client, EmailTemplate.WELCOME, null)
            return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                    "${request.email} was successfully registered!" +
                        "\n${request.name}, welcome to the Journey Car Rent"
                )
        } catch (ar: EmailWasAlreadyRegisteredException) {
            // Client with requested email was already registered
            return ResponseEntity
                .status(HttpStatus.OK)
                .body(ar.message)
        }
    }

    @GetMapping("/log_in")
    fun getLogInPage(): String {
        return "log_in"
    }

    @PostMapping("/log_in")
    fun logIn(@RequestBody request: UserRequest): ResponseEntity<*> {
        if (request.password.equals("")) return ResponseEntity.ok().body("Field \"password\" can't be empty!")
        return try {
            if (accountService.logIn(request))
                ResponseEntity.status(HttpStatus.OK).body("")
            else ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email or password incorrect!")
        } catch (ue: UnknownEmailException) {
            ResponseEntity
                .ok()
                .body(ue.message)
        }
    }

    @PostMapping("/restore_password")
    fun restorePassword(@RequestBody request: UserRequest): ResponseEntity<*> {
        return try {
            val client = accountService.getClientByEmail(request.email)
            val pass = accountService.restorePassword(client)
            emailNotificationService.notify(client, EmailTemplate.RESTORE_PASSWORD, mapOf(Pair("pass", pass.first)))
            ResponseEntity.ok().body("Password was restored, please, check your email")
        } catch (ue: UnknownEmailException) {
            ResponseEntity
                .ok()
                .body(ue.message)
        }
    }

    @GetMapping("/account")
    fun getAccountPage(): String {
        return "account"
    }
}
