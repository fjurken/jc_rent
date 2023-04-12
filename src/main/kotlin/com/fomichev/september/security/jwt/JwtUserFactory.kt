package com.fomichev.september.security.jwt

import com.fomichev.september.enum.EntityStatus
import com.fomichev.september.model.Role
import com.fomichev.september.model.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

final class JwtUserFactory {

    fun create(user: User): JwtUser {
        return JwtUser(
            user.id!!,
            user.username,
            user.firstName,
            user.lastName,
            user.password,
            user.email,
            mapToGrantedAuthorities(user.roles),
            user.status == EntityStatus.ACTIVE,
            user.updatedDate!!
        )
    }

    private fun mapToGrantedAuthorities(userRoles: List<Role>): List<GrantedAuthority> {
        return userRoles.map { role ->
            SimpleGrantedAuthority(role.name)
        }
    }
}
