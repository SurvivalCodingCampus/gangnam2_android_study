package com.survivalcoding.gangnam2kiandroidstudy.data.data_source

import com.survivalcoding.gangnam2kiandroidstudy.data.dto.RecipeDTO
import com.survivalcoding.gangnam2kiandroidstudy.data.dto.RecipesWrapperDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class RemoteRecipeDataSourceImpl(
    private val client: HttpClient
) : RecipeDataSource {
    private val baseUrl = "https://raw.githubusercontent.com/junsuk5/mock_json/refs/heads/main/recipe/recipes.json"

    override suspend fun getRecipe(id: Long): RecipeDTO? {
        return try {
            val wrapper = client.get(baseUrl).body<RecipesWrapperDTO>()
            wrapper.recipes?.find { it?.id == id }
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getRecipes(): List<RecipeDTO?>? {
        return try {
            val wrapper = client.get(baseUrl).body<RecipesWrapperDTO>()
            println("remote에서 데이터 가져오기 성공!!!")
            wrapper.recipes
        } catch (e: Exception) {
            println("remote에서 데이터 가져오기 실패!!!: ${e.message}")
            e.printStackTrace()
            null
        }
    }
}