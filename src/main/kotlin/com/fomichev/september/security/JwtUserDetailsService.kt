package com.fomichev.september.security

import com.fomichev.september.repository.UserRepository
import com.fomichev.september.security.jwt.JwtUserFactory
import com.fomichev.september.service.user.UserService
import mu.KotlinLogging
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class JwtUserDetailsService(
//    private val userService: UserService
    private val userRepository: UserRepository
) : UserDetailsService {

    val log = KotlinLogging.logger { }

    override fun loadUserByUsername(username: String?): UserDetails {
//        val user = userService.findByUsername(username!!)
        val user = userRepository.findByUsername(username!!)
            ?: throw UsernameNotFoundException("User with username: $username not found")

        val jwtUser = JwtUserFactory().create(user)
        log.info("User with name $username successfully loaded")
        return jwtUser
    }
}
