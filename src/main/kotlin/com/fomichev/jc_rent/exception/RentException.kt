package com.fomichev.jc_rent.exception

class RentException(
    message: String,
    cause: Throwable?
) : BaseException(
    message,
    cause
)
