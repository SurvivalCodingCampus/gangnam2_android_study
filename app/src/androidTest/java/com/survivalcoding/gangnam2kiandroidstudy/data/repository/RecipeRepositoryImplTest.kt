package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.RecipeDataSourceImpl
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Test

class RecipeRepositoryImplTest {
    @Test
    fun getRecipes() = runTest {
        val dataSource = RecipeDataSourceImpl(
            context = ApplicationProvider.getApplicationContext<Context>()
        )
        val repository = RecipeRepositoryImpl(dataSource)

        // when
        val recipes = repository.getRecipes()

        // then
        assertTrue("불러온 레시피 리스트가 비어있습니다.", recipes.isNotEmpty())
        println(recipes[0].toString())

    }

}