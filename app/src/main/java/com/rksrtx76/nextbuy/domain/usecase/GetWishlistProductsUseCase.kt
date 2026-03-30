package com.rksrtx76.nextbuy.domain.usecase

import com.rksrtx76.nextbuy.domain.model.Product
import com.rksrtx76.nextbuy.domain.repository.WishlistRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWishlistProductsUseCase @Inject constructor(
    private val wishlistRepository: WishlistRepository
) {
    operator fun invoke() : Flow<List<Product>>{
        return wishlistRepository.getWishlistProducts()
    }
}