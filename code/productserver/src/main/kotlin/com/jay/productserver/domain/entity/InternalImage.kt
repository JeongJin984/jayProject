package com.jay.productserver.domain.entity

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue("II")
class InternalImage (
    path : String
) : ProductImage(path = path) {
    override fun url(): String {
        return "/images/original/" + getPath()
    }

    override fun hasThumbnail(): Boolean {
        return true
    }

    override fun thumbnailURL(): String {
        return "images/thumbnail/" + getPath()
    }
}