package com.survivalcoding.gangnam2kiandroidstudy

import androidx.activity.ComponentActivity
import dagger.hilt.android.AndroidEntryPoint

// Hilt가 적용된 Compose UI를 테스트 환경에서 안전하게 띄우기 위한 최소한의 Activity 컨테이너
@AndroidEntryPoint
class TestActivity : ComponentActivity()