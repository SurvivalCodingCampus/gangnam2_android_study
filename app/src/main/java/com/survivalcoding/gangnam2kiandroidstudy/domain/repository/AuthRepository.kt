package com.survivalcoding.gangnam2kiandroidstudy.domain.repository

interface AuthRepository {
    fun getCurrentUserUid(): String?
    fun signOut()
    // Add other authentication methods as needed
}
