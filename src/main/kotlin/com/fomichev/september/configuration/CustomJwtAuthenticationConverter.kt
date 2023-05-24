package com.fomichev.september.configuration

import io.jsonwebtoken.Jwts
import org.springframework.core.convert.converter.Converter
import org.springframework.security.authentication.AbstractAuthenticationToken

class CustomJwtAuthenticationConverter : Converter<Jwts, AbstractAuthenticationToken> {

    override fun convert(source: Jwts): AbstractAuthenticationToken? {
        TODO("Not yet implemented")
    }
}
