package com.rksrtx76.nextbuy.data.repository

import com.rksrtx76.nextbuy.data.remote.api.ProductApiService
import com.rksrtx76.nextbuy.data.mapper.toDomain
import com.rksrtx76.nextbuy.domain.model.Category
import com.rksrtx76.nextbuy.domain.model.Product
import com.rksrtx76.nextbuy.util.Result
import com.rksrtx76.nextbuy.domain.repository.ProductRepository
import javax.inject.Inject


class ProductRepositoryImpl @Inject constructor(
    private val productApiService: ProductApiService
) : ProductRepository{
    override suspend fun getProducts(): Result<List<Product>> {
        return try {
            val response = productApiService.getProducts()
            val result = response.productResponses.map { productResponse ->
                productResponse.toDomain()
            }
            Result.Success(result)
        }catch (e : Exception){
            Result.Error(e.localizedMessage ?: "Unknown error occurred")
        }
    }

    override suspend fun searchProducts(query: String): Result<List<Product>> {
        return try {
            val response = productApiService.searchProducts(query)
            val result = response.productResponses.map { productResponse ->
                productResponse.toDomain()
            }
            Result.Success(result)
        }catch (e : Exception){
            Result.Error(e.localizedMessage ?: "Unknown error occurred")
        }
    }

    override suspend fun getCategories(): Result<List<Category>> {
        return try {
            val response = productApiService.getCategories()
            val result = response.map { category ->
                category.toDomain()
            }
            Result.Success(result)
        }catch (e : Exception){
            Result.Error(e.localizedMessage ?: "Unknown error occurred")
        }
    }

    override suspend fun getProductsByCategory(category: String): Result<List<Product>> {
        return try {
            val response = productApiService.getProductsByCategory(category)
            val result = response.productResponses.map { productResponse ->
                productResponse.toDomain()
            }
            Result.Success(result)
        }catch (e : Exception){
            Result.Error(e.localizedMessage ?: "Unknown error occured")
        }
    }
}