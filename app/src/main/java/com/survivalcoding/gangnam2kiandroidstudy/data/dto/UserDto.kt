package com.survivalcoding.gangnam2kiandroidstudy.data.dto

import com.google.firebase.firestore.PropertyName
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.UserDto

data class UserDto(
    @get:PropertyName("uid") @set:PropertyName("uid") var uid: String = "",
    @get:PropertyName("email") @set:PropertyName("email") var email: String = "",
    @get:PropertyName("nickname") @set:PropertyName("nickname") var nickname: String = "",
    @get:PropertyName("photoUrl") @set:PropertyName("photoUrl") var photoUrl: String = "",
    @get:PropertyName("createdAt") @set:PropertyName("createdAt") var createdAt: Long = 0L
) {
    fun toDomain(): UserDto {
        return UserDto(
            uid = uid,
            name = nickname,
            email = email,
            photoUrl = photoUrl,
            nickname = nickname
        )
    }

    companion object {
        fun fromDomain(domain: UserDto): com.survivalcoding.gangnam2kiandroidstudy.data.dto.UserDto {
            return com.survivalcoding.gangnam2kiandroidstudy.data.dto.UserDto(
                uid = domain.uid,
                email = domain.email ?: "",
                nickname = domain.nickname ?: domain.name ?: "",
                photoUrl = domain.photoUrl ?: "",
                createdAt = System.currentTimeMillis()
            )
        }
    }
}
