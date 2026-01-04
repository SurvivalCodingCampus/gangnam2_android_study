package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.SignUpRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseSignUpRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
) : SignUpRepository {

    override suspend fun signUpWithEmail(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).await()
    }

    override suspend fun signUpWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential).await()
    }
}
