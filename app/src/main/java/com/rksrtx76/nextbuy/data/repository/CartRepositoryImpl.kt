package com.rksrtx76.nextbuy.data.repository

import com.rksrtx76.nextbuy.data.preference.CartDataStore
import com.rksrtx76.nextbuy.domain.model.CartItem
import com.rksrtx76.nextbuy.domain.model.Product
import com.rksrtx76.nextbuy.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val cartDataStore: CartDataStore
) : CartRepository{
    override fun getCartItems(): Flow<List<CartItem>> {
        return cartDataStore.cartItems
    }

    override suspend fun addToCart(
        product: Product,
        quantity: Int
    ) {
        return cartDataStore.addToCart(product,quantity)
    }

    override suspend fun removeFromCart(productId: Int) {
        return cartDataStore.removeFromCart(productId)
    }

    override suspend fun updateQuantity(productId: Int, newQuantity: Int) {
        return cartDataStore.updateQuantity(productId,newQuantity)
    }

    override suspend fun clearCart() {
        return cartDataStore.clearCart()
    }

    override suspend fun getCartItemCount(): Int {
        return cartDataStore.getCartItemCount()
    }
}