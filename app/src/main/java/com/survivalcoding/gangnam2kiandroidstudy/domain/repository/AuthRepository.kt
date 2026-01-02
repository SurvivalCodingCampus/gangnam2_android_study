package com.survivalcoding.gangnam2kiandroidstudy.domain.repository

interface AuthRepository {
    suspend fun signUpWithEmail(email: String, password: String)

    suspend fun signInWithEmail(email: String, password: String)

    suspend fun signInWithGoogle(idToken: String)

    suspend fun signOut()
}