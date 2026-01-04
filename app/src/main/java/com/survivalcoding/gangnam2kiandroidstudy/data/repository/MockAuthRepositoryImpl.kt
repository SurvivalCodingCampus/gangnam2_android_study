package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.UserDto
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import com.survivalcoding.gangnam2kiandroidstudy.core.Result

class MockAuthRepositoryImpl : AuthRepository {
    private val _authState = MutableStateFlow<UserDto?>(null)
    override val authState: Flow<UserDto?> = _authState.asStateFlow()

    override suspend fun signInWithGoogle(idToken: String): Result<UserDto, String> {
        val user = UserDto(
            uid = "mock-google-uid",
            name = "Mock Google User",
            email = "google@test.com",
            photoUrl = null,
            nickname = "Mock Google User"
        )
        _authState.update { user }
        return Result.Success(user)
    }

    override suspend fun signInWithEmail(email: String, password: String): Result<UserDto, String> {
        if (email == "error@test.com") return Result.Error("Mock Login Failed")
        val user = UserDto(
            uid = "mock-email-uid",
            name = "Mock Email User",
            email = email,
            photoUrl = null,
            nickname = "Mock Email User"
        )
        _authState.update { user }
        return Result.Success(user)
    }

    override suspend fun signUpWithEmail(name: String, email: String, password: String): Result<UserDto, String> {
        val user = UserDto(
            uid = "mock-new-uid",
            name = name,
            email = email,
            photoUrl = null,
            nickname = name
        )
        _authState.update { user }
        return Result.Success(user)
    }

    override suspend fun signOut() {
        _authState.update { null }
    }
}