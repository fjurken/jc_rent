package com.fomichev.jc_rent.configuration

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class JwtUtils {
    val username: String
        get() {
            return SecurityContextHolder.getContext().authentication.name
        }
}
