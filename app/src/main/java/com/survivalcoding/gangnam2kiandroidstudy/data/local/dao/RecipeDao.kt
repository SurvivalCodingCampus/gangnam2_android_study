package com.survivalcoding.gangnam2kiandroidstudy.data.local.dao

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.survivalcoding.gangnam2kiandroidstudy.data.local.entity.IngredientEntity
import com.survivalcoding.gangnam2kiandroidstudy.data.local.entity.ProcedureEntity
import com.survivalcoding.gangnam2kiandroidstudy.data.local.entity.RecipeEntity
import com.survivalcoding.gangnam2kiandroidstudy.data.local.entity.RecipeIngredientEntity

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipes")
    suspend fun getAllRecipes(): List<RecipeEntity>

    @Query("SELECT * FROM recipes")
    fun getAllRecipesCursor(): Cursor

    @Query("SELECT * FROM recipes WHERE id = :id")
    suspend fun getRecipeById(id: Int): RecipeEntity?

    @Query("SELECT * FROM recipes WHERE id = :id")
    fun getRecipeByIdCursor(id: Int): Cursor

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipes(recipes: List<RecipeEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIngredients(ingredients: List<IngredientEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipeIngredients(items: List<RecipeIngredientEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProcedures(items: List<ProcedureEntity>)

    @Query("SELECT * FROM recipe_ingredients WHERE recipeId = :recipeId")
    suspend fun getRecipeIngredients(recipeId: Int): List<RecipeIngredientEntity>

    @Query("SELECT * FROM ingredients WHERE id IN (:ids)")
    suspend fun getIngredientsByIds(ids: List<Int>): List<IngredientEntity>
    
    // Join queries could be added here if needed for deeper data fetching
}
