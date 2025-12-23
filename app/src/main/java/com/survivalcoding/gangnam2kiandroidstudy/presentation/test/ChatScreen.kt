package com.survivalcoding.gangnam2kiandroidstudy.presentation.test

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// To make the example runnable, define Message and MessageItem here.
// In a real app, these would likely be in their own files.
class Message(val text: String)

@Composable
fun MessageItem(message: Message) {
    Text(text = message.text, modifier = Modifier.padding(8.dp))
}


/**
 * 메시지 목록을 보여주는 화면입니다.
 * 새로운 메시지가 추가되면 자동으로 목록의 가장 아래로 스크롤됩니다.
 *
 * @param message 보여줄 메시지 목록
 */
@Composable
fun ChatScreen(
    message: List<Message>
) {
    // 자동 스크롤 여부를 저장하는 상태 변수입니다.
    // 사용자가 스크롤을 위로 올리는 등 상호작용을 하면 false로 변경하여 자동 스크롤을 막을 수 있습니다. (현재 로직에는 미구현)
    var autoScroll by remember { mutableStateOf(true) }
    // LazyColumn의 스크롤 상태를 기억하고 제어하기 위한 객체입니다.
    val listState = rememberLazyListState()

    // 스크롤이 첫번째 아이템보다 위에 있는지 확인하는 변수
    val showButton by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 0
        }
    }

    // LaunchedEffect는 컴포지션에 진입했을 때나, key 값이 변경되었을 때 한 번만 실행되는 코루틴을 실행합니다.
    // 여기서는 message 리스트가 변경될 때마다 실행됩니다.
    LaunchedEffect(message) {
        // autoScroll이 true이고 메시지가 비어있지 않은 경우에만 자동 스크롤을 실행합니다.
        if (autoScroll && message.isNotEmpty()) {
            // 메시지 리스트의 가장 마지막 아이템으로 즉시 스크롤합니다.
            listState.scrollToItem(message.size - 1)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // 스크롤 가능한 리스트를 보여주는 컴포저블입니다.
        LazyColumn(
            state = listState, // 위에서 생성한 listState를 연결하여 스크롤 제어가 가능하도록 합니다.
            modifier = Modifier.fillMaxSize()
        ) {
            // message 리스트의 각 아이템을 화면에 그립니다.
            items(message) { message ->
                MessageItem(message)
            }
        }

        // 버튼이 보여야 할 때만 보이도록 AnimatedVisibility 사용
        AnimatedVisibility(
            visible = showButton,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            ScrollToTopButton(listState = listState)
        }
    }

}


@Preview(showBackground = true)
@Composable
fun ChatScreenPreview() {
    ChatScreen(
        // List 생성자를 사용하여 20개의 미리보기 메시지를 간결하게 생성합니다.
        message = List(40) { index -> Message("Preview ${index + 1}") }
    )
}
