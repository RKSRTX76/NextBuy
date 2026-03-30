package com.rksrtx76.nextbuy.domain.repository

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.rksrtx76.nextbuy.util.Result

interface AuthRepository {
    suspend fun signIn(email : String, password : String) : Result<String>
    suspend fun signUp(email : String, password : String) : Result<String>
    // Using Credential manager approach not old legacy Play Services approach
    suspend fun signInWithGoogle(idToken : String): Result<String>
}