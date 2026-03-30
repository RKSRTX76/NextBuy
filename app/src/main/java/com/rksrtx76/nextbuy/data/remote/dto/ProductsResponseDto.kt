package com.rksrtx76.nextbuy.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductsResponseDto(
    @SerialName("limit")
    val limit: Int,
    @SerialName("products")
    val productResponses: List<ProductResponse>,
    @SerialName("skip")
    val skip: Int,
    @SerialName("total")
    val total: Int
)