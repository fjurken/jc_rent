package com.fomichev.jc_rent.security

import com.fomichev.jc_rent.exception.EncryptPassException
import org.springframework.stereotype.Service
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import kotlin.experimental.and

@Service
class SecurityServiceImpl : SecurityService {
    override fun encryptPassword(data: String): String {

        try {
            /* MessageDigest instance for MD5. */
            val m: MessageDigest = MessageDigest.getInstance("MD5")
            /* Add plain-text password bytes to digest using MD5 update() method. */
            m.update(data.toByteArray())
            /* Convert the hash value into bytes */
            val bytes = m.digest()
            /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */
            val s = StringBuilder()
            for (element in bytes) {
                s.append(((element and 0xff.toByte()) + 0x100).toString(16).substring(1))
            }
            return s.toString()
        } catch (ex: NoSuchAlgorithmException) {
            throw EncryptPassException(message = "Encrypt pass error", cause = ex.cause)
        }
    }
}
