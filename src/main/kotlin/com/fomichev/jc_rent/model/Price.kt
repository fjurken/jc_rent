package com.fomichev.jc_rent.model

import jakarta.persistence.*

@Entity
@Table(schema = "main", name = "price")
class Price(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null,

    @Column(name = "car_id")
    var carId: Long,

    @Column(name = "price")
    var price: Double

) : BaseEntity()
