package com.fomichev.jc_rent.exception

class EmailWasAlreadyRegisteredException(
    message: String,
    cause: Throwable?
) : BaseException(
    message,
    cause
)
