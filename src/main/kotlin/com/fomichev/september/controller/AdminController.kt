package com.fomichev.september.controller

import com.fomichev.september.controller.dto.request.UserRequest
import com.fomichev.september.exception.UnknownEmailException
import com.fomichev.september.service.AbstractService
import com.fomichev.september.service.account.AccountService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

/**
 * Administrator
 */
@Controller
@RequestMapping("/admin")
class AdminController(
    private val accountService: AccountService
) : AbstractService() {

    /**
     *
     */
    @PostMapping("/log_in")
    fun logIn(@RequestBody request: UserRequest): ResponseEntity<*> {
        return try {
            if (accountService.logIn(request)) {
                log.info("Accessed as Admin")
                ResponseEntity.ok().body("Accessed as Admin")
            } else {
                log.info("Admin access denied")
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Admin access denied")
            }
        } catch (ue: UnknownEmailException) {
            ResponseEntity.ok().body(ue.message)
        }
    }
}
