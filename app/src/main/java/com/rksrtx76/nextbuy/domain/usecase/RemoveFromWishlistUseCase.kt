package com.rksrtx76.nextbuy.domain.usecase

import com.rksrtx76.nextbuy.domain.model.Product
import com.rksrtx76.nextbuy.domain.repository.WishlistRepository
import javax.inject.Inject

class RemoveFromWishlistUseCase @Inject constructor(
    private val wishlistRepository: WishlistRepository
) {
    suspend operator fun invoke(productId : Int){
        wishlistRepository.removeFromWishlist(productId)
    }
}