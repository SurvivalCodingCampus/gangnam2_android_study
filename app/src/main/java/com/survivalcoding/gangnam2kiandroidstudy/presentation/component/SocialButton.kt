package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import android.graphics.BlurMaskFilter
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors

@Composable
fun SocialButton(
    id: Int,
    description: String,
    onClick: () -> Unit = {}
    ) {
    Box(
        modifier = Modifier
            .size(44.dp)
            .figmaShadow(
                color = Color(0xFF696969),
                alpha = 0.1f,
                blur = 5.dp,
                spread = 3.dp,
                offsetX = 0.dp,
                offsetY = 0.dp,
                cornerRadius = 10.dp
            )
            .background(color = AppColors.white, shape = RoundedCornerShape(10.dp))
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center

    ) {
        Image(
            painter = painterResource(id),
            contentDescription = description
        )
    }
}

fun Modifier.figmaShadow(
    color: Color = Color.Black,
    alpha: Float = 0.1f,
    blur: Dp = 0.dp,
    spread: Dp = 0.dp,
    offsetX: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    cornerRadius: Dp = 0.dp
) = this.drawBehind {

    val shadowColor = color.copy(alpha = alpha).toArgb()

    drawIntoCanvas { canvas ->
        val paint = Paint()
        val frameworkPaint = paint.asFrameworkPaint()
        frameworkPaint.color = shadowColor

        val blurPixel = blur.toPx()
        if (blurPixel > 0) {
            frameworkPaint.maskFilter = BlurMaskFilter(
                blurPixel,
                BlurMaskFilter.Blur.NORMAL
            )
        }

        val spreadPixel = spread.toPx()
        val leftPixel = (0f - spreadPixel) + offsetX.toPx()
        val topPixel = (0f - spreadPixel) + offsetY.toPx()
        val rightPixel = (size.width + spreadPixel) + offsetX.toPx()
        val bottomPixel = (size.height + spreadPixel) + offsetY.toPx()

        val radiusPixel = cornerRadius.toPx()

        canvas.drawRoundRect(
            left = leftPixel,
            top = topPixel,
            right = rightPixel,
            bottom = bottomPixel,
            radiusX = radiusPixel,
            radiusY = radiusPixel,
            paint = paint
        )
    }
}