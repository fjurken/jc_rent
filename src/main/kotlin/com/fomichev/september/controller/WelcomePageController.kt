package com.fomichev.september.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/start")
class WelcomePageController {

    fun start(): String{
        return "Welcome!"
    }
}