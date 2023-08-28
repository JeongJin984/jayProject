package com.jay.orderserver.domain.entity

import com.jay.orderserver.domain.entity.Address
import com.jay.orderserver.domain.entity.Receiver
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "shipping_info")
class ShippingInfo (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var shippingInfoId: Long? = null,
    var receiver: Receiver? = null,
    var address: Address? = null
) {
}