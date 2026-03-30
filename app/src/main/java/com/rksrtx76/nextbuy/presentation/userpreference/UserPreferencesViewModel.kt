package com.rksrtx76.nextbuy.presentation.userpreference

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rksrtx76.nextbuy.presentation.userpreference.UserPreferenceState
import com.rksrtx76.nextbuy.domain.usecase.GetUserPreferenceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserPreferencesViewModel @Inject constructor(
    private val getUserPreferenceUseCase: GetUserPreferenceUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(UserPreferenceState())
    val state = _state.asStateFlow()

    init {
        observeUserPreferences()
    }

    private fun observeUserPreferences(){
        viewModelScope.launch {
            combine(
                getUserPreferenceUseCase.isFirstTimeLogin(),
                getUserPreferenceUseCase.isLoggedIn()
            ){ isFirstTime, isLoggedIn ->

                UserPreferenceState(
                    firstTimeLogin = isFirstTime,
                    isLoggedIn = isLoggedIn,
                    isLoading = false
                )
            }.collect { newState ->
                _state.value = newState
            }
        }
    }


//    fun setFirstTimeLogin(isFirstTime : Boolean){
//        viewModelScope.launch {
//            setUserPreferenceUseCase.setFirstTimeLogin(isFirstTime)
//        }
//    }
//
//    fun setLoggedIn(isLoggedIn : Boolean){
//        viewModelScope.launch {
//            setUserPreferenceUseCase.setLoggedIn(isLoggedIn)
//        }
//    }



}

