package com.rksrtx76.nextbuy.data.preference

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.rksrtx76.nextbuy.domain.model.CartItem
import com.rksrtx76.nextbuy.domain.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json

class CartDataStore(private val context : Context) {

    companion object{
        // Context.datastore  is a variable name
        private val Context.datastore : DataStore<Preferences> by preferencesDataStore("cart_preferences")
        private val CART_ITEMS = stringPreferencesKey("cart_items")
    }

    private val json = Json{
        ignoreUnknownKeys = true
        isLenient = true
    }

    val cartItems : Flow<List<CartItem>> = context.datastore.data.map { preferences ->
        val itemsJson = preferences[CART_ITEMS] ?: "[]"
        try {
            json.decodeFromString<List<CartItem>>(itemsJson)
        }catch (e : Exception){
            emptyList()
        }
    }

    suspend fun addToCart(product : Product, quantity : Int = 1){
        context.datastore.edit { preferences ->
            val currentJson = preferences[CART_ITEMS] ?: "[]"
            val currentItems = try{
                json.decodeFromString<List<CartItem>>(currentJson)
            }catch (e : Exception){
                emptyList()
            }

            // already exist in cart
            val existingItemIndex = currentItems.indexOfFirst {
                it.product.id == product.id
            }
            // update quantity
            val updatedItems = if(existingItemIndex != -1){
                currentItems.toMutableList().apply {
                    this[existingItemIndex] = this[existingItemIndex].copy(
                        quantity = this[existingItemIndex].quantity + quantity
                    )
                }
            }else{
                currentItems + CartItem(product, quantity)
            }

            preferences[CART_ITEMS] = json.encodeToString(updatedItems)
        }
    }

    suspend fun removeFromCart(productId: Int){
        context.datastore.edit { preferences ->
            val currentJson = preferences[CART_ITEMS] ?: "[]"
            val currentItems = try{
                json.decodeFromString<List<CartItem>>(currentJson)
            }catch (e : Exception){
                emptyList()
            }

            val updatedItems = currentItems.filter {
                it.product.id != productId
            }
            preferences[CART_ITEMS] = json.encodeToString(updatedItems)
        }
    }

    suspend fun updateQuantity(productId : Int, newQuantity : Int){
        context.datastore.edit { preferences ->
            val currentJson = preferences[CART_ITEMS] ?: "[]"
            val currentItems = try{
                json.decodeFromString<List<CartItem>>(currentJson)
            }catch (e : Exception){
                emptyList()
            }

            val updatedItems = currentItems.map { item ->
                if(item.product.id == productId){
                    item.copy(quantity = newQuantity)
                }else{
                    item
                }
            }.filter { it.quantity > 0 } // remove items with quantity 0

            preferences[CART_ITEMS] = json.encodeToString(updatedItems)
        }
    }

    suspend fun clearCart(){
        context.datastore.edit { preferences ->
            preferences[CART_ITEMS] = "[]"
        }
    }

    suspend fun getCartItemCount() : Int{
        var count = 0
        context.datastore.data.map { preferences ->
            val currentJson = preferences[CART_ITEMS] ?: "[]"
            val currentItems = try{
                json.decodeFromString<List<CartItem>>(currentJson)
            }catch (e : Exception){
                emptyList()
            }
            count = currentItems.sumOf { it.quantity }
        }.collect {  }
        return count
    }

}