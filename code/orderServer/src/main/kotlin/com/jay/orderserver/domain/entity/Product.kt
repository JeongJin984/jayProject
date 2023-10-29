package com.jay.orderserver.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
@Table(name = "product")
class Product (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val productId: Long? = null,

    @Column(name = "name")
    var name: String,

    @Column(name = "price")
    var price: BigDecimal,

    @Column(name = "description")
    var description: String
) {
}