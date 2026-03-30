package com.rksrtx76.nextbuy.domain.usecase

import com.rksrtx76.nextbuy.domain.model.Category
import com.rksrtx76.nextbuy.domain.model.Product
import com.rksrtx76.nextbuy.domain.repository.ProductRepository
import com.rksrtx76.nextbuy.util.Result
import javax.inject.Inject

class SearchProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(query : String) : Result<List<Product>> {
        if(query.isBlank()) return productRepository.getProducts()
        return productRepository.searchProducts(query)
    }
}