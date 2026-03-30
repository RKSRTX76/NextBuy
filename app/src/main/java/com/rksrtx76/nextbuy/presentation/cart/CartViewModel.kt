package com.rksrtx76.nextbuy.presentation.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rksrtx76.nextbuy.presentation.cart.CartState
import com.rksrtx76.nextbuy.domain.model.Product
import com.rksrtx76.nextbuy.presentation.wishlist.WishlistState
import com.rksrtx76.nextbuy.domain.usecase.AddToCartUseCase
import com.rksrtx76.nextbuy.domain.usecase.ClearCartUseCase
import com.rksrtx76.nextbuy.domain.usecase.GetCartItemCountUseCase
import com.rksrtx76.nextbuy.domain.usecase.GetCartItemsUseCase
import com.rksrtx76.nextbuy.domain.usecase.RemoveFromCartUseCase
import com.rksrtx76.nextbuy.domain.usecase.UpdateQuantityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getCartItemsUseCase: GetCartItemsUseCase,
    private val addToCartUseCase: AddToCartUseCase,
    private val removeFromCartUseCase: RemoveFromCartUseCase,
    private val updateQuantityUseCase: UpdateQuantityUseCase,
    private val clearCartUseCase: ClearCartUseCase,
    private val getCartItemCountUseCase: GetCartItemCountUseCase
) : ViewModel(){

    private val _state = MutableStateFlow(CartState())
    val state = _state.asStateFlow()

    init {
        loadCartItems()
    }

    private fun loadCartItems() {
        viewModelScope.launch {
            _state.value = _state.value.copy(
                isLoading = true
            )

            try {
                getCartItemsUseCase().collect { items->
                    val totalPrice = items.sumOf {
                        it.product.discountedPrice * it.quantity
                    }
                    val totalItems = items.sumOf { it.quantity }

                    _state.value = _state.value.copy(
                        isLoading = false,
                        cartItems = items,
                        totalItems = totalItems,
                        totalPrice = totalPrice,
                        error = null
                    )
                }
            }catch (e : Exception){
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message ?: "Failed to load cart"
                )
            }
        }
    }

    fun addToCart(product : Product, quantity : Int = 1){
        viewModelScope.launch {
            try {
                addToCartUseCase(product,quantity)
            }catch (e : Exception){
                _state.value = _state.value.copy(
                    error = e.message ?: "Failed to add to cart"
                )
            }
        }
    }

    fun removeFromCart(productId : Int){
        viewModelScope.launch {
            try {
                removeFromCartUseCase(productId)
            }catch (e : Exception){
                _state.value = _state.value.copy(
                    error = e.message ?: "Failed to remove from cart"
                )
            }
        }
    }

    fun updateQuantity(productId : Int, quantity : Int){
        viewModelScope.launch {
            try {
                updateQuantityUseCase(productId,quantity)
            }catch (e : Exception){
                _state.value = _state.value.copy(
                    error = e.message ?: "Failed to Update quantity in cart"
                )
            }
        }
    }

    fun clearCart(){
        viewModelScope.launch {
            try {
                clearCartUseCase()
            }catch (e : Exception){
                _state.value = _state.value.copy(
                    error = e.message ?: "Failed to clear cart"
                )
            }
        }
    }
}