package com.fomichev.september.security.jwt

import org.springframework.security.core.AuthenticationException

class JwtAuthenticationException(
    override val message: String?,
    override val cause: Throwable?
) : AuthenticationException(message, cause)
