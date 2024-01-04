package com.jay.productserver.domain.entity

import com.jay.productserver.common.dto.ProductOrderInfo
import jakarta.persistence.*
import java.io.Serializable
import java.math.BigDecimal

@Entity(name = "ProductEntity")
@Table(name = "product")
@Access(AccessType.FIELD)
class Product(
    @EmbeddedId
    val productId: ProductId,

    @Column(name = "name")
    var name: String,

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "product_category",
        joinColumns = [JoinColumn(name = "product_id")])
    private var categories: List<CategoryId> = listOf(),

    @OneToMany(
        cascade = [CascadeType.PERSIST, CascadeType.REMOVE],
        orphanRemoval = true,
        fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    @OrderColumn(name = "list_idx")
    private var images: MutableList<ProductImage> = mutableListOf(),

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "product_option",
        joinColumns = [JoinColumn(name = "product_id")])
    @OrderColumn(name = "list_idx")
    private var options: List<ProductOption> = listOf(),

    @Column(name = "price")
    var price: BigDecimal,

    @Column(name = "description")
    var description: String
) {
    public fun firstThumbnailPath(): String? {
        if(images.isEmpty()) return null
        return images[0].getPath()
    }

    fun createProductOrderInfo() : ProductOrderInfo {
        return ProductOrderInfo(
            productId = productId.getProductId(),
            name = name,
            category = categories.map { it.getValue() },
            productImage = images.map { it.getPath() },
            price = price.toInt(),
            description = description
        )
    }
}

@Embeddable
class ProductId internal constructor (
    @Column(name = "product_id")
    private val productId: String
) : Serializable {
    fun getProductId(): String {
        return this.productId
    }
}

fun productIdOf(id: String) : ProductId {
    return ProductId(id)
}

