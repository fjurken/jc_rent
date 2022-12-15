package com.fomichev.september.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class WelcomePageController {

    @RequestMapping("/welcome")
    fun welcome(): String {
        return "welcome"
    }
}
