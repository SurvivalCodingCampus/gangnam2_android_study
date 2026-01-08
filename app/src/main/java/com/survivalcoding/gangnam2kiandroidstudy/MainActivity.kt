package com.survivalcoding.gangnam2kiandroidstudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.survivalcoding.gangnam2kiandroidstudy.data.bookmark.entity.BookmarkEntity
import com.survivalcoding.gangnam2kiandroidstudy.data.bookmark.local.AppDatabase
import com.survivalcoding.gangnam2kiandroidstudy.presentation.MyApp
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private var deepLinkUri by mutableStateOf<String?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java,
                "bookmark_db"
            ).build()

            db.bookmarkDao().addBookmark(
                BookmarkEntity(recipeId = 2),
                BookmarkEntity(recipeId = 3),
                BookmarkEntity(recipeId = 6),
            )
        }

        setContent {
            MyApp(
                deepLinkUri = deepLinkUri,
                onDeepLinkHandled = { deepLinkUri = null }
            )
        }
    }
}