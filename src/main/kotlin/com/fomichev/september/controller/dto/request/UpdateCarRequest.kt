package com.fomichev.september.controller.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import com.fomichev.september.enum.CarColor
import com.fomichev.september.enum.EntityStatus

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