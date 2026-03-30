package com.rksrtx76.nextbuy.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.rksrtx76.nextbuy.data.mapper.toDomain
import com.rksrtx76.nextbuy.data.mapper.toDto
import com.rksrtx76.nextbuy.data.remote.dto.UserProfileDto
import com.rksrtx76.nextbuy.domain.model.UserProfile
import com.rksrtx76.nextbuy.domain.repository.UserProfileRepository
import com.rksrtx76.nextbuy.util.Result
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserProfileRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : UserProfileRepository{

    companion object{
        private const val COLLECTION_PATH = "users"
    }

    override suspend fun saveUserProfile(userProfile: UserProfile) : Result<Unit>{
        return try {
            val userProfileDto = userProfile.toDto()
            firestore.collection(COLLECTION_PATH)
                .document(userProfileDto.userId)
                .set(userProfileDto)
                .await()
            Result.Success(Unit)
        }catch (e : Exception){
            Result.Error(e.message ?: "Unknown Error occurred while saving user profile")
        }
    }

    override fun getUserProfile(userId: String): Flow<Result<UserProfile>> = callbackFlow{
        try {
            val listener = firestore.collection(COLLECTION_PATH)
                .document(userId)
                .addSnapshotListener { snapshot, exception ->
                    if(exception != null){
                        trySend(Result.Error(exception.message ?: "Firestore error"))
                        close()
                        return@addSnapshotListener
                    }
                    if(snapshot != null && snapshot.exists()){
                        val userProfileDto = snapshot.toObject(UserProfileDto::class.java)
                        if(userProfileDto != null){
                            trySend(Result.Success(userProfileDto.toDomain()))
                        }else{
                            trySend(Result.Error("User profile is not found"))
                        }
                    }else {
                        trySend(Result.Error("Profile does not exist"))
                    }
                }
            awaitClose { listener.remove() }
        }
        catch (e : Exception){
            trySend(Result.Error(e.message ?: "Unknown error"))
            close(e)
        }
    }

    override suspend fun updateUserProfile(userProfile: UserProfile): Result<Unit> {
        return try {
            val userProfileDto = userProfile.toDto()
            firestore.collection(COLLECTION_PATH)
                .document(userProfileDto.userId)
                .set(userProfileDto, SetOptions.merge()) // merge updates the existing fields
                .await()
            Result.Success(Unit)
        }catch (e : Exception){
            Result.Error(e.message ?: "Unknown Error occurred while updating user profile")
        }
    }
}