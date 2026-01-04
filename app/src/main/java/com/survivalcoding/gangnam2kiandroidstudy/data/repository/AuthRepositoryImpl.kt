package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.UserDto
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.AuthRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : AuthRepository {

    override val authState: Flow<UserDto?> = callbackFlow {
        val listener = FirebaseAuth.AuthStateListener { auth ->
            trySend(auth.currentUser?.toDto())
        }
        auth.addAuthStateListener(listener)
        awaitClose { auth.removeAuthStateListener(listener) }
    }

    override suspend fun signInWithGoogle(idToken: String): Result<UserDto, String> {
        return try {
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            val result = auth.signInWithCredential(credential).await()
            val userProfile = result.user?.toDto() ?: return Result.Error("Google Sign In failed: User is null")
            
            // Firestore에 유저 정보 동기화 (실체화 보장)
            saveUserToFirestore(result.user!!, userProfile.name)

            Result.Success(userProfile)
        } catch (e: Exception) {
            Result.Error(e.message ?: "Google Sign In failed")
        }
    }

    override suspend fun signInWithEmail(email: String, password: String): Result<UserDto, String> {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            val user = result.user?.toDto() ?: return Result.Error("Sign In failed: User is null")
            Result.Success(user)
        } catch (e: Exception) {
            Result.Error(e.message ?: "Sign In failed")
        }
    }

    override suspend fun signUpWithEmail(name: String, email: String, password: String): Result<UserDto, String> {
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            val userProfile = result.user?.toDto() ?: return Result.Error("Sign Up failed: User is null")
            
            // Firestore에 유저 문서 생성
            saveUserToFirestore(result.user!!, name)

            Result.Success(userProfile.copy(nickname = name, name = name))
        } catch (e: Exception) {
            Result.Error(e.message ?: "Sign Up failed")
        }
    }

    override suspend fun signOut() {
        auth.signOut()
    }

    private suspend fun saveUserToFirestore(firebaseUser: FirebaseUser, overrideName: String? = null) {
        val domainDto = firebaseUser.toDto()
        val userDto = com.survivalcoding.gangnam2kiandroidstudy.data.dto.UserDto.fromDomain(
            domainDto.copy(nickname = overrideName ?: domainDto.nickname)
        )
        firestore.collection("users")
            .document(firebaseUser.uid)
            .collection("user_details")
            .document("info")
            .set(userDto)
            .await()
    }

    private fun FirebaseUser.toDto(): UserDto {
        return UserDto(
            uid = uid,
            name = displayName,
            email = email,
            photoUrl = photoUrl?.toString(),
            nickname = displayName
        )
    }
}
