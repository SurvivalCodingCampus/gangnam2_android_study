package com.survivalcoding.gangnam2kiandroidstudy.data.model

import kotlinx.serialization.Serializable

@Serializable
data class RecipesResponse(val recipes: List<Recipe>)

@Serializable
data class RecipesIngredientsResponse(val recipesIngredients: List<RecipeIngredientEntity>)

@Serializable
data class IngredientsResponse(val ingredients: List<IngredientEntity>)

@Serializable
data class ProceduresResponse(val procedures: List<Procedure>)

@Serializable
data class ProfilesResponse(val profiles: List<Chef>)