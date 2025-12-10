package com.survivalcoding.gangnam2kiandroidstudy.data.model

import kotlinx.serialization.Serializable

@Serializable
data class RecipesResponse(val recipes: List<Recipe>)