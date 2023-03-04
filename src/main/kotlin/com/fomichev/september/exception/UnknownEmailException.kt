package com.fomichev.september.exception

class UnknownEmailException(
    message: String,
    cause: Throwable?
) : BaseException(
    message, cause
)