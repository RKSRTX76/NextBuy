package com.rksrtx76.nextbuy.domain.usecase

import com.rksrtx76.nextbuy.util.Result
import com.rksrtx76.nextbuy.domain.repository.AuthRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val repository: AuthRepository
){
    suspend operator fun invoke(email : String, password : String) : Result<String>{
        // check some conditions
        // email must not empty
        if (email.isBlank()){
            return Result.Error("Email can not be empty")
        }
        // password should be >= 8
        if(password.length < 8){
            return Result.Error("Password must be at least 8 characters")
        }
        // email format
        if(!email.contains("@") || !email.contains(".")){
            return Result.Error("Invalid email format")
        }

        // will execute when every condition met
        return repository.signUp(email,password)
    }
}