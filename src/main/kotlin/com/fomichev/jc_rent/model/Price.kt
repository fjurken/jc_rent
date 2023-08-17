package com.fomichev.jc_rent.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "price")
class Price(

    @Column(name = "car_id")
    var carId: Long,

    @Column(name = "price")
    var price: Double

) : BaseEntity()
