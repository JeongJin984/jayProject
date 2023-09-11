package com.jay.orderserver.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
abstract class Receiver (
    @Column(name =  "name")
    var name: String,

    @Column(name = "phone")
    var phone: String
) {}