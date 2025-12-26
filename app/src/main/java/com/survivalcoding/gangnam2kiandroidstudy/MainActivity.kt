package com.survivalcoding.gangnam2kiandroidstudy

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.survivalcoding.gangnam2kiandroidstudy.presentation.MyApp
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class MainActivity : ComponentActivity() {

    private val deepLinkChannel = Channel<String>(Channel.BUFFERED)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        intent?.data?.toString()?.let { uri ->
            deepLinkChannel.trySend(uri)
        }

        setContent {
            MyApp(deepLinkFlow = deepLinkChannel.receiveAsFlow())
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        intent.data?.toString()?.let { uri ->
            deepLinkChannel.trySend(uri)
        }
    }
}