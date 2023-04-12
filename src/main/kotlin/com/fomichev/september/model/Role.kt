package com.fomichev.september.model

import lombok.Data
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToMany
import javax.persistence.Table

@Entity
@Table(name = "role")
@Data
class Role(

    @Column(name = "name")
    var name: String,

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    var users: List<User>

) : BaseEntity() {

    override fun toString(): String {
        return "Role{id: ${super.id}, name: $name}"
    }
}
