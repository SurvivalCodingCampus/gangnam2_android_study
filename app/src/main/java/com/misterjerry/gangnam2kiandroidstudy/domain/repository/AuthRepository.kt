package com.misterjerry.gangnam2kiandroidstudy.domain.repository

interface AuthRepository {
    suspend fun signInWithGoogle(idToken: String, accessToken: String? = null): Result<Unit>
    suspend fun signOut(): Result<Unit>

    suspend fun signUpWithEmail(
        email: String, password: String
    ): Result<Unit>

    suspend fun signInWithEmail(email: String, password: String): Result<Unit>
}