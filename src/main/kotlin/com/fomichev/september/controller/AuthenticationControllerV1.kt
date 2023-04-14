package com.fomichev.september.controller

import com.fomichev.september.controller.dto.request.AuthenticationRequestDto
import com.fomichev.september.security.jwt.JwtTokenProvider
import com.fomichev.september.service.user.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth/")
class AuthenticationControllerV1(
    private val authenticationManager: AuthenticationManager,
    private val jwtTokenProvider: JwtTokenProvider,
    private val userService: UserService
) {

    @PostMapping("login")
    fun login(@RequestBody request: AuthenticationRequestDto): ResponseEntity<*> {
        try {
            val username = request.username
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(username, request.password))
            val user = userService.findByUsername(username)
                ?: throw UsernameNotFoundException("User with username: $username not found")
            val token = jwtTokenProvider.createToken(username, user.roles)
            val response = mutableMapOf<Any, Any>()
            response["username"] = username
            response["token"] = token
            return ResponseEntity.ok(response)
        } catch (e: AuthenticationException) {
            throw BadCredentialsException("Invalid username or password")
        }
    }
}
