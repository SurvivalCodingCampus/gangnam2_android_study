package com.survivalcoding.gangnam2kiandroidstudy.data.local

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import org.koin.android.ext.android.inject

class RecipeContentProvider : ContentProvider() {

    private val recipeDao: RecipeDao by inject()

    companion object {
        private const val AUTHORITY = "com.survivalcoding.gangnam2kiandroidstudy.provider"
        private const val BOOKMARKED_RECIPES = 1
        private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI(AUTHORITY, "bookmarked_recipes", BOOKMARKED_RECIPES)
        }
    }

    override fun onCreate(): Boolean = true

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        return when (uriMatcher.match(uri)) {
            BOOKMARKED_RECIPES -> {
                recipeDao.getBookmarkedRecipesCursor().apply {
                    setNotificationUri(context?.contentResolver, uri)
                }
            }
            else -> null
        }
    }

    override fun getType(uri: Uri): String? {
        return when (uriMatcher.match(uri)) {
            BOOKMARKED_RECIPES -> "vnd.android.cursor.dir/vnd.$AUTHORITY.bookmarked_recipes"
            else -> null
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? = null

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int = 0

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int = 0
}
