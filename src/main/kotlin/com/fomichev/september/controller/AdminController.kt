package com.fomichev.september.controller

import com.fomichev.september.controller.dto.request.UserRequest
import com.fomichev.september.service.account.AccountService
import mu.KotlinLogging
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

/**
 * Administrator
 */
@Controller
@RequestMapping("/admin")
class AdminController(
    private val accountService: AccountService
) {

    val log = KotlinLogging.logger {}

    @GetMapping
    fun getAdminPage(@RequestBody request: UserRequest): String? {
        return if (accountService.logIn(request)) {
            log.info("Accessed as Admin")
            return "admin"
        } else {
            log.info("Admin access denied")
            null
        }
    }
}