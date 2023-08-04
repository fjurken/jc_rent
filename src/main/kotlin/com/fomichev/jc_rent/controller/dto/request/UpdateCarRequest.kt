package com.fomichev.jc_rent.controller.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import com.fomichev.jc_rent.enum.CarColor
import com.fomichev.jc_rent.enum.EntityStatus

/**
 * Update some car specs
 */
data class UpdateCarRequest(
    @JsonProperty("id")
    val id: Long,

    @JsonProperty("color")
    val color: CarColor?,

    @JsonProperty("licencePlate")
    val licencePlate: String?,

    @JsonProperty("status")
    val status: EntityStatus?
)
