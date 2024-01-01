package com.jay.orderserver.domain.order

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
class Receiver (
    @Column(name =  "name")
    var name: String,

    @Column(name = "phone")
    var phone: String
) {}