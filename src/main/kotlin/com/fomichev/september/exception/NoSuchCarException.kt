package com.fomichev.september.exception

class NoSuchCarException(
    message: String,
    cause: Throwable?
) : BaseException(
    message,
    cause
)