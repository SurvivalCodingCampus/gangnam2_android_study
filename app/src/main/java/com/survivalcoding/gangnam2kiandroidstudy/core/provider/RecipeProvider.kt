package com.survivalcoding.gangnam2kiandroidstudy.core.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.util.Log
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.context.GlobalContext

class RecipeProvider : ContentProvider(), KoinComponent {

    private val TAG = "RecipeProvider"

    private val recipeRepository: RecipeRepository?
        get() = if (GlobalContext.getOrNull() != null) get() else null

    companion object {
        private const val AUTHORITY = "com.survivalcoding.gangnam2kiandroidstudy.provider"
        private const val RECIPES = 1
        private const val RECIPE_ID = 2

        private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI(AUTHORITY, "recipes", RECIPES)
            addURI(AUTHORITY, "recipes/#", RECIPE_ID)
        }
    }

    override fun onCreate(): Boolean {
        Log.d(TAG, "onCreate: RecipeProvider initialized")
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        Log.d(TAG, "query: uri=$uri")
        
        val match = uriMatcher.match(uri)
        Log.d(TAG, "query: match result=$match")

        if (recipeRepository == null) {
            Log.e(TAG, "query: RecipeRepository is null (Koin not initialized?)")
            return null
        }

        return try {
            when (match) {
                RECIPES -> {
                    Log.d(TAG, "query: fetching all recipes cursor")
                    recipeRepository?.getRecipesCursor()
                }
                RECIPE_ID -> {
                    val id = uri.lastPathSegment?.toIntOrNull()
                    Log.d(TAG, "query: fetching recipe cursor for id=$id")
                    if (id == null) return null
                    recipeRepository?.getRecipeCursorById(id)
                }
                else -> {
                    Log.w(TAG, "query: no match for uri=$uri")
                    null
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "query: exception occurred", e)
            null
        }
    }

    override fun getType(uri: Uri): String? {
        return when (uriMatcher.match(uri)) {
            RECIPES -> "vnd.android.cursor.dir/$AUTHORITY.recipes"
            RECIPE_ID -> "vnd.android.cursor.item/$AUTHORITY.recipes"
            else -> null
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? = null
    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int = 0
    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int = 0
}
