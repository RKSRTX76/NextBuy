package com.rksrtx76.nextbuy.domain.usecase

import com.rksrtx76.nextbuy.domain.model.Product
import com.rksrtx76.nextbuy.domain.repository.ProductRepository
import com.rksrtx76.nextbuy.util.Result
import javax.inject.Inject

class GetProductsByCategoryUseCase @Inject constructor(
    private val productRepository: ProductRepository

) {
    suspend operator fun invoke(category : String) : Result<List<Product>>{
        return productRepository.getProductsByCategory(category)
    }
}