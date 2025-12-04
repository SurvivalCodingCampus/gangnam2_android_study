package com.survivalcoding.gangnam2kiandroidstudy.kakao.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.survivalcoding.gangnam2kiandroidstudy.kakao.presentation.data.model.ChatItem

@Composable
fun ChatListItem(
    chatItem: ChatItem,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        AsyncImage(
            model = chatItem.imageUrl,
            contentDescription = "profile image",
            modifier = Modifier.size(50.dp)
        )

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(chatItem.name)
            Text(chatItem.message)
        }

        Text(chatItem.time)
    }
}

@Preview(showBackground = true)
@Composable
private fun ChatListItemPreview() {
    val chatItem = ChatItem(
        imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQK0pEZas5_qRK1NAKMGO27eFR6lvJ1LyHKp6B-j9k_BBOP4I6dA2Z_LjqNWLUjnt8v9yx_-wYDdQnR5w3WjWApHFa2dzndaGjd9epxsw&s=10",
        name = "김준수",
        message = "안녕하세요",
        time = "10:00",
        isRead = true,
    )
    ChatListItem(chatItem)
}