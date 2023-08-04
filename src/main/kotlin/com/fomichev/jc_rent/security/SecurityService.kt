package com.fomichev.jc_rent.security

interface SecurityService {

    fun encryptPassword(data: String): String
}
