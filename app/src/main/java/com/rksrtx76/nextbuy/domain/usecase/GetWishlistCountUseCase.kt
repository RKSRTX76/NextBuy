package com.rksrtx76.nextbuy.domain.usecase

import com.rksrtx76.nextbuy.domain.repository.WishlistRepository
import javax.inject.Inject

class GetWishlistCountUseCase @Inject constructor(
    private val wishlistRepository: WishlistRepository
) {
    suspend operator fun invoke() : Int{
        return wishlistRepository.getWishlistCount()
    }
}