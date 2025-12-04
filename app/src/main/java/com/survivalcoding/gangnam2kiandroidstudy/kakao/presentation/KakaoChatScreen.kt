@file:OptIn(ExperimentalMaterial3Api::class)

package com.survivalcoding.gangnam2kiandroidstudy.kakao.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.survivalcoding.gangnam2kiandroidstudy.kakao.presentation.component.AdItem
import com.survivalcoding.gangnam2kiandroidstudy.kakao.presentation.component.ChatListItem
import com.survivalcoding.gangnam2kiandroidstudy.kakao.presentation.data.model.Ad
import com.survivalcoding.gangnam2kiandroidstudy.kakao.presentation.data.model.ChatItem

@Composable
fun KakaoChatScreen(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            Row() {
                Text("채팅")
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    Icons.Default.Search,
                    contentDescription = "검색",
                )
                Icon(
                    Icons.Default.Add,
                    contentDescription = "검색",
                )
                Icon(
                    Icons.Default.Settings,
                    contentDescription = "검색",
                )
            }
        }
    ) {
        Box(
            modifier = modifier
                .padding(it)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    val ad = Ad(
                        title = "광고 제목",
                        description = "광고 설명",
                        imageUrl = "https://picsum.photos/200",
                        linkUrl = "https://google.com"
                    )
                    AdItem(ad)
                }

                val chatItem = ChatItem(
                    imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQK0pEZas5_qRK1NAKMGO27eFR6lvJ1LyHKp6B-j9k_BBOP4I6dA2Z_LjqNWLUjnt8v9yx_-wYDdQnR5w3WjWApHFa2dzndaGjd9epxsw&s=10",
                    name = "김준수",
                    message = "안녕하세요",
                    time = "10:00",
                    isRead = true,
                )

                items(20) {
                    ChatListItem(chatItem)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun KakaoChatScreenPreview() {
    KakaoChatScreen()
}