package com.rksrtx76.nextbuy.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rksrtx76.nextbuy.domain.model.Review
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "products_wishlist")
data class ProductEntity(
    @PrimaryKey
    val id: Int,
    val availabilityStatus: String,
    val brand: String,
    val category: String,
    val description: String,
    val discountPercentage: Double,
    val images: List<String>,
    val minimumOrderQuantity: Int,
    val price: Double,
    val rating: Double,
    val returnPolicy: String,
    val stock: Int,
    val tags: List<String>,
    val thumbnail: String,
    val title: String,
    val warrantyInformation: String,
)