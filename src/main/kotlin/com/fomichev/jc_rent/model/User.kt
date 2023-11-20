package com.fomichev.jc_rent.model

import jakarta.persistence.*

@Entity
@Table(schema = "main", name = "users")
class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null,

    @Column(name = "username")
    var username: String,

    @Column(name = "first_name")
    var firstName: String,

    @Column(name = "last_name")
    var lastName: String,

    @Column(name = "password")
    var password: String,

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        schema = "main",
        name = "user_roles",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")]
    )
    var roles: List<Role> = mutableListOf()

) : BaseEntity()
