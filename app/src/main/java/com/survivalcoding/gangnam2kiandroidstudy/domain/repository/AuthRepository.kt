package com.survivalcoding.gangnam2kiandroidstudy.domain.repository

import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.UserDto
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val authState: Flow<UserDto?>
    suspend fun signInWithGoogle(idToken: String): Result<UserDto, String>
    suspend fun signInWithEmail(email: String, password: String): Result<UserDto, String>
    suspend fun signUpWithEmail(name: String, email: String, password: String): Result<UserDto, String>
    suspend fun signOut()
}
