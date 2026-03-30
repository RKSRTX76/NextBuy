package com.rksrtx76.nextbuy.domain.usecase

import com.rksrtx76.nextbuy.domain.model.UserProfile
import com.rksrtx76.nextbuy.domain.repository.UserProfileRepository
import com.rksrtx76.nextbuy.util.Result
import javax.inject.Inject

class SaveUserProfileUseCase @Inject constructor(
    private val userProfileRepository: UserProfileRepository
) {
    suspend operator fun invoke(userProfile : UserProfile) : Result<Unit> {
        return userProfileRepository.saveUserProfile(userProfile)
    }
}