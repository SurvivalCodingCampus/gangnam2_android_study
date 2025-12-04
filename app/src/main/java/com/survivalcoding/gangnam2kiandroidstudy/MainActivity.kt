package com.survivalcoding.gangnam2kiandroidstudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.survivalcoding.gangnam2kiandroidstudy.kakao.presentation.KakaoChatScreen
import com.survivalcoding.gangnam2kiandroidstudy.kakao.presentation.component.ChatListItem
import com.survivalcoding.gangnam2kiandroidstudy.kakao.presentation.data.model.ChatItem
import com.survivalcoding.gangnam2kiandroidstudy.presentation.CounterScreen
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.BigButton

class MainActivity : ComponentActivity() {
    override fun onDestroy() {
        super.onDestroy()
        println("MainActivity: onDestroy")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        println("MainActivity: onCreate")
        enableEdgeToEdge()
        setContent {
            CounterScreen()

//            Column(
//                modifier = Modifier
//                    .fillMaxSize(),
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.Center
//            ) {
//                val chatItem = ChatItem(
//                    imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQK0pEZas5_qRK1NAKMGO27eFR6lvJ1LyHKp6B-j9k_BBOP4I6dA2Z_LjqNWLUjnt8v9yx_-wYDdQnR5w3WjWApHFa2dzndaGjd9epxsw&s=10",
//                    name = "김준수",
//                    message = "안녕하세요",
//                    time = "10:00",
//                    isRead = true,
//                )
//                ChatListItem(chatItem)
//                BigButton(
//                    text = "My Button",
//                    onClick = {
//                        println("클릭!!!!!!")
//                    }
//                )
//
//                Text("test")
//            }

        }
    }
}

// no 불변
data class Person(
    val name: String,
    val age: Int,
)

@Composable
fun PersonUI(
    person: Person,
    onClick: (Person) -> Unit = {},
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "${person.name} : ${person.age}",
            fontSize = 25.sp
        )
        ElevatedButton(onClick = {
//            // 화면 이동 X
//            // 상태 변경 X
//            println(person)

            onClick(person)
            onClick.invoke(person)

        }) {
            Text("버튼")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PersonUIPreview() {
    PersonUI(
        person = Person("홍길동", 20),
        onClick = {
            println(it)
        },
    )
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = name,
        fontSize = 25.sp,
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Greeting("John")
}

