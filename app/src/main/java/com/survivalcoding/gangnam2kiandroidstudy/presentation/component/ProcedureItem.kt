package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Procedure
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun ProcedureItem(
    procedure: Procedure,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = AppColors.Gray4,
                shape = RoundedCornerShape(12.dp),
            ),
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp),
        ) {
            Text(
                text = "Step ${procedure.step}",
                style = AppTextStyles.PoppinsSmallerRegular.copy(color = AppColors.Black),
            )
            Text(
                text = procedure.content,
                style = AppTextStyles.PoppinsSmallerRegular.copy(color = AppColors.Gray3),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProcedureItemPreview() {
    val procedure = Procedure(
        recipeId = 1,
        step = 1,
        content = "Lorem Ipsum tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur?",
    )
    ProcedureItem(procedure = procedure)
}

@Preview(showBackground = true)
@Composable
fun LongNameProcedureItemPreview() {
    val procedure = Procedure(
        recipeId = 1,
        step = 2,
        content = "Lorem Ipsum tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur?\n" +
                "Tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur?",
    )
    ProcedureItem(procedure = procedure)
}