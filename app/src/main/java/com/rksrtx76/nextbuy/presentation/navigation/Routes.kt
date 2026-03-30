package com.rksrtx76.nextbuy.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes{
    @Serializable
    data object SplashScreen : Routes()

    @Serializable
    data object WelcomeScreen1 : Routes()

    @Serializable
    data object WelcomeScreen2 : Routes()

    @Serializable
    data object WelcomeScreen3 : Routes()

    @Serializable
    data object SignInScreen : Routes()

    @Serializable
    data object SignUpScreen : Routes()

    @Serializable
    data object ForgotPasswordScreen : Routes()

    @Serializable
    data object HomeScreen : Routes()

    @Serializable
    data class ProductDetailScreen(val productId : Int) : Routes()

    @Serializable
    data object SearchScreen : Routes()

    @Serializable
    data object CartScreen : Routes()

    @Serializable
    data object ProfileScreen : Routes()

    @Serializable
    data object OrderScreen : Routes()

    @Serializable
    data object SettingsScreen : Routes()

    @Serializable
    data object WishlistScreen : Routes()
}



