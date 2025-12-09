package com.survivalcoding.gangnam2kiandroidstudy.data.datasource

import android.content.Context
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.data.dto.RecipeRootDto
import kotlinx.serialization.json.Json

class RecipeDataSourceImpl(
    private val context: Context, // Context를 주입받습니다.
) : RecipeDataSource {
    override suspend fun getRecipes(): RecipeRootDto {
        // res/raw/recipes.json 파일을 엽니다.
        val inputStream = context.resources.openRawResource(R.raw.recipes)

        // kotlinx.serialization.json.Json을 사용하여 스트림에서 직접 디코딩합니다.
        return Json.decodeFromString(inputStream.bufferedReader().use { it.readText() })
    }
}
