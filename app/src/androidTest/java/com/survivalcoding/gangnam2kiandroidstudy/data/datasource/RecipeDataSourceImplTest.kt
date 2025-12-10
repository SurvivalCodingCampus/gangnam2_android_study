package com.survivalcoding.gangnam2kiandroidstudy.data.datasource

import android.content.Context
import android.util.Log
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RecipeDataSourceImplTest {
    private lateinit var dataSource: RecipeDataSource

    @Before
    fun setUp() {
        // ApplicationProvider를 통해 테스트용 Context를 가져옵니다.
        val context = ApplicationProvider.getApplicationContext<Context>()
        // 테스트할 대상인 RecipeDataSourceImpl을 초기화합니다.
        dataSource = RecipeDataSourceImpl(context)
    }

    @Test
    fun getRecipes() = runTest {
        // when
        val recipeRootDto = dataSource.getRecipes()

        // then
        assertTrue("불러온 레시피 리스트가 비어있습니다.", recipeRootDto.recipes!!.isNotEmpty())

        println("테스트 성공: 총 ${recipeRootDto.recipes.size}개의 레시피를 불러왔습니다.")
        println("첫 번째 레시피: ${recipeRootDto.recipes.firstOrNull()}")
        Log.d("RecipeDataSourceImplTest", "테스트 성공: 총 ${recipeRootDto.recipes.size}개의 레시피를 불러왔습니다.")

    }

}