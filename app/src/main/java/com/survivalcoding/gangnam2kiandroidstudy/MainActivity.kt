package com.survivalcoding.gangnam2kiandroidstudy

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.survivalcoding.gangnam2kiandroidstudy.core.routing.NavigationRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.util.KeyboardVisibilityHandler
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    // 딥 링크 URI를 상태로 관리
    private var deepLinkUri by mutableStateOf<String?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 시작 시 인텐트 데이터 확인
        deepLinkUri = intent.dataString

        setContent {
            KeyboardVisibilityHandler {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .windowInsetsPadding(WindowInsets.safeDrawing)
                ) {
                    NavigationRoot(
                        modifier = Modifier.fillMaxSize(),
                        deepLinkUri = deepLinkUri, // URI 전달
                    )
                }
            }
        }
    }

    // 앱이 켜져 있는 상태에서 딥 링크를 클릭했을 때 대응
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        println("디버그: onNewIntent 호출됨! 데이터: ${intent.dataString}")
        deepLinkUri = intent.dataString
    }
}
