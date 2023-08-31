package com.fomichev.jc_rent.service.utils

import java.time.Instant
import java.time.ZoneOffset

fun Instant.prettyDateTime(): String {
    val atZone = this.atZone(ZoneOffset.UTC)
    return "${if (atZone.dayOfMonth.toString().length == 1) "0${atZone.dayOfMonth}" else atZone.dayOfMonth}-" +
        "${if (atZone.monthValue.toString().length == 1) "0${atZone.monthValue}" else atZone.monthValue}-" +
        "${atZone.year} " +
        "${atZone.toLocalTime()}"
}

fun Instant.prettyDate(): String {
    val atZone = this.atZone(ZoneOffset.UTC)
    return "${if (atZone.dayOfMonth.toString().length == 1) "0${atZone.dayOfMonth}" else atZone.dayOfMonth}-" +
        "${if (atZone.monthValue.toString().length == 1) "0${atZone.monthValue}" else atZone.monthValue}-" +
        "${atZone.year}"
}
