package com.fomichev.september.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

// private const val HOST = "http://192.168.3.10:8080"

@RestController
@RequestMapping("/api")
// @CrossOrigin(HOST)
class WelcomePageController {

    @GetMapping("/welcome")
    fun welcome(): List<GoodsType> {
        return listOf()
    }

    @GetMapping("/goods")
    fun laptop(): List<GoodsType> {
        return listOf(
            GoodsType(
                id = 1,
                name = "Desktop",
                link = "/desktop"
            ),
            GoodsType(
                id = 2,
                name = "Laptop",
                link = "/api/laptop"
            ),
            GoodsType(
                id = 3,
                name = "Phone",
                link = "/api/phone"
            )
        )
    }

    @GetMapping("/goods/laptop")
    fun getLaptop(): List<Goods> {
        return listOf(
            Goods(
                id = 1,
                type = GoodsType(
                    id = 1,
                    name = "Laptop",
                    link = "/goods/laptop"
                ),
                brand = "Apple"
            )
        )
    }
}

class GoodsType(
    var id: Long,
    var name: String,
    var link: String,
)

class Goods(
    var id: Long,
    var type: GoodsType,
    var brand: String
)
