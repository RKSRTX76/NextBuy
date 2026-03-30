package com.rksrtx76.nextbuy.domain.model

data class UserProfile(
    val userId : String,
    val email : String,
    val password : String,
    val firstName : String,
    val lastName : String,
    val profilePhotoUrl : String,
    val phoneNumber : String,
    val address : String,
    val city : String,
    val state : String,
    val pinCode : String,
    val country : String,
)