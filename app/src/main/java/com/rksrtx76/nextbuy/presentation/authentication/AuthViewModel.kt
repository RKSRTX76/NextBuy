package com.rksrtx76.nextbuy.presentation.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.rksrtx76.nextbuy.domain.model.UserProfile
import com.rksrtx76.nextbuy.domain.usecase.GetUserProfileUseCase
import com.rksrtx76.nextbuy.domain.usecase.GoogleSignInUseCase
import com.rksrtx76.nextbuy.domain.usecase.SaveUserProfileUseCase
import com.rksrtx76.nextbuy.util.Result
import com.rksrtx76.nextbuy.domain.usecase.SetUserPreferenceUseCase
import com.rksrtx76.nextbuy.domain.usecase.SignInUseCase
import com.rksrtx76.nextbuy.domain.usecase.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signInUseCase : SignInUseCase,
    private val signUpUseCase : SignUpUseCase,
    private val googleSignInUseCase: GoogleSignInUseCase,
    private val setUserPreferenceUseCase: SetUserPreferenceUseCase,
    private val firebaseAuth: FirebaseAuth,
    private val saveUserProfileUseCase: SaveUserProfileUseCase,
    private val getUserProfileUseCase: GetUserProfileUseCase
) : ViewModel() {

    // assigning null because I do not have Idle state in Resource and for authentication I can use initial state as Loading
    private val _authState = MutableStateFlow<Result<String>?>(null)
    val authState = _authState.asStateFlow()



    fun signIn(email : String, password : String){
        _authState.value = Result.Loading
        viewModelScope.launch {
            // no need try catch again because we handled exceptions for authentication in authRepository
            val result = signInUseCase(email, password)
            _authState.value = result
//            if(result is Result.Success){
//                setUserPreferenceUseCase.setFirstTimeLogin(false)
//                setUserPreferenceUseCase.setLoggedIn(true)
//            }
        }
    }

    fun signUp(email : String, password : String){
        _authState.value = Result.Loading
        viewModelScope.launch {
            val result = signUpUseCase(email, password)
            _authState.value = result
            if(result is Result.Success){
                setUserPreferenceUseCase.setFirstTimeLogin(false)
                setUserPreferenceUseCase.setLoggedIn(true)
                seedProfileIfFirstTime()
            }
        }
    }

    // old way
//    fun signInWithGoogle(account: GoogleSignInAccount){
//        _authState.value = Result.Loading
//        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                val result = googleSignInUseCase(account)
//                _authState.value = result
//                if(result is Result.Success){
//                    setUserPreferenceUseCase.setFirstTimeLogin(false)
//                    setUserPreferenceUseCase.setLoggedIn(true)
//                }
//            }catch (e : Exception){
//                _authState.value = Result.Error(e.message ?: "Google sign-in failed")
//            }
//        }
//    }

    // New way
    fun signInWithGoogle(idToken : String){
        _authState.value = Result.Loading
        viewModelScope.launch {
            try {
                val result = googleSignInUseCase(idToken)
                _authState.value = result
                if(result is Result.Success){
                    setUserPreferenceUseCase.setFirstTimeLogin(false)
                    setUserPreferenceUseCase.setLoggedIn(true)
                    seedProfileIfFirstTime()
                }
            }catch (e : Exception){
                _authState.value = Result.Error(e.message ?: "Google sign-in failed")
            }
        }
    }

    private fun seedProfileIfFirstTime(){
        val currentUser = firebaseAuth.currentUser ?: return
        viewModelScope.launch {
            val existing = getUserProfileUseCase(userId = currentUser.uid).first()
            val profileExists = existing is Result.Success && existing.data.userId.isNotEmpty()

            if(!profileExists){
                val seedProfile = UserProfile(
                    userId = currentUser.uid,
                    email = currentUser.email.orEmpty(),
                    profilePhotoUrl = currentUser.photoUrl?.toString().orEmpty(),
                    password = "",       // never store plain password
                    firstName = "",
                    lastName = "",
                    phoneNumber = "",
                    address = "",
                    city = "",
                    state = "",
                    pinCode = "",
                    country = ""
                )
                saveUserProfileUseCase(seedProfile)
            }

        }
    }


    fun resetAuthState(){
        _authState.value = null
    }


}