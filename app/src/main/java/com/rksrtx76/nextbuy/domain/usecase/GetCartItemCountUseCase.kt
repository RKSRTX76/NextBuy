package com.rksrtx76.nextbuy.domain.usecase

import com.rksrtx76.nextbuy.domain.repository.CartRepository
import javax.inject.Inject

class GetCartItemCountUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {
    suspend operator fun invoke() : Int {
        return cartRepository.getCartItemCount()
    }
}