package com.rksrtx76.nextbuy.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductResponse(
    @SerialName("availabilityStatus")
    val availabilityStatus: String,
    @SerialName("brand")
    val brand: String? = null,
    @SerialName("category")
    val category: String,
    @SerialName("description")
    val description: String,
    @SerialName("discountPercentage")
    val discountPercentage: Double,
    @SerialName("id")
    val id: Int,
    @SerialName("images")
    val images: List<String>,
    @SerialName("minimumOrderQuantity")
    val minimumOrderQuantity: Int,
    @SerialName("price")
    val price: Double,
    @SerialName("rating")
    val rating: Double,
    @SerialName("returnPolicy")
    val returnPolicy: String,
    @SerialName("reviews")
    val reviewResponses: List<ReviewResponse>,
    @SerialName("shippingInformation")
    val shippingInformation: String,
    @SerialName("sku")
    val sku: String,
    @SerialName("stock")
    val stock: Int,
    @SerialName("tags")
    val tags: List<String>,
    @SerialName("thumbnail")
    val thumbnail: String,
    @SerialName("title")
    val title: String,
    @SerialName("warrantyInformation")
    val warrantyInformation: String,
    @SerialName("weight")
    val weight: Int
)