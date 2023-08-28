package com.fomichev.jc_rent

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.servlet.ModelAndView

@RestControllerAdvice
class ExcptHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun doResolveException(
        e: MethodArgumentNotValidException,
        request: HttpServletRequest,
        response: HttpServletResponse
    ): ModelAndView? {

        throw HttpClientErrorException(
            HttpStatus.BAD_REQUEST,
            e.bindingResult.allErrors[0].defaultMessage!!
        )
    }
}
