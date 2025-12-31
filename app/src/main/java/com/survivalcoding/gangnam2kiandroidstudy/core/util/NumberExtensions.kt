package com.survivalcoding.gangnam2kiandroidstudy.core.util

import android.icu.number.Notation
import android.icu.number.NumberFormatter
import android.os.Build
import java.text.NumberFormat
import java.util.Locale

fun Long.formatCompactNumber(
    locale: Locale = Locale.getDefault(),
): String {
    val number = this

    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        NumberFormatter.withLocale(locale)
            .notation(Notation.compactShort())
            .format(number)
            .toString()
    } else {
        when {
            number < 1000 -> number.toString()
            number < 1000000 -> {
                val format = NumberFormat.getInstance(locale)
                format.maximumFractionDigits = 1
                "${format.format(number / 1000.0)}K"
            }
            else -> {
                val format = NumberFormat.getInstance(locale)
                format.maximumFractionDigits = 1
                "${format.format(number / 1000000.0)}M"
            }
        }
    }
}

fun Int.formatCompactNumber(
    locale: Locale = Locale.getDefault(),
): String {
    return this.toLong().formatCompactNumber(locale)
}