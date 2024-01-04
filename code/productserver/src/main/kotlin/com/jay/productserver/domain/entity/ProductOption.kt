package com.jay.productserver.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
class ProductOption(
    @Column(name="option_value")
    private var value: String,
    @Column(name = "option_title")
    private var titile: String
) {

}