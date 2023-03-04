package com.fomichev.september.exception

class EmailWasAlreadyRegisteredException(
    message: String,
    cause: Throwable?
): BaseException(
    message,
    cause
)