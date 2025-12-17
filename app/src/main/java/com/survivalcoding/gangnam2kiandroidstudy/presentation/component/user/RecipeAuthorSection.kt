package com.survivalcoding.gangnam2kiandroidstudy.presentation.component.user

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Chef
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.buttons.SmallButton
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppTextStyles

@Composable
fun RecipeAuthorSection(
    author: Chef,
    isFollowing: Boolean,
    onFollowClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        // Profile Image + Info
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = author.imageUrls,
                contentDescription = "author profile",
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    text = author.name,
                    style = AppTextStyles.smallTextBold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(R.drawable.ic_location),
                        contentDescription = null,
                        tint = AppColors.primary80,
                        modifier = Modifier.size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = author.address,
                        style = AppTextStyles.smallerTextRegular.copy(
                            color = AppColors.gray3
                        )
                    )
                }
            }
        }

        // Follow Button
        SmallButton(
            text = if (isFollowing) "Following" else "Follow",
            onClick = onFollowClick,
            modifier = Modifier.size(85.dp, 37.dp)

        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RecipeAuthorSectionPreview() {
    val sampleChef = Chef(
        id = 1,
        name = "Chef John",
        imageUrls = "https://cdn.pixabay.com/photo/2022/10/19/01/02/woman-7531315_1280.png",
        address = "Lagos, Nigeria"
    )

    RecipeAuthorSection(
        author = sampleChef,
        isFollowing = false,
        onFollowClick = {}
    )
}

