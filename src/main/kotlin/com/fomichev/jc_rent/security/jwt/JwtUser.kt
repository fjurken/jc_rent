package com.fomichev.jc_rent.security.jwt

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.Instant

class JwtUser(
    private val id: Long,
    private val username: String,
    private val firstName: String,
    private val lastName: String,
    private val password: String,
    private val authorities: Collection<GrantedAuthority>,
    private val enabled: Boolean,
    private val lastPasswordResetDate: Instant
) : UserDetails {

    @JsonIgnore
    fun getId(): Long = id

    fun getFirstName(): String = firstName

    fun getLastName(): String = lastName

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return authorities as MutableCollection<out GrantedAuthority>
    }

    @JsonIgnore
    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return username
    }

    @JsonIgnore
    override fun isAccountNonExpired(): Boolean {
        return true
    }

    @JsonIgnore
    override fun isAccountNonLocked(): Boolean {
        return true
    }

    @JsonIgnore
    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return enabled
    }

    @JsonIgnore
    fun getLastPasswordResetDate(): Instant = lastPasswordResetDate
}
