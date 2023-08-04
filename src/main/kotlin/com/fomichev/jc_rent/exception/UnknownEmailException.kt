package com.fomichev.jc_rent.exception

class UnknownEmailException(
    message: String,
    cause: Throwable?
) : BaseException(
    message, cause
)
