package com.survivalcoding.gangnam2kiandroidstudy.data.dto

import com.google.firebase.firestore.PropertyName

data class BookmarkDto(
    @get:PropertyName("userId") @set:PropertyName("userId") var userId: String = "",
    @get:PropertyName("recipeId") @set:PropertyName("recipeId") var recipeId: Long = 0L
)
