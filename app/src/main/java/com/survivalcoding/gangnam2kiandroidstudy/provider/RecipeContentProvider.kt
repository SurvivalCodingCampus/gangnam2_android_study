package com.survivalcoding.gangnam2kiandroidstudy.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.survivalcoding.gangnam2kiandroidstudy.BuildConfig
import com.survivalcoding.gangnam2kiandroidstudy.core.di.ContentProviderEntryPoint
import dagger.hilt.android.EntryPointAccessors

class RecipeContentProvider : ContentProvider() {

    companion object {
        val AUTHORITY = BuildConfig.APPLICATION_ID + ".provider"
        const val TABLE_NAME = "bookmarks"
        const val ID_BOOKMARKS = 1
    }

    private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
        addURI(AUTHORITY, TABLE_NAME, ID_BOOKMARKS)
    }

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
        val appContext = context?.applicationContext ?: return null
        val entryPoint = EntryPointAccessors.fromApplication(appContext, ContentProviderEntryPoint::class.java)
        val dao = entryPoint.getBookmarkDao()

        return when (uriMatcher.match(uri)) {
            ID_BOOKMARKS -> {
                val cursor = dao.getAllBookmarksCursor()
                cursor.setNotificationUri(context?.contentResolver, uri)
                cursor
            }
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun getType(uri: Uri): String? {
        return when (uriMatcher.match(uri)) {
            ID_BOOKMARKS -> "vnd.android.cursor.dir/vnd.$AUTHORITY.$TABLE_NAME"
            else -> null
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null // Read-only
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return 0 // Read-only
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        return 0 // Read-only
    }
}
