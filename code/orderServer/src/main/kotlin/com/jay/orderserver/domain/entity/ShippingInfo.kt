package com.jay.orderserver.domain.entity

import jakarta.persistence.*

@Embeddable
class ShippingInfo (
    @Embedded
    val receiver: Receiver? = null,

    @Embedded
    @AttributeOverrides(
        AttributeOverride(name="zipCode", column=Column(name = "shipping_zipcode")),
        AttributeOverride(name="address1", column=Column(name = "shipping_addr1")),
        AttributeOverride(name="address2", column=Column(name = "shipping_addr2"))
    )
    val address: Address? = null,

    val message: String? = null
) {
}