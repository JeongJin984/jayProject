package com.jay.orderserver.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
abstract class Address(
    @Column(name = "zip_code")
    var zipCode: String,

    @Column(name = "address1")
    var  address1: String,

    @Column(name = "address2")
    var  address2: String
) {
    fun changeAddress1(address1: String) {
        this.address1 = address1
    }

    fun changeAddress2(address2: String) {
        this.address2 = address2
    }
}