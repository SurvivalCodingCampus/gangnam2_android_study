package com.survivalcoding.gangnam2kiandroidstudy.domain.use_case

import com.google.firebase.firestore.auth.User
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.SignUpRepository

class SignUpWithGoogleUseCase(
    private val signUpRepository: SignUpRepository
) {
    suspend operator fun invoke(
        idToken: String
    ): Result<User> {
        return signUpRepository.signUpWithGoogle(idToken)
    }
}