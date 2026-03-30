package com.rksrtx76.nextbuy.domain.repository

import com.rksrtx76.nextbuy.domain.model.Category
import com.rksrtx76.nextbuy.domain.model.Product
import com.rksrtx76.nextbuy.util.Result

interface ProductRepository {
    suspend fun getProducts() : Result<List<Product>>
    suspend fun searchProducts(query : String) : Result<List<Product>>
    suspend fun getCategories() : Result<List<Category>>
    suspend fun getProductsByCategory(category : String) : Result<List<Product>>
}

