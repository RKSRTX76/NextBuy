package com.rksrtx76.nextbuy.domain.repository

import com.rksrtx76.nextbuy.domain.model.UserProfile
import com.rksrtx76.nextbuy.util.Result
import kotlinx.coroutines.flow.Flow

interface UserProfileRepository {
    suspend fun saveUserProfile(userProfile : UserProfile) : Result<Unit>
    fun getUserProfile(userId : String) : Flow<Result<UserProfile>>
    suspend fun updateUserProfile(userProfile : UserProfile) : Result<Unit>
}