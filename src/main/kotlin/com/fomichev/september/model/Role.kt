package com.fomichev.september.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
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
