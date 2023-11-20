package com.fomichev.jc_rent.model

import com.fomichev.jc_rent.enum.EntityStatus
import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.DynamicUpdate
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant

@MappedSuperclass
@DynamicUpdate
@EntityListeners(AuditingEntityListener::class)
open class BaseEntity(

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
