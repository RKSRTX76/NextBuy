package com.rksrtx76.nextbuy.domain.usecase

import com.rksrtx76.nextbuy.domain.model.UserProfile
import com.rksrtx76.nextbuy.domain.repository.UserProfileRepository
import com.rksrtx76.nextbuy.util.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserProfileUseCase @Inject constructor(
    private val userProfileRepository: UserProfileRepository
) {
    operator fun invoke(userId : String) : Flow<Result<UserProfile>>{
        return userProfileRepository.getUserProfile(userId)
    }
}