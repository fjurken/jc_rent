package com.fomichev.jc_rent.mapper

import com.fomichev.jc_rent.controller.dto.response.CarCatalog
import com.fomichev.jc_rent.enum.EngineType
import com.fomichev.jc_rent.model.Car
import org.springframework.stereotype.Service

@Service
class CarMapper {

    fun toAvailableListCarResponse(cars: List<Car>, price: Map<Long, Double>): List<CarCatalog> {
        val result = mutableListOf<CarCatalog>()
        cars
            .filter { price.containsKey(it.id) } // only cars with price
            .map {
                /*TODO fix convert Long to Int*/
                result.add(
                    CarCatalog(
                        id = it.id!!.toInt(),
                        car = it.brand.displayName + " " + it.model,
                        color = it.color.name,
                        engine = getEngineDescription(it),
                        transmission = it.transmission.name,
                        price = price.getValue(it.id!!)
                    )
                )
            }
        return result
    }

    private fun getEngineDescription(car: Car): String {
        return if (car.engineType != EngineType.ELECTRIC) {
            "${car.engineCapacity}L, ${car.enginePower}hp"
        } else "${car.enginePower}hp"
    }
}
