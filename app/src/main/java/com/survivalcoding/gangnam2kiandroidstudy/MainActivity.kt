package com.survivalcoding.gangnam2kiandroidstudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home.HomeRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home.HomeScreen
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipes.SearchRecipesRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.util.KeyboardVisibilityHandler
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppColors

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KeyboardVisibilityHandler {

                // 전체 배경색 적용 Surface
                Surface(
                    color = AppColors.white, // 전체 배경 적용
                    modifier = Modifier
                        .fillMaxSize()
                        .windowInsetsPadding(WindowInsets.safeDrawing) // 상단 시스템 바 침범 방지
                ) {
//                    SplashScreen(
//                        modifier = Modifier.fillMaxSize()
//                    )

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 30.dp),
                    ) {
                       HomeRoot()

                        //FilterSearchBottomSheet()
                        SearchRecipesRoot()
                        //SavedRecipesScreen()


                        //SignInScreen()
                        //SignUpScreen()
                        // RecipeScreen()
                    }
                }
            }
        }
    }
}
