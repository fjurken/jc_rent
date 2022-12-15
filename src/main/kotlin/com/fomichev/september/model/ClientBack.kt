package com.fomichev.september.model

import javax.persistence.*

@Entity
@Table(name = "client_back")
class ClientBack(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    var id: Long? = null,

    @Column(name = "client_id")
    var client_id: Long,

    @Column(name = "data")
    var data: String,

)
