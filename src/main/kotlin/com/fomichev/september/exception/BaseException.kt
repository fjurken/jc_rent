package com.fomichev.september.exception

abstract class BaseException(
    override var message: String,
    override var cause: Throwable?
): Exception()
