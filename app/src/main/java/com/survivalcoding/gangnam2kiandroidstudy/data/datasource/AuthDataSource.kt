package com.survivalcoding.gangnam2kiandroidstudy.data.datasource

interface AuthDataSource {
    suspend fun signUpWithEmail(email: String, password: String)

    suspend fun signInWithEmail(email: String, password: String)

    suspend fun signInWithGoogle(idToken: String)

    suspend fun signOut()
}