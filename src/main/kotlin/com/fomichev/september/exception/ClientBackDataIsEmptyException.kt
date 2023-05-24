package com.fomichev.september.exception

class ClientBackDataIsEmptyException(
    message: String,
    cause: Throwable?
) : BaseException(
    message, cause
)
