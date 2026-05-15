package com.rksrtx76.nextbuy.presentation.navigation

import kotlinx.serialization.Serializable
import okhttp3.Route

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

    @Serializable
    data class AddressScreen(val amount : Double) : Routes()

    @Serializable
    data class PaymentSuccessScreen(val paymentId : String, val amount : Double) : Routes()

    @Serializable
    data class PaymentFailedScreen(val errorMessage : String, val amount : Double) : Routes()
}



