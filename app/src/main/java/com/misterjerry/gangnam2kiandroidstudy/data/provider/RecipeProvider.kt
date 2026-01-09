package com.misterjerry.gangnam2kiandroidstudy.data.provider

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import androidx.room.Room
import com.misterjerry.gangnam2kiandroidstudy.data.saved_recipes.SavedRecipesDatabase

class RecipeProvider : ContentProvider() {

    private lateinit var database: SavedRecipesDatabase

    // URI 매처 초기화
    private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
        // content://com.misterjerry.../saved_recipes -> 전체 목록 (CODE 1)
        addURI(
            RecipeContract.AUTHORITY,
            RecipeContract.PATH_SAVED_RECIPES,
            CODE_RECIPE_DIR
        )
        // content://com.misterjerry.../saved_recipes/# -> 특정 ID (CODE 2)
        addURI(
            RecipeContract.AUTHORITY,
            "${RecipeContract.PATH_SAVED_RECIPES}/#",
            CODE_RECIPE_ITEM
        )
    }

    override fun onCreate(): Boolean {
        // ContentProvider는 Application 시작 전에 초기화될 수 있으므로
        // context가 null이 아닐 때 DB 인스턴스를 확보합니다.
        context?.let {
            database = Room.databaseBuilder(
                it.applicationContext,
                SavedRecipesDatabase::class.java,
                "saved_recipes_db" // 기존 Room DB 이름과 정확히 일치해야 데이터 공유 가능
            ).build()
        }
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        val dao = database.savedRecipesDao()
        val cursor: Cursor? = when (uriMatcher.match(uri)) {
            CODE_RECIPE_DIR -> {
                dao.selectAllForProvider()
            }
            CODE_RECIPE_ITEM -> {
                // URI 맨 뒤의 숫자(ID) 파싱
                val id = ContentUris.parseId(uri)
                dao.selectByIdForProvider(id.toInt())
            }
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }

        // 데이터 변경 시 Observer에게 알림을 주기 위해 Notification URI 설정
        cursor?.setNotificationUri(context?.contentResolver, uri)
        return cursor
    }

    override fun getType(uri: Uri): String? {
        return when (uriMatcher.match(uri)) {
            CODE_RECIPE_DIR -> RecipeContract.SavedRecipeEntry.CONTENT_TYPE
            CODE_RECIPE_ITEM -> RecipeContract.SavedRecipeEntry.CONTENT_ITEM_TYPE
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    // --- 아래 메서드들은 현재 읽기 전용으로 구성하므로 구현하지 않음 ---

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        throw UnsupportedOperationException("Insert not supported")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        throw UnsupportedOperationException("Delete not supported")
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        throw UnsupportedOperationException("Update not supported")
    }

    companion object {
        private const val CODE_RECIPE_DIR = 1
        private const val CODE_RECIPE_ITEM = 2
    }
}
