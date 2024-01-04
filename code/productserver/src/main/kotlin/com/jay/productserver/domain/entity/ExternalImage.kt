package com.jay.productserver.domain.entity

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue("EI")
class ExternalImage(
    path : String
) : ProductImage(path = path) {
    override fun url(): String {
        return getPath()
    }

    override fun hasThumbnail(): Boolean {
        return false
    }

    override fun thumbnailURL(): String {
        return ""
    }
}