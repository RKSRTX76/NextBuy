package com.rksrtx76.nextbuy.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserProfileDto(
    val userId : String = "",
    val email : String = "",
    val password : String = "",
    val firstName : String = "",
    val lastName : String? = "",
    val profilePhotoUrl : String? = "",
    val phoneNumber : String? = "",
    val address : String? = "",
    val city : String? = "",
    val state : String? = "",
    val pinCode : String? = "",
    val country : String? = "",
)
