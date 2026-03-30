package com.rksrtx76.nextbuy.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.rksrtx76.nextbuy.R
import com.rksrtx76.nextbuy.presentation.navigation.Routes

sealed class BottomNavItem(
    val title : String,
    val icon : Int,
    val route : Routes
) {
    data object Home : BottomNavItem("Home", R.drawable.ic_home, Routes.HomeScreen)
    data object Search : BottomNavItem("Search", R.drawable.ic_search, Routes.SearchScreen)
    data object Cart : BottomNavItem("Cart", R.drawable.ic_round_background_cart, Routes.CartScreen)
    data object Wishlist : BottomNavItem("Wishlist", R.drawable.ic_heart, Routes.WishlistScreen)
    data object Profile : BottomNavItem("Profile",R.drawable.person, Routes.ProfileScreen)
}