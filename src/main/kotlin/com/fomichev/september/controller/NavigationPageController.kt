package com.fomichev.september.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/")
class NavigationPageController {

    @RequestMapping("laptops")
    fun getLaptopsPage(): String {
        return "laptops"
    }

    @RequestMapping("phones")
    fun getPhonesPage(): String {
        return "phones"
    }

    @RequestMapping("accessorize")
    fun getAccessorizePage(): String {
        return "accessorize"
    }
}
