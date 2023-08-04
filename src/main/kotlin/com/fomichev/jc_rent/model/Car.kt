package com.fomichev.jc_rent.model

import com.fomichev.jc_rent.enum.CarBrand
import com.fomichev.jc_rent.enum.CarColor
import com.fomichev.jc_rent.enum.CarType
import com.fomichev.jc_rent.enum.EngineType
import com.fomichev.jc_rent.enum.Transmission
import org.hibernate.annotations.DynamicUpdate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EntityListeners
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Table

@Entity
@DynamicUpdate
@EntityListeners(AuditingEntityListener::class)
@Table(name = "cars")
class Car(

    @Enumerated(value = EnumType.STRING)
    @Column(name = "brand", length = 30)
    var brand: CarBrand,

    @Column(name = "model", length = 30)
    var model: String,

    @Enumerated(value = EnumType.STRING)
    @Column(name = "car_type", length = 10)
    var carType: CarType,

    @Enumerated(value = EnumType.STRING)
    @Column(name = "color", length = 10)
    var color: CarColor,

    @Enumerated(value = EnumType.STRING)
    @Column(name = "engine_type", length = 10)
    var engineType: EngineType,

    @Column(name = "engine_capacity", length = 10)
    var engineCapacity: String,

    @Column(name = "engine_power")
    var enginePower: Int,

    @Enumerated(value = EnumType.STRING)
    @Column(name = "transmission")
    var transmission: Transmission,

    @Column(name = "licence_plate")
    var licencePlate: String,
) : BaseEntity() {

    override fun toString(): String {
        return "" +
            "Car Id=$id, " +
            "brand=$brand, " +
            "model=$model, " +
            "car type=$carType, " +
            "color=$color, " +
            "engine type=$engineType, " +
            "engine capacity=$engineCapacity, " +
            "engine power=$enginePower, " +
            "transmission=$transmission, " +
            "licence plate=$licencePlate, "
    }
}