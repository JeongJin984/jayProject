package com.jay.orderserver.domain.jayMarket.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
abstract class Receiver (
    @Column(name =  "name")
    var name: String,

    @Column(name = "phone")
    var phone: String
) {
}