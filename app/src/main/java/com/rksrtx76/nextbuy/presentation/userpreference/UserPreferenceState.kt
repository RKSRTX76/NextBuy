package com.rksrtx76.nextbuy.presentation.userpreference

data class UserPreferenceState(
    val firstTimeLogin : Boolean = true,
    val isLoggedIn : Boolean = false,
    val isLoading : Boolean = true
)