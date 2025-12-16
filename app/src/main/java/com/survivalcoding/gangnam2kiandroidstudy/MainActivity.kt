package com.survivalcoding.gangnam2kiandroidstudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.survivalcoding.gangnam2kiandroidstudy.presentation.MyApp
import com.survivalcoding.gangnam2kiandroidstudy.presentation.home.HomeScreenRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.search.SearchRecipesRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.sign_in.SignInScreen
import com.survivalcoding.gangnam2kiandroidstudy.presentation.sign_up.SignUpScreen
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.Gangnam2kiAndroidStudyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
//            Gangnam2kiAndroidStudyTheme {
//                HomeScreenRoot()
//            }
            MyApp()
        }
    }
}