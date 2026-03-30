package com.rksrtx76.nextbuy.data.remote.api

import com.rksrtx76.nextbuy.data.remote.dto.CategoryDto
import com.rksrtx76.nextbuy.data.remote.dto.ProductsResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductApiService @Inject constructor(
    private val client : HttpClient
) {
    suspend fun getProducts(limit : Int = 0) : ProductsResponseDto{
        return client.get("products"){
            parameter("limit", limit)
        }.body()
    }

    suspend fun searchProducts(query : String) : ProductsResponseDto{
        return client.get("products/search"){
            parameter("q", query)
        }.body()
    }

    suspend fun getCategories() : List<CategoryDto>{
        return client.get("products/categories").body()
    }
    suspend fun getProductsByCategory(category : String) : ProductsResponseDto{
        return client.get("products/category/$category").body()
    }
}