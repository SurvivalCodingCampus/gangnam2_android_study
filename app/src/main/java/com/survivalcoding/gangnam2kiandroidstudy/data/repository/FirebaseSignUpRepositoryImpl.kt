package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.User
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.SignUpRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseSignUpRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
) : SignUpRepository {

    override suspend fun signUpWithEmail(name: String, email: String, password: String): Result<User> {
        return try {
            val authResult = auth.createUserWithEmailAndPassword(email, password).await()
            val firebaseUser = authResult.user
            if (firebaseUser != null) {
                val profileUpdates = com.google.firebase.auth.UserProfileChangeRequest.Builder()
                    .setDisplayName(name)
                    .build()
                firebaseUser.updateProfile(profileUpdates).await()

                Result.success(
                    User(
                        uid = firebaseUser.uid,
                        email = firebaseUser.email,
                        name = name
                    )
                )
            } else {
                Result.failure(Exception("User creation failed"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun signUpWithGoogle(idToken: String): Result<User> {
        return try {
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            val authResult = auth.signInWithCredential(credential).await()
            val firebaseUser = authResult.user
            if (firebaseUser != null) {
                Result.success(
                    User(
                        uid = firebaseUser.uid,
                        email = firebaseUser.email,
                        name = firebaseUser.displayName
                    )
                )
            } else {
                Result.failure(Exception("Google sign in failed"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
