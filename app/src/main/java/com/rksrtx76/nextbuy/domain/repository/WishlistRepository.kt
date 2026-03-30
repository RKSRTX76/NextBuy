package com.rksrtx76.nextbuy.domain.repository

import com.rksrtx76.nextbuy.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface WishlistRepository {
    fun getWishlistProducts() : Flow<List<Product>>
    suspend fun addToWishlist(product: Product)
    suspend fun removeFromWishlist(productId : Int)
    suspend fun isInWishlist(productId : Int) : Boolean
    suspend fun clearWishlist()
    suspend fun getWishlistCount() : Int
}
