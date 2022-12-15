package com.fomichev.september.model

import javax.persistence.*

@Entity
@Table(name = "client")
class Client(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    var id: Long? = null,

    @Column(name = "email")
    var email: String,

    @Column(name = "name")
    var name: String,
)
