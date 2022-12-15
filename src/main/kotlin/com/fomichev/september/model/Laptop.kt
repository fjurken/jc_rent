package com.fomichev.september.model

import javax.persistence.*

@Entity
@Table(name = "laptop")
class Laptop(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    var id: Long? = null,

    @Column(name = "type")
    var type: String? = null

)
