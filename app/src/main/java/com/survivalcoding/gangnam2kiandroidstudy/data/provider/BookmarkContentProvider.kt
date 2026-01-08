package com.survivalcoding.gangnam2kiandroidstudy.data.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri
import com.survivalcoding.gangnam2kiandroidstudy.core.AppResult
import com.survivalcoding.gangnam2kiandroidstudy.data.dao.BookmarkDao
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BookmarkContentProvider : ContentProvider(), KoinComponent {

    private val bookmarkDao: BookmarkDao by inject()
    private val recipeRepository: RecipeRepository by inject()

    companion object {
        const val AUTHORITY = "com.survivalcoding.gangnam2kiandroidstudy.provider"
        const val TABLE_NAME = "bookmark"
        const val CODE_BOOKMARK_DIR = 1
        const val CODE_BOOKMARK_ITEM = 2

        // Column names
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_IMAGE_URL = "image_url"
        const val COLUMN_CHEF = "chef"
        const val COLUMN_TIME = "time"
        const val COLUMN_RATING = "rating"
        const val COLUMN_IS_SAVED = "is_saved"

        val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI(AUTHORITY, TABLE_NAME, CODE_BOOKMARK_DIR)
            addURI(AUTHORITY, "$TABLE_NAME/*", CODE_BOOKMARK_ITEM)
        }
    }

    override fun onCreate(): Boolean {
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?,
    ): Cursor? {
        return when (uriMatcher.match(uri)) {
            CODE_BOOKMARK_DIR -> {
                runBlocking {
                    try {
                        val profileId = 1L // Default profile ID
                        val bookmarks = bookmarkDao.findAllByProfileId(profileId).first()
                        val bookmarkSet = bookmarks.map { it.recipeId }.toSet()

                        val recipeResult = recipeRepository.getSavedRecipes()
                        val recipes = if (recipeResult is AppResult.Success) {
                            recipeResult.data.filter { it.id in bookmarkSet }
                        } else {
                            emptyList()
                        }

                        val columns = arrayOf(
                            COLUMN_ID, COLUMN_NAME, COLUMN_IMAGE_URL,
                            COLUMN_CHEF, COLUMN_TIME, COLUMN_RATING, COLUMN_IS_SAVED,
                        )
                        val cursor = MatrixCursor(columns)

                        recipes.forEach { recipe ->
                            cursor.addRow(
                                arrayOf(
                                    recipe.id,
                                    recipe.name,
                                    recipe.imageUrl,
                                    recipe.chef,
                                    recipe.time,
                                    recipe.rating,
                                    if (bookmarkSet.contains(recipe.id)) 1 else 0,
                                ),
                            )
                        }
                        cursor.setNotificationUri(context?.contentResolver, uri)
                        cursor
                    } catch (e: Exception) {
                        e.printStackTrace()
                        null
                    }
                }
            }
            else -> null
        }
    }

    override fun getType(uri: Uri): String? {
        return when (uriMatcher.match(uri)) {
            CODE_BOOKMARK_DIR -> "vnd.android.cursor.dir/$AUTHORITY.$TABLE_NAME"
            CODE_BOOKMARK_ITEM -> "vnd.android.cursor.item/$AUTHORITY.$TABLE_NAME"
            else -> null
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return 0
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?,
    ): Int {
        return 0
    }
}
