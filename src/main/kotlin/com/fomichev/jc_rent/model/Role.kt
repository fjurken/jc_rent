package com.fomichev.jc_rent.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table
import org.hibernate.annotations.DynamicUpdate
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@Entity
@DynamicUpdate
@EntityListeners(AuditingEntityListener::class)
@Table(name = "roles")
class Role(

    @Column(name = "name")
    var name: String,

    @JsonIgnore
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    var users: List<User>

) : BaseEntity() {

    override fun toString(): String {
        return "Role{id: $id, name: $name}"
    }
}
