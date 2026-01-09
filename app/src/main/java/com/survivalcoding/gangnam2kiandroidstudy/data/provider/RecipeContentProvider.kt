package com.survivalcoding.gangnam2kiandroidstudy.data.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import com.survivalcoding.gangnam2kiandroidstudy.data.local.AppDatabase
import org.koin.android.ext.android.inject

class RecipeContentProvider : ContentProvider() {

    // Using Koin to get the Database instance. 
    // Note: ensure Koin is initialized in Application.onCreate before this is used.
    private val appDatabase: AppDatabase by inject()

    override fun onCreate(): Boolean {
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        // Basic implementation: Only supports querying all recipes or by ID if needed.
        // URI format: content://com.survivalcoding.gangnam2kiandroidstudy.provider/recipes
        
        return when (uri.lastPathSegment) {
            "recipes" -> {
                val cursor = appDatabase.recipeDao().getAllRecipesCursor()
                cursor.setNotificationUri(context?.contentResolver, uri)
                cursor
            }
            else -> null
        }
    }

    override fun getType(uri: Uri): String? {
        return "vnd.android.cursor.dir/vnd.com.survivalcoding.gangnam2kiandroidstudy.provider.recipes"
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null // Read-only for now
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return 0
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        return 0
    }
}
