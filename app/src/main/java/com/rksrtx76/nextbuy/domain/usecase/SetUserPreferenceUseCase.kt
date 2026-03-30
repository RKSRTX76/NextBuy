package com.rksrtx76.nextbuy.domain.usecase

import com.rksrtx76.nextbuy.domain.repository.UserPreferenceRepository
import javax.inject.Inject

class SetUserPreferenceUseCase @Inject constructor(
    private val userPreferenceRepository: UserPreferenceRepository
) {
    suspend fun setFirstTimeLogin(isFirstTime: Boolean) {
        userPreferenceRepository.setFirstTimeLogin(isFirstTime)
    }

    suspend fun setLoggedIn(isLoggedIn: Boolean) {
        userPreferenceRepository.setLoggedIn(isLoggedIn)
    }
}