package com.survivalcoding.gangnam2kiandroidstudy.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.survivalcoding.gangnam2kiandroidstudy.data.local.dao.BookmarkDao
import com.survivalcoding.gangnam2kiandroidstudy.data.local.dao.RecipeDao
import com.survivalcoding.gangnam2kiandroidstudy.data.local.entity.BookmarkEntity
import com.survivalcoding.gangnam2kiandroidstudy.data.local.entity.IngredientEntity
import com.survivalcoding.gangnam2kiandroidstudy.data.local.entity.ProcedureEntity
import com.survivalcoding.gangnam2kiandroidstudy.data.local.entity.RecipeEntity
import com.survivalcoding.gangnam2kiandroidstudy.data.local.entity.RecipeIngredientEntity


@Database(
    entities = [
        BookmarkEntity::class,
        RecipeEntity::class,
        IngredientEntity::class,
        ProcedureEntity::class,
        RecipeIngredientEntity::class
    ],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookmarkDao(): BookmarkDao
    abstract fun recipeDao(): RecipeDao
}
