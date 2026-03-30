package com.rksrtx76.nextbuy.data.repository



import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.rksrtx76.nextbuy.util.Result
import com.rksrtx76.nextbuy.domain.repository.AuthRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth : FirebaseAuth
) : AuthRepository {
    override suspend fun signIn(
        email: String,
        password: String
    ): Result<String> {
        return try {
            firebaseAuth.signInWithEmailAndPassword(email,password).await()
            Result.Success("Login Successful")
        }catch (e : Exception){
            Result.Error(e.message ?: "Unknown error occurred during SignIn")
        }
    }

    override suspend fun signUp(
        email: String,
        password: String
    ): Result<String> {
        return try {
            firebaseAuth.createUserWithEmailAndPassword(email,password).await()
            Result.Success("SignUp Successful")
        }catch (e : Exception){
            Result.Error(e.message ?: "Unknown error occurred during SignUp")
        }
    }



    // Old way
//    override suspend fun signInWithGoogle(
//        account: GoogleSignInAccount
//    ): Result<String> {
//        return try {
//            val credential = GoogleAuthProvider
//                .getCredential(account.idToken, null)
//            val authResult = firebaseAuth
//                .signInWithCredential(credential)
//                .await()
//
//            Result.Success("Google sign-in successful")
//        }catch (e : Exception){
//            Result.Error(e.message ?: "Unknown error occurred during Google SignIn")
//        }
//    }

    // New way (shuttle change we passed idToken instead of account)
    override suspend fun signInWithGoogle(idToken: String): Result<String> {
        return try {
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            val authResult = firebaseAuth
                .signInWithCredential(credential)
                .await()
            Result.Success("Google sign-in successful")
        }catch (e : Exception){
            Result.Error(e.message ?: "Unknown error occurred during Google SignIn")
        }
    }




}