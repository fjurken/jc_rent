package com.fomichev.september.controller

import com.fomichev.september.controller.dto.request.UserRequest
import com.fomichev.september.exception.EmailWasAlreadyRegisteredException
import com.fomichev.september.exception.UnknownEmailException
import com.fomichev.september.model.Role
import com.fomichev.september.model.User
import com.fomichev.september.repository.RoleRepository
import com.fomichev.september.repository.UserRepository
import com.fomichev.september.service.notification.email.EmailNotificationService
import com.fomichev.september.service.user.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Instant
import java.util.*

//@CrossOrigin(origins = ["http://localhost:5173"])
@RestController
@RequestMapping("/api/v1/auth/")
class AccountController(
    private val userService: UserService,
    private val emailNotificationService: EmailNotificationService,
    private val userRepository: UserRepository,
    private val passwordEncoder: BCryptPasswordEncoder,
    private val roleRepository: RoleRepository
) {

    @PostMapping("signup")
//    fun signIn(@RequestBody request: UserRequest): ResponseEntity<*> {
    fun signIn(@RequestBody request: UserRequest) {
        val role = roleRepository.findByName("ROLE_USER")
        val userRoles = mutableListOf<Role>()
        userRoles.add(role)

        userRepository.save(
            User(
                username = request.email,
                password = passwordEncoder.encode(request.password),
                firstName = request.firstName,
                lastName = request.lastName,
                roles = userRoles
            )
        )
//        if (request.password.equals("")) return ResponseEntity.badRequest().body("Field \"password\" can't be empty!")
//        try {
//          Save new client data
//            val newUser = userService.register(
//                User(
//                    username = request.email,
//                    password = request.password,
//                    firstName = request.firstName,
//                    lastName = request.lastName
//                )
//            )
//            Notify new our client with Welcome template email
//            emailNotificationService.notify(newUser, EmailTemplate.WELCOME, null)
//            return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(
//                    "${request.email} was successfully registered!" +
//                            "\n${request.email}, welcome to the Journey Car Rent"
//                )
//        } catch (ar: EmailWasAlreadyRegisteredException) {
        // Client with requested email was already registered
//            return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(ar.message)
//        }
    }

    @GetMapping("/login")
    fun getLogInPage(): String {
        return "log_in"
    }

//    @PostMapping("/log_in")
//    fun logIn(@RequestBody request: UserRequest): ResponseEntity<*> {
//        if (request.password.equals("")) return ResponseEntity.badRequest().body("Field \"password\" can't be empty!")
//        return try {
//            if (accountService.logIn(request))
//                ResponseEntity.status(HttpStatus.OK).body("")
//            else ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email or password incorrect!")
//        } catch (ue: UnknownEmailException) {
//            ResponseEntity
//                .ok()
//                .body(ue.message)
//        }
//    }

    @PostMapping("/restore_password")
    fun restorePassword(@RequestBody request: UserRequest): ResponseEntity<*> {
        return try {
//            val client = accountService.getClientByEmail(request.email)
//            val pass = accountService.restorePassword(client)
//            emailNotificationService.notify(client, EmailTemplate.RESTORE_PASSWORD, mapOf(Pair("pass", pass.first)))
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
