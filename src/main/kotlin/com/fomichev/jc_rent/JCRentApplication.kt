package com.fomichev.jc_rent

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class JCRentApplication

fun main(args: Array<String>) {
    runApplication<JCRentApplication>(*args)
}
