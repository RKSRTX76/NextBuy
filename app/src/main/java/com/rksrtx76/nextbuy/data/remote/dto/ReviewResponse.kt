package com.rksrtx76.nextbuy.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReviewResponse(
    @SerialName("rating")
    val rating: Int,
    @SerialName("comment")
    val comment: String,
    @SerialName("reviewerName")
    val reviewerName: String,
    @SerialName("reviewerEmail")
    val reviewerEmail: String,
    @SerialName("date")
    val date: String
)