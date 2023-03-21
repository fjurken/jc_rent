package com.fomichev.september.controller.dto.response

data class CarCatalogResponse(
    val data: List<CarCatalog>
)

data class CarCatalog(
    val id: Int,
    val car: String,
    val color: String,
    val price: Double
)
