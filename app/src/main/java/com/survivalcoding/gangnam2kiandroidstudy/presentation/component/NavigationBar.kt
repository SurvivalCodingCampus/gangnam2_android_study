package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.core.routing.Route
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavigationBar(
//    body: @Composable (modifier: Modifier) -> Unit,
//    backStack: NavBackStack<NavKey>,
    currentRoute: NavKey?,
    modifier: Modifier = Modifier,
    navigate: (NavKey) -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(106.dp)
                .align(Alignment.BottomCenter)
        ) {
            val width = size.width
            val height = size.height

            val centerX = width / 2f
            val radius = 24.dp.toPx()
            val depth = 30.dp.toPx()


            val path = Path().apply {
                moveTo(0f, 0f)
                lineTo(centerX - radius * 1.6f, 0f)

                cubicTo(
                    centerX - radius * 1.2f, 0f,
                    centerX - radius, depth,
                    centerX, depth
                )

                cubicTo(
                    centerX + radius, depth,
                    centerX + radius * 1.2f, 0f,
                    centerX + radius * 1.6f, 0f
                )

                lineTo(width, 0f)
                lineTo(width, height)
                lineTo(0f, height)
                close()
            }

            drawPath(path, color = Color.White)
        }

        // ---------- 좌측 아이콘 2개 ----------
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 38.dp)
                .align(Alignment.TopCenter),
        ) {
            Spacer(Modifier.width(40.dp))
            Icon(
                painter = painterResource(R.drawable.home_2),
                contentDescription = "Home",
                tint = if (currentRoute == Route.Home) AppColors.primary40 else AppColors.gray4
            )

            Spacer(Modifier.width(40.dp))
            Icon(
                painter = painterResource(R.drawable.union),
                contentDescription = "Saved Recipes",
                tint = if (currentRoute == Route.SavedRecipes) AppColors.primary40 else AppColors.gray4
            )

            Spacer(modifier = Modifier.width(119.dp))
            Icon(
                painter = painterResource(R.drawable.notification_bing),
                contentDescription = "Notification",
                tint = if (currentRoute == Route.SavedRecipes) AppColors.primary40 else AppColors.gray4
            )

            Spacer(modifier = Modifier.width(40.dp))
            Icon(
                painter = painterResource(R.drawable.profile_img),
                contentDescription = "Profile",
                tint = if (currentRoute == Route.SavedRecipes) AppColors.primary40 else AppColors.gray4
            )
        }

        Box(
            modifier = Modifier
                .size(48.dp)
                .align(Alignment.TopCenter)
                .offset(y = (-23).dp)
                .background(AppColors.primary100, CircleShape)
                .clickable { /* TODO */ },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_add),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

@Composable
fun BottomBarIcon(
    painter: Painter,
    selected: Boolean,
    onClick: () -> Unit,
) {
    IconButton(modifier = Modifier.size(24.dp), onClick = onClick) {
        Icon(
            painter = painter,
            contentDescription = null,
            tint = if (selected) AppColors.primary40 else AppColors.gray4,
            modifier = if (selected) {
                Modifier
                    .border(1.5.dp, AppColors.primary100)
                    .padding(6.dp)
            } else {
                Modifier.size(28.dp)
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun NavigationBarPreview() {
    NavigationBar(
        currentRoute = Route.Home
    )
}
