package com.jay.productserver.domain.entity

import jakarta.persistence.Access
import jakarta.persistence.AccessType
import jakarta.persistence.Column
import jakarta.persistence.DiscriminatorColumn
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Inheritance
import jakarta.persistence.InheritanceType
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "image_type")
@Table(name = "product_image")
abstract class ProductImage internal constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private var id : Long? = null,

    @Column(name = "image_path")
    private var path : String,

    @Column(name = "upload_time")
    private var uploadTime: LocalDateTime = LocalDateTime.now()
) {
    internal fun getPath(): String {
        return path
    }

    internal fun getUploadTime() :LocalDateTime {
        return uploadTime
    }

    abstract fun url() : String
    abstract fun hasThumbnail() : Boolean
    abstract fun thumbnailURL(): String

}