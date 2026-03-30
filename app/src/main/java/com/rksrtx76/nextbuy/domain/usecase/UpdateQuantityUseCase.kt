package com.rksrtx76.nextbuy.domain.usecase

import com.rksrtx76.nextbuy.domain.repository.CartRepository
import javax.inject.Inject

class UpdateQuantityUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {
    suspend operator fun invoke(productId : Int, newQuantity : Int) {
        cartRepository.updateQuantity(productId, newQuantity)
    }
}