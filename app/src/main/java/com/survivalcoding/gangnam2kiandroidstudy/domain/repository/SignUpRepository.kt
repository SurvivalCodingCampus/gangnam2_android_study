package com.survivalcoding.gangnam2kiandroidstudy.domain.repository

interface SignUpRepository {
    suspend fun signUpWithEmail(email: String, password: String)

    suspend fun signUpWithGoogle(idToken: String)
}