package com.survivalcoding.gangnam2kiandroidstudy.domain.use_case

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.User
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.SignUpRepository
import javax.inject.Inject

class SignUpWithGoogleUseCase @Inject constructor(
    private val signUpRepository: SignUpRepository
) {
    suspend operator fun invoke(
        idToken: String
    ): Result<User> {
        return signUpRepository.signUpWithGoogle(idToken)
    }
}