package com.jay.orderserver.domain.jayMarket.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

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
    var price: Int,

    @Column(name = "detail")
    var detail: String
) {
}