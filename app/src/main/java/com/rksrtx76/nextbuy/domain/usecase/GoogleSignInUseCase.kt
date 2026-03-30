package com.rksrtx76.nextbuy.domain.usecase

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.rksrtx76.nextbuy.domain.repository.AuthRepository
import com.rksrtx76.nextbuy.util.Result
import javax.inject.Inject

class GoogleSignInUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
//    suspend operator fun invoke(account: GoogleSignInAccount) : Result<String>{
//        return authRepository.signInWithGoogle(account)
//    }
    suspend operator fun invoke(idToken : String) : Result<String>{
        return authRepository.signInWithGoogle(idToken)
    }
}