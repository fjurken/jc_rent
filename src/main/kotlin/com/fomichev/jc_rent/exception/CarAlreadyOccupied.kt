package com.fomichev.jc_rent.exception

class CarAlreadyOccupied(
    message: String,
    cause: Throwable?
) : BaseException(
    message,
    cause
)
