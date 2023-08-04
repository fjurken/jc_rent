package com.fomichev.jc_rent.exception

class ClientBackDataIsEmptyException(
    message: String,
    cause: Throwable?
) : BaseException(
    message, cause
)
