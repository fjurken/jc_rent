package com.fomichev.jc_rent.model

import com.fomichev.jc_rent.enum.EntityStatus
import org.hibernate.annotations.DynamicUpdate
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
@DynamicUpdate
@EntityListeners(AuditingEntityListener::class)
open class BaseEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null,

    @CreatedDate
    @Column(name = "created")
    var createdDate: Instant? = Instant.now(),

    @LastModifiedDate
    @Column(name = "updated")
    var updatedDate: Instant? = Instant.now(),

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    open var status: EntityStatus = EntityStatus.ACTIVE
)
