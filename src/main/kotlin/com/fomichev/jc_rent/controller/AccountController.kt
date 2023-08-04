package com.fomichev.jc_rent.controller

import com.fomichev.jc_rent.controller.dto.request.UserRequest
import com.fomichev.jc_rent.enum.Roles
import com.fomichev.jc_rent.exception.EmailWasAlreadyRegisteredException
import com.fomichev.jc_rent.exception.UnknownEmailException
import com.fomichev.jc_rent.model.Role
import com.fomichev.jc_rent.model.User
import com.fomichev.jc_rent.repository.RoleRepository
import com.fomichev.jc_rent.repository.UserRepository
import com.fomichev.jc_rent.service.account.AccountService
import com.fomichev.jc_rent.service.notification.email.EmailNotificationService
import com.fomichev.jc_rent.service.notification.email.templates.EmailTemplate
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

// @CrossOrigin(origins = ["http://localhost:5173"])
@RestController
@RequestMapping("/api/v1/auth/")
class AccountController(
    private val userRepository: UserRepository,
    private val passwordEncoder: BCryptPasswordEncoder,
    private val roleRepository: RoleRepository,
    private val emailNotificationService: EmailNotificationService,
    private val accountService: AccountService
) {

    @PostMapping("signup")
    fun signIn(@RequestBody request: UserRequest): ResponseEntity<*> {
//    fun signIn(@RequestBody request: UserRequest) {
        val role = roleRepository.findByName(Roles.USER.text)
        val userRoles = mutableListOf<Role>()
        userRoles.add(role)

        val newUser = userRepository.save(
            User(
                username = request.email,
                password = passwordEncoder.encode(request.password),
                firstName = request.firstName,
                lastName = request.lastName,
                roles = userRoles
            )
        )
        if (request.password == "") return ResponseEntity.badRequest().body("Field \"password\" can't be empty!")
        try {

            /*Notify new our client with Welcome template email*/
            emailNotificationService.notify(newUser, EmailTemplate.WELCOME, null)
            return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                    "${request.email} was successfully registered!" +
                        "\n${request.email}, welcome to the Journey Car Rent"
                )
        } catch (ar: EmailWasAlreadyRegisteredException) {
            /*Client with requested email was already registered*/
            return ResponseEntity
                .status(HttpStatus.OK)
                .body(ar.message)
        }
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

    @PostMapping("/restore_password/{username}")
    fun restorePassword(@PathVariable username: String): ResponseEntity<*> {
        return try {
//            val user = userRepository.findByUsername(request.email)
//                ?: throw UsernameNotFoundException("User with username ${request.email} not found")
//            val pass = BCryptPasswordEncoder().encode(RandomStringUtils.random(12))
//            emailNotificationService.notify(user, EmailTemplate.RESTORE_PASSWORD, mapOf(Pair("pass", pass)))
            accountService.restorePassword(username)
            ResponseEntity.ok().body("Password has been restored, please, check your email")
        } catch (ue: UnknownEmailException) {
            ResponseEntity
                .ok()
                .body(ue.message)
        } catch (uu: UsernameNotFoundException) {
            ResponseEntity
                .ok()
                .body(uu.message)
        }
    }

    @GetMapping("/account")
    fun getAccountPage(): String {
        return "account"
    }
}
