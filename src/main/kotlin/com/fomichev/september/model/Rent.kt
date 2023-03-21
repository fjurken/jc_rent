package com.fomichev.september.model

import java.time.Instant
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "rent")
class Rent(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    var id: Long? = null,

    @Column(name = "car_id")
    var carId: Long,

    @Column(name = "start_date")
    var startDate: Instant,

    @Column(name = "end")
    var endDate: Instant,

    @Column(name = "finished")
    var finished: Boolean,

)
