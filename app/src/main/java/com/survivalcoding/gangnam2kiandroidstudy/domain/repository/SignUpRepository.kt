package com.survivalcoding.gangnam2kiandroidstudy.domain.repository

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.User

interface SignUpRepository {
    suspend fun signUpWithEmail(name: String, email: String, password: String): Result<User>

    suspend fun signUpWithGoogle(idToken: String): Result<User>
}