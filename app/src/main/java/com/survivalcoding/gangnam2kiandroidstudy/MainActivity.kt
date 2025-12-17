package com.survivalcoding.gangnam2kiandroidstudy

import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.survivalcoding.gangnam2kiandroidstudy.kakao.presentation.KakaoChatScreen
import com.survivalcoding.gangnam2kiandroidstudy.kakao.presentation.component.ChatListItem
import com.survivalcoding.gangnam2kiandroidstudy.kakao.presentation.data.model.ChatItem
import com.survivalcoding.gangnam2kiandroidstudy.presentation.CounterScreen
import com.survivalcoding.gangnam2kiandroidstudy.presentation.MainViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.BigButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.RatingDialog
import dagger.hilt.android.AndroidEntryPoint
import java.io.Serializable
import kotlin.jvm.java

data class Hero(val name: String, val age: Int): Parcelable {
    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        TODO("Not yet implemented")
    }
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    // 상태

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // 화면 돌아갈때
        // 상태 저장
//        val hero = Hero("홍", 10)
//        outState.putParcelable("hero", hero)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        // 상태를 복구
    }

    override fun onDestroy() {
        super.onDestroy()
        println("MainActivity: onDestroy")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val myIntent = Intent(this, SavedRecipesActivity::class.java)
        startActivity(myIntent)

        val name = Hero("", 10)
        println("Hero: ${name.hashCode()}")

        this.applicationContext

        // 매직
//        val viewModel: MainViewModel by viewModels {
//            MainViewModel.Factory
//        }
//        println("MainViewModel1: ${viewModel.hashCode()}")

        println("MainActivity: onCreate")
        enableEdgeToEdge()
        setContent {
//            // 컴포즈 전용
//            val viewModel2: MainViewModel = viewModel(
//                factory = MainViewModel.Factory
//            )
//            println("MainViewModel2: ${viewModel2.hashCode()}")
//
//            viewModel.state.collectAsStateWithLifecycle()

            var rating by rememberSaveable { mutableStateOf(0) }

            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column {
                    Text("$rating")
                    RatingDialog(
                        onSubmit = {
                            rating = it
                        },
                    )
                }
            }

//            CounterScreen()

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

