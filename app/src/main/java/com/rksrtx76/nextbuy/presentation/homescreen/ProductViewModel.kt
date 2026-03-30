package com.rksrtx76.nextbuy.presentation.homescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.rksrtx76.nextbuy.domain.model.Category
import com.rksrtx76.nextbuy.domain.model.Product
import com.rksrtx76.nextbuy.domain.usecase.GetCategoriesUseCase
import com.rksrtx76.nextbuy.domain.usecase.GetProductsByCategoryUseCase
import com.rksrtx76.nextbuy.domain.usecase.GetProductsUseCase
import com.rksrtx76.nextbuy.domain.usecase.SearchProductsUseCase
import com.rksrtx76.nextbuy.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val getProductsByCategoryUseCase: GetProductsByCategoryUseCase,
    private val searchProductsUseCase: SearchProductsUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val firebaseAuth: FirebaseAuth
) : ViewModel(){
    private val _productState = MutableStateFlow<Result<List<Product>>>(Result.Loading)
    val productState = _productState.asStateFlow()

    private val _categoryState = MutableStateFlow<Result<List<Category>>>(Result.Loading)
    val categoryState = _categoryState.asStateFlow()

    private val _selectedCategory = MutableStateFlow<String?>(null)
    val selectedCategory = _selectedCategory.asStateFlow()

    private val _profilePhotoUrl = MutableStateFlow<String?>(null)
    val profilePhotoUrl = _profilePhotoUrl.asStateFlow()

    private val _selectedProduct = MutableStateFlow<Product?>(null)
    val selectedProduct = _selectedProduct.asStateFlow()

    private var searchJob : Job? = null

    init {
        loadProducts()
        loadCategories()
        loadUserProfilePhoto()
    }

    private fun loadUserProfilePhoto() {
        val user = firebaseAuth.currentUser
        _profilePhotoUrl.value = user?.photoUrl.toString()
    }

    fun findProductById(productId : Int){
        val state = _productState.value
        if(state is Result.Success){
            _selectedProduct.value = state.data.find {
                it.id == productId
            }
        }
    }


    fun loadProducts() {
        _productState.value = Result.Loading
        viewModelScope.launch {
            try {
                val result = getProductsUseCase()
                _productState.value = result
            }catch (e : Exception){
                _productState.value = Result.Error(e.message ?: "Unknown Error")
            }
        }
    }

    fun retryLoading(){
        loadProducts()
    }

    fun loadCategories() {
        _categoryState.value = Result.Loading
        viewModelScope.launch {
            try {
                val result = getCategoriesUseCase()
                _categoryState.value = result
            }catch (e : Exception){
                _categoryState.value = Result.Error(e.message ?: "Unknown Error")
            }
        }
    }

    fun searchProducts(query : String){
        // Cancel previous search job
        searchJob?.cancel()
        // clear selected category when searching
        _selectedCategory.value = null

        searchJob = viewModelScope.launch {
            delay(200) // wait 200ms before executing
            _productState.value = Result.Loading
            try {
                val result = searchProductsUseCase(query)
                _productState.value = result
            }catch (e : Exception){
                _productState.value = Result.Error(e.message ?: "Unknown Error")
            }
        }
    }


    fun filterByCategory(categorySlug : String){
        _selectedCategory.value = categorySlug
        _productState.value = Result.Loading

        viewModelScope.launch {
            try {
                val result = getProductsByCategoryUseCase(categorySlug)
                _productState.value = result
            }catch (e : Exception){
                _productState.value = Result.Error(e.message ?: "Unknown Error")
            }
        }
    }

    fun clearCategoryFilter(){
        _selectedCategory.value = null
        loadProducts()
    }


}