package com.jay.orderserver.domain.product

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.io.Serializable

@Entity
@Table(name = "category")
class Category (
    @EmbeddedId
    private var id : CategoryId,

    @Column(name = "name")
    private var name : String
) {
}

@Embeddable
class CategoryId internal constructor(
    @Column(name = "category_id")
    private val value : Long
) : Serializable {
    fun getValue() : Long {
        return value
    }
}

public fun categoryIdOf(value : Long) : CategoryId {
    return CategoryId(value)
}