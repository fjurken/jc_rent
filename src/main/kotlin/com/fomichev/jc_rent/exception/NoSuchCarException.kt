package com.fomichev.jc_rent.exception

class NoSuchCarException(
    message: String,
    cause: Throwable?
) : BaseException(
    message,
    cause
)
