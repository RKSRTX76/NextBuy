package com.rksrtx76.nextbuy.presentation.wishlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rksrtx76.nextbuy.domain.model.Product
import com.rksrtx76.nextbuy.presentation.wishlist.WishlistState
import com.rksrtx76.nextbuy.domain.usecase.AddToWishlistUseCase
import com.rksrtx76.nextbuy.domain.usecase.CheckIsInWishlistUseCase
import com.rksrtx76.nextbuy.domain.usecase.ClearWishlistUseCase
import com.rksrtx76.nextbuy.domain.usecase.GetWishlistCountUseCase
import com.rksrtx76.nextbuy.domain.usecase.GetWishlistProductsUseCase
import com.rksrtx76.nextbuy.domain.usecase.RemoveFromWishlistUseCase

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishlistViewModel @Inject constructor(
    private val getWishlistProductsUseCase: GetWishlistProductsUseCase,
    private val addToWishlistUseCase: AddToWishlistUseCase,
    private val removeFromWishlistUseCase: RemoveFromWishlistUseCase,
    private val checkIsInWishlistUseCase: CheckIsInWishlistUseCase,
    private val clearWishlistUseCase: ClearWishlistUseCase,
    private val getWishlistCountUseCase: GetWishlistCountUseCase,
) : ViewModel(){

    private val _state = MutableStateFlow(WishlistState())
    val state = _state.asStateFlow()

    init {
        loadWishlistProducts()
    }

    private fun loadWishlistProducts() {
        viewModelScope.launch {
            _state.value = _state.value.copy(
                isLoading = true
            )
            try {
                // returns flow so handle
                getWishlistProductsUseCase().collectLatest { products ->
                    _state.value = _state.value.copy(
                        isLoading = false,
                        allProducts = products,
                        filteredProducts = products,
                        error = null
                    )
                }
            }catch (e : Exception){
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message ?: "Unknown Error"
                )
            }
        }
    }

    fun searchProducts(query : String){
        viewModelScope.launch {
            _state.value = _state.value.copy(
                isLoading = true
            )
            try {
                // if query is empty return all products else show filtered products as per query
                val filteredProducts = if(query.isBlank()){
                    _state.value.allProducts
                }else{
                    _state.value.allProducts.filter { product ->
                        product.title.contains(query, ignoreCase = true) ||
                        product.description.contains(query, ignoreCase = true) ||
                        product.brand.contains(query, ignoreCase = true) ||
                        product.category.contains(query, ignoreCase = true)
                    }
                }
                _state.value = _state.value.copy(
                    filteredProducts = filteredProducts,
                    isLoading = false
                )
            }catch (e : Exception){
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message ?: "Failed to load wishlist"
                )
            }
        }
    }

    fun addToWishlist(product : Product){
        viewModelScope.launch {
            try {
                addToWishlistUseCase(product)
            }catch (e : Exception){
                _state.value = _state.value.copy(
                    error = e.message ?: "Failed to add to wishlist"
                )
            }
        }
    }

    fun removeFromWishlist(productId : Int){
        viewModelScope.launch {
            try {
                removeFromWishlistUseCase(productId)
            }catch (e : Exception){
                _state.value = _state.value.copy(
                    error = e.message ?: "Failed to remove from wishlist"
                )
            }
        }
    }

    suspend fun isInWishlist(productId : Int) : Boolean{
        return try {
            checkIsInWishlistUseCase(productId)
        }catch (e : Exception){
            false
        }
    }


}