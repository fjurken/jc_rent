package com.fomichev.jc_rent.exception

abstract class BaseException(
    override var message: String,
    override var cause: Throwable?
) : Exception()
