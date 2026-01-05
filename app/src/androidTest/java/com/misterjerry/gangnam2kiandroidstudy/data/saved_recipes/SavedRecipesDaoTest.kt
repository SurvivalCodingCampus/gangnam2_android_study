package com.misterjerry.gangnam2kiandroidstudy.data.saved_recipes

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class SavedRecipesDaoTest {

    private lateinit var db: SavedRecipesDatabase
    private lateinit var dao: SavedRecipesDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, SavedRecipesDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
        dao = db.savedRecipesDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun 레시피_저장_및_조회_테스트() = runBlocking {
        val recipeId = 123
        dao.addSavedRecipe(recipeId)

        val savedRecipes = dao.getSavedRecipesList()
        assertEquals(1, savedRecipes.size)
        assertEquals(recipeId, savedRecipes[0].recipeId)
    }

    @Test
    fun 저장된_레시피_삭제_테스트() = runBlocking {
        val recipeId = 456
        dao.addSavedRecipe(recipeId)
        
        // Confirm added
        var savedRecipes = dao.getSavedRecipesList()
        assertEquals(1, savedRecipes.size)

        // Delete
        dao.deleteSavedRecipe(recipeId)

        // Confirm deleted
        savedRecipes = dao.getSavedRecipesList()
        assertTrue(savedRecipes.isEmpty())
    }

    @Test
    fun 중복된_레시피_저장_시_하나만_유지되는지_테스트() = runBlocking {
        val recipeId = 789
        dao.addSavedRecipe(recipeId)
        dao.addSavedRecipe(recipeId)

        val savedRecipes = dao.getSavedRecipesList()
        assertEquals(1, savedRecipes.size)
        assertEquals(recipeId, savedRecipes[0].recipeId)
    }
}
