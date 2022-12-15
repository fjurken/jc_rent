package com.fomichev.september.security

interface SecurityService {

    fun encryptPassword(data: String): String
}