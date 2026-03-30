package com.rksrtx76.nextbuy.data.repository

import com.rksrtx76.nextbuy.data.preference.UserPreferencesDataStore
import com.rksrtx76.nextbuy.domain.repository.UserPreferenceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserPreferenceRepositoryImpl @Inject constructor(
    private val userPreferencesDataStore: UserPreferencesDataStore,
) : UserPreferenceRepository {

    override val isFirstTimeLogin: Flow<Boolean> = userPreferencesDataStore.isFirstTimeLogin

    override val isLoggedIn: Flow<Boolean> = userPreferencesDataStore.isLoggedIn

    override suspend fun setFirstTimeLogin(isFirstTime: Boolean) {
        userPreferencesDataStore.setFirstTimeLogin(isFirstTime)
    }

    override suspend fun setLoggedIn(isLoggedIn: Boolean) {
        userPreferencesDataStore.setLoggedIn(isLoggedIn)
    }
}