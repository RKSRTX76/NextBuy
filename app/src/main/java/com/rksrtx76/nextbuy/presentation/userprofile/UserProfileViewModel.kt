package com.rksrtx76.nextbuy.presentation.userprofile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.rksrtx76.nextbuy.domain.usecase.GetUserProfileUseCase
import com.rksrtx76.nextbuy.domain.usecase.SaveUserProfileUseCase
import com.rksrtx76.nextbuy.domain.usecase.UpdateUserProfileUseCase
import com.rksrtx76.nextbuy.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val saveUserProfileUseCase: SaveUserProfileUseCase,
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val updateUserProfileUseCase: UpdateUserProfileUseCase,
    private val firebaseAuth: FirebaseAuth
) : ViewModel(){
    private val _state = MutableStateFlow(UserProfileState())
    val state = _state.asStateFlow()

    private val currentUser = firebaseAuth.currentUser
    private val userId = currentUser?.uid.orEmpty()

    init {
        loadUserProfile()
    }

    fun onEvent(event: UserProfileEvent){
        when(event){
            is UserProfileEvent.AddressChanged -> {
                _state.update {
                    it.copy(userProfile = it.userProfile.copy(address = event.value))
                }
            }

            is UserProfileEvent.PasswordChanged -> {
                _state.update {
                    it.copy(userProfile = it.userProfile.copy(password = event.value))
                }
            }

            is UserProfileEvent.CityChanged -> {
                _state.update {
                    it.copy(userProfile = it.userProfile.copy(city = event.value))
                }
            }
            is UserProfileEvent.CountryChanged -> {
                _state.update {
                    it.copy(userProfile = it.userProfile.copy(country = event.value))
                }
            }
            is UserProfileEvent.FirstNameChanged -> {
                _state.update {
                    it.copy(userProfile = it.userProfile.copy(firstName = event.value))
                }
            }
            is UserProfileEvent.LastNameChanged -> {
                _state.update {
                    it.copy(userProfile = it.userProfile.copy(lastName = event.value))
                }
            }
            is UserProfileEvent.PhoneNumberChanged -> {
                _state.update {
                    it.copy(userProfile = it.userProfile.copy(phoneNumber = event.value))
                }
            }
            is UserProfileEvent.StateChanged -> {
                _state.update {
                    it.copy(userProfile = it.userProfile.copy(state = event.value))
                }
            }
            is UserProfileEvent.PinCodeChanged -> {
                _state.update {
                    it.copy(userProfile = it.userProfile.copy(pinCode = event.value))
                }
            }
            is UserProfileEvent.ProfilePhotoChanged -> {
                _state.update {
                    it.copy(userProfile = it.userProfile.copy(profilePhotoUrl = event.value))
                }
            }
            is UserProfileEvent.ResetSaveSuccess -> {
                _state.update {
                    it.copy(saveSuccess = false)
                }
            }
            is UserProfileEvent.ClearError -> {
                _state.update {
                    it.copy(error = null)
                }
            }
            is UserProfileEvent.SaveProfile -> saveProfile()
            is UserProfileEvent.UpdateProfile -> updateProfile()
        }
    }

    private fun loadUserProfile(){
        if(userId.isEmpty()){
            _state.update {
                it.copy(error = "User not logged in")
            }
            return
        }
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            getUserProfileUseCase(userId).collectLatest { result ->
                when(result){
                    Result.Loading -> {
                        _state.update {
                            it.copy(isLoading = true)
                        }
                    }
                    is Result.Success -> {
                        // At first Userprofile will be empty, but we need to fetch email and profile photo and userId(do not show)
                        // as firebase has those details
                        val userProfile = result.data.copy(
                            userId = currentUser?.uid.orEmpty(),
                            profilePhotoUrl = currentUser?.photoUrl?.toString().orEmpty(),
                            email = currentUser?.email.orEmpty()
                        )
                        _state.update {
                            it.copy(
                                isLoading = false,
                                userProfile = userProfile,
                            )
                        }
                    }
                    is Result.Error -> {
                        _state.update {
                            it.copy(isLoading = false, error = null) // don't show an error to the user on first open
                        }
                    }
                }
            }
        }
    }

    // Not using this anywhere
    private fun saveProfile(){
        if(userId.isEmpty()){
            _state.update {
                it.copy(error = "User not logged in")
            }
            return
        }

        viewModelScope.launch {
            _state.update { it.copy(isSaving = true) }
            val userProfile = _state.value.userProfile.copy(userId = userId)
            when(val result = saveUserProfileUseCase(userProfile)){
                Result.Loading -> Unit
                is Result.Success -> {
                    _state.update {
                        it.copy(
                            isSaving = false,
                            saveSuccess = true,
                        )
                    }
                }
                is Result.Error -> {
                    _state.update {
                        it.copy(isSaving = false, error = result.message)
                    }
                }
            }
        }
    }

    private fun updateProfile(){
        if(userId.isEmpty()){
            _state.update {
                it.copy(error = "User not logged in")
            }
            return
        }
        viewModelScope.launch {
            _state.update { it.copy(isSaving = true) }

            val userProfile = _state.value.userProfile.copy(userId = userId)
            when(val result = updateUserProfileUseCase(userProfile)){
                Result.Loading -> Unit
                is Result.Success -> {
                    _state.update {
                        it.copy(
                            isSaving = false,
                            saveSuccess = true,
                        )
                    }
                }
                is Result.Error -> {
                    _state.update {
                        it.copy(isSaving = false, error = result.message)
                    }
                }
            }
        }

    }

}