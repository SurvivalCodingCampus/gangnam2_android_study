package com.survivalcoding.gangnam2kiandroidstudy.data.util

import java.text.SimpleDateFormat
import java.util.Locale

fun date(dateString: String): Long {
    return SimpleDateFormat("yyyy-MM-dd", Locale.KOREA).parse(dateString)?.time ?: 0L
}