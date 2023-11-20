package com.fomichev.jc_rent.model

import com.fomichev.jc_rent.enum.EntityStatus
import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant

@Entity
@EntityListeners(AuditingEntityListener::class)
@DynamicUpdate
@Table(schema = "main", name = "rent")
class Rent(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null,

    @Column(name = "car_id")
    var carId: Long,

    @Column(name = "user_id")
    var userId: String,

    @Column(name = "start_date")
    var startDate: Instant,

    @Column(name = "end_date")
    var endDate: Instant,

    @Column(name = "finished_date")
    var finishedDate: Instant? = null,

    override var status: EntityStatus = EntityStatus.NOT_ACTIVE

) : BaseEntity()
