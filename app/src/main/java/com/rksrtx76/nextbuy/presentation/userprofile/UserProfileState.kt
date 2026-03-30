package com.rksrtx76.nextbuy.presentation.userprofile

import com.rksrtx76.nextbuy.domain.model.UserProfile

data class UserProfileState(
    val userProfile: UserProfile = UserProfile(
        userId = "",
        email = "",
        password = "",
        firstName = "",
        lastName = "",
        profilePhotoUrl = "",
        phoneNumber = "",
        address = "",
        city = "",
        state = "",
        pinCode = "",
        country = ""
    ),
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSaving: Boolean = false,
    val saveSuccess: Boolean = false
)