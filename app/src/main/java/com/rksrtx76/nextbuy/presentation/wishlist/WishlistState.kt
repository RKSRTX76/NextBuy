package com.rksrtx76.nextbuy.presentation.wishlist

import com.rksrtx76.nextbuy.domain.model.Product

data class WishlistState(
    val allProducts : List<Product> = emptyList(),
    val filteredProducts : List<Product> = emptyList(),
    val isLoading : Boolean = false,
    val searchQuery : String = "",
    val error : String? = "",
)