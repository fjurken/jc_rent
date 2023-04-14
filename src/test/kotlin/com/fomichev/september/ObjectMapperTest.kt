package com.fomichev.september

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.junit.jupiter.api.Test
import java.time.Instant

class ObjectMapperTest {

    @Test
    fun objectMapperTest() {
        val time = Instant.now()
        val mapper = ObjectMapper().registerModule(JavaTimeModule())
        mapper.writeValueAsBytes(time)
    }
}
