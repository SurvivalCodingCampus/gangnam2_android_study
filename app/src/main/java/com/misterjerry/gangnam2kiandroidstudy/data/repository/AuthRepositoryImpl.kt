package com.misterjerry.gangnam2kiandroidstudy.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.misterjerry.gangnam2kiandroidstudy.domain.repository.AuthRepository
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {

    override suspend fun signInWithGoogle(idToken: String, accessToken: String?): Result<Unit> {
        return try {
            val credential = GoogleAuthProvider.getCredential(idToken, accessToken)
            firebaseAuth.signInWithCredential(credential).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun signOut(): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun signUpWithEmail(
        email: String,
        password: String
    ): Result<Unit> {
        return try {
            // Firebase API 호출: 이메일/비밀번호로 유저 생성
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun signInWithEmail(
        email: String,
        password: String
    ): Result<Unit> {
        return try {
            // Firebase API 호출: 이메일/비밀번호로 로그인
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}