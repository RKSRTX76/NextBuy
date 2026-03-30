package com.rksrtx76.nextbuy.domain.usecase

import com.rksrtx76.nextbuy.util.Result
import com.rksrtx76.nextbuy.domain.repository.AuthRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    // invoke() will auto called when use case used
    suspend operator fun invoke(email : String, password : String) : Result<String> {
        return repository.signIn(email,password)
    }
}