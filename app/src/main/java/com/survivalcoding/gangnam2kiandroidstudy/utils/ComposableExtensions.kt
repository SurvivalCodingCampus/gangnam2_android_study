package com.survivalcoding.gangnam2kiandroidstudy.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalInspectionMode
import com.survivalcoding.gangnam2kiandroidstudy.R

val String.orPreview: Any
    @Composable get() = if (LocalInspectionMode.current) R.drawable.ic_profile_outline else this


