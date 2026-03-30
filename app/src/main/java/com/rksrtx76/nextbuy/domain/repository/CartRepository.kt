package com.rksrtx76.nextbuy.domain.repository

import com.rksrtx76.nextbuy.domain.model.CartItem
import com.rksrtx76.nextbuy.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    fun getCartItems() : Flow<List<CartItem>>
    suspend fun addToCart(product : Product, quantity : Int = 1)
    suspend fun removeFromCart(productId : Int)
    suspend fun updateQuantity(productId : Int, newQuantity : Int)
    suspend fun clearCart()
    suspend fun getCartItemCount() : Int

}