package com.survivalcoding.gangnam2kiandroidstudy.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri
import androidx.room.Room
import com.survivalcoding.gangnam2kiandroidstudy.BuildConfig
import com.survivalcoding.gangnam2kiandroidstudy.data.bookmark.local.AppDatabase

/**
 * 외부 앱이 ContentResolver로 bookmark 테이블의 recipeId 목록만 조회할 수 있도록 제공하는 Provider.
 * 이 앱은 Provider 제공자 역할만 하며, 레시피 상세는 Reader 앱이 별도 데이터 소스로 조합한다.
 */
class BookmarkContentProvider : ContentProvider() {

    companion object {
        // 요구사항에 맞춘 authority와 경로
        const val AUTHORITY =
            BuildConfig.APPLICATION_ID + ".provider"
        private const val PATH_BOOKMARK = "bookmark"

        // 외부에서 사용하는 Content URI
        val CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY/$PATH_BOOKMARK")

        // UriMatcher 코드
        private const val CODE_BOOKMARK_LIST = 1

        // Cursor 최소 컬럼
        private const val COLUMN_RECIPE_ID = "recipe_id"

        // UriMatcher 세팅
        private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI(AUTHORITY, PATH_BOOKMARK, CODE_BOOKMARK_LIST)
        }
    }

    // Provider 내부에서 직접 Room DB를 생성해 사용 (Koin 미사용)
    private lateinit var database: AppDatabase

    override fun onCreate(): Boolean {
        val appContext = context?.applicationContext ?: return false
        database = Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "bookmark_db"
        ).build()
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor {
        // 이 Provider는 recipeId 목록 조회만 지원한다.
        return when (uriMatcher.match(uri)) {
            CODE_BOOKMARK_LIST -> {
                val cursor = MatrixCursor(arrayOf(COLUMN_RECIPE_ID))
                val ids = database.bookmarkDao().getAllBookmarkedRecipeIdsOnce()
                ids.forEach { id ->
                    cursor.addRow(arrayOf(id))
                }

                // ContentResolver가 변경 감지를 할 수 있도록 Notification URI 지정
                context?.contentResolver?.let { resolver ->
                    cursor.setNotificationUri(resolver, uri)
                }
                cursor
            }
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun getType(uri: Uri): String? {
        return when (uriMatcher.match(uri)) {
            CODE_BOOKMARK_LIST -> "vnd.android.cursor.dir/vnd.$AUTHORITY.$PATH_BOOKMARK"
            else -> null
        }
    }

    // 이 앱은 Provider 제공자 역할만 하므로 쓰기 기능은 지원하지 않는다.
    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        throw UnsupportedOperationException("Insert is not supported.")
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        throw UnsupportedOperationException("Update is not supported.")
    }

    override fun delete(
        uri: Uri,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        throw UnsupportedOperationException("Delete is not supported.")
    }
}
