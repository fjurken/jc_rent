package com.fomichev.september

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class])
@EnableJpaRepositories
class SeptemberApplication

fun main(args: Array<String>) {
    runApplication<SeptemberApplication>(*args)
}
