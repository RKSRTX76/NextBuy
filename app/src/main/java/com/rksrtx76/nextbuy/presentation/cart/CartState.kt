package com.rksrtx76.nextbuy.presentation.cart

import com.rksrtx76.nextbuy.domain.model.CartItem

data class CartState(
    val cartItems: List<CartItem> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val totalItems: Int = 0,
    val totalPrice: Double = 0.0,
)