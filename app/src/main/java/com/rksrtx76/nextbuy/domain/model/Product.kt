package com.rksrtx76.nextbuy.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: Int,
    val availabilityStatus: String,
    val brand: String,
    val category: String,
    val description: String,
    val price: Double,
    val discountPercentage: Double,
    val discountedPrice: Double,
    val images: List<String>,
    val minimumOrderQuantity: Int,
    val rating: Double,
    val returnPolicy: String,
    val stock: Int,
    val tags: List<String>,
    val thumbnail: String,
    val title: String,
    val warrantyInformation: String,
    val reviews: List<Review>,
)