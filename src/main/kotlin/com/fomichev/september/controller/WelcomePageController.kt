package com.fomichev.september.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class WelcomePageController {

    @RequestMapping("/start")
    fun start(): String {
        return "Welcome to the future!"
    }
}
