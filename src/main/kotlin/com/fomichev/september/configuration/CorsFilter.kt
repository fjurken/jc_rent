package com.fomichev.september.configuration

import org.springframework.stereotype.Component
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletResponse

@Component
class CorsFilter : Filter {
    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val httpResponse = response as HttpServletResponse
        httpResponse.setHeader("Access-Control-Allow-Origin", "*")
        httpResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE")
        httpResponse.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization")
        httpResponse.setHeader("Access-Control-Max-Age", "3600")
        chain?.doFilter(request, httpResponse)
    }
}
