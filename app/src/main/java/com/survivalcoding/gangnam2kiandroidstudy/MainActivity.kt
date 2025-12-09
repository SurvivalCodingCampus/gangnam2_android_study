package com.survivalcoding.gangnam2kiandroidstudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.survivalcoding.gangnam2kiandroidstudy.presentation.signin.SignInScreen
import com.survivalcoding.gangnam2kiandroidstudy.presentation.signup.SignUpScreen
import com.survivalcoding.gangnam2kiandroidstudy.presentation.splash.SplashScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SplashScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Day2ComponentsPreview() {
    SignUpScreen()
}
