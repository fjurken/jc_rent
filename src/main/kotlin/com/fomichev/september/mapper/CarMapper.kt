package com.fomichev.september.mapper

import com.fomichev.september.controller.dto.response.CarCatalog
import com.fomichev.september.model.Car
import org.springframework.stereotype.Service

@Service
class CarMapper {

    fun toAvailableListCarResponse(cars: List<Car>): List<CarCatalog> {
        val result = mutableListOf<CarCatalog>()
        cars.map {
            /*TODO fix convert Long to Int*/
            result.add(
                CarCatalog(
                    id = it.id!!.toInt(),
                    car = it.brand.displayName + " " + it.model,
                    color = it.color.name,
                    price = 10.0
                )
            )
        }
        return result
    }
}
