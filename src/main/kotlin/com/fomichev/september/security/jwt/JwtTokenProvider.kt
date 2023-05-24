package com.fomichev.september.security.jwt

import com.fomichev.september.model.Role
import com.fomichev.september.security.JwtUserDetailsService
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import java.io.Serializable
import java.time.Instant
import java.util.Base64
import java.util.Date
import javax.annotation.PostConstruct
import javax.servlet.http.HttpServletRequest

@Component
class JwtTokenProvider(
    @Value("\${jwt.token.secret}")
    private var secret: String,

    @Value("\${jwt.token.expired}")
    private var validityInMilliseconds: Long,

    private val jwtUserDetailsService: JwtUserDetailsService

) : Serializable {

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @PostConstruct
    fun init() {
        secret = Base64.getEncoder().encodeToString(secret.toByteArray())
    }

    fun createToken(username: String, roles: List<Role>): String {
        val claims = Jwts.claims()
        claims.subject = username
        claims["roles"] = roles.map { it.name }

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(Date.from(Instant.now()))
            .setExpiration(Date.from(Instant.now().plusMillis(validityInMilliseconds)))
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact()
    }

    fun getAuthentication(token: String): Authentication {
        val userDetails = jwtUserDetailsService.loadUserByUsername(getUsername(token))
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    fun getUsername(token: String): String {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).body.subject
    }

    fun resolveToken(req: HttpServletRequest): String? {
        val bearerToken = req.getHeader(HttpHeaders.AUTHORIZATION)
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length)
        }
        return null
    }

    fun validateToken(token: String): Boolean {
        try {
            val claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token)
            if (claims.body.expiration.before(Date())) {
                return false
            }
            return true
        } catch (j: JwtException) {
            throw JwtAuthenticationException("JWT token is expired or invalid", null)
        } catch (e: IllegalArgumentException) {
            throw JwtAuthenticationException("JWT token is expired or invalid", null)
        }
    }

    private fun getRoleNames(userRoles: List<Role>): List<String> {
        val result = mutableListOf<String>()
        userRoles.map { role ->
            result.add(role.name)
        }
        return result
    }
}
