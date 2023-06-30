package com.fomichev.september.mapper

import com.fomichev.september.controller.dto.response.CarCatalog
import com.fomichev.september.model.Car
import org.springframework.stereotype.Service

@Service
class CarMapper {

    fun toAvailableListCarResponse(cars: List<Car>, price: Map<Long, Double>): List<CarCatalog> {
        val result = mutableListOf<CarCatalog>()
        cars.map {
            /*TODO fix convert Long to Int*/
            result.add(
                CarCatalog(
                    id = it.id!!.toInt(),
                    car = it.brand.displayName + " " + it.model,
                    color = it.color.name,
                    engine = "${it.engineCapacity}L, ${it.enginePower}hp",
                    transmission = it.transmission.name,
                    price = price.getValue(it.id!!)
                )
            )
        }
        return result
    }
}
