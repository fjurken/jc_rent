package com.fomichev.september.model

import org.hibernate.annotations.DynamicUpdate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EntityListeners
import javax.persistence.Table

@Entity
@EntityListeners(AuditingEntityListener::class)
@DynamicUpdate
@Table(name = "rent")
class Rent(

    @Column(name = "car_id")
    var carId: Long,

    @Column(name = "start_date")
    var startDate: Instant,

    @Column(name = "end_date")
    var endDate: Instant,

    @Column(name = "finished_date")
    var finishedDate: Instant? = null,

) : BaseEntity()
