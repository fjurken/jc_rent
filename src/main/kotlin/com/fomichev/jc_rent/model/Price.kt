package com.fomichev.jc_rent.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "price")
class Price(

    @Column(name = "car_id")
    var carId: Long,

    @Column(name = "price")
    var price: Double

) : BaseEntity()
