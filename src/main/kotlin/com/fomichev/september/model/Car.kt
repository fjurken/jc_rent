package com.fomichev.september.model

import com.fomichev.september.enum.CarBrand
import com.fomichev.september.enum.CarColor
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Table

@Entity
@Table(name = "car")
class Car(

    @Enumerated(value = EnumType.STRING)
    @Column(name = "brand", length = 20)
    var brand: CarBrand,

    @Column(name = "model", length = 20)
    var model: String,

    @Enumerated(value = EnumType.STRING)
    @Column(name = "color", length = 20)
    var color: CarColor,

    @Column(name = "horse_power")
    var horsePower: Int,

    @Column(name = "licence_plate")
    var licencePlate: String,
) : BaseEntity() {

    override fun toString(): String {
        return "Car Id=$id, brand=$brand, model=$model, color=$color, horsePower=$horsePower"
    }
}
