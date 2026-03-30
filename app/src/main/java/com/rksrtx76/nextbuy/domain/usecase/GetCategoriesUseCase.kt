package com.rksrtx76.nextbuy.domain.usecase

import com.rksrtx76.nextbuy.domain.model.Category
import com.rksrtx76.nextbuy.domain.model.Product
import com.rksrtx76.nextbuy.domain.repository.ProductRepository
import com.rksrtx76.nextbuy.util.Result
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke() : Result<List<Category>>{
        return productRepository.getCategories()
    }
}